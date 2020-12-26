package src;

import src.display.Display;
import src.gfx.Assets;
import src.gfx.GameCamera;
import src.states.GameState;
import src.states.MenuState;
import src.states.State;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

//Temporary Imports
import src.gfx.ImageLoader;
import src.input.KeyManager;


public class Game implements Runnable {
    private Display display;
    public String title;
    private int width, height;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    private BufferedImage testImage;

    //States
    private State gameState;
    private State menuState;
    
    //Input
    private KeyManager keyManager;

    //Camera
    private GameCamera gameCamera;

    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        keyManager = new KeyManager();
    }

    private void init() {
        //Initialize the Display object
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        testImage = ImageLoader.loadImage("/img/title/title.jpg");
        Assets.init();

        gameCamera = new GameCamera(this, 0, 0);

        gameState = new GameState(this);
        menuState = new MenuState(this);
        State.setState(gameState);
    }
    
    private void update() {
        keyManager.update();

        if(State.getState() != null) {
            State.getState().update();
        }
    }

    private void render() {
        //Initialize the BufferStrategy object
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        //Initialize the Graphics object
        g = bs.getDrawGraphics();
        //Clear Screen
        g.clearRect(0, 0, width, height);
        //Start drawing
        {
            g.setColor(Color.WHITE);

            //Title Screen
            g.drawImage(testImage, 0, 0, width, height, null);
            
            int marginx = 10, marginy = 10, thickness = 5;
            //g.setColor(Color.RED);
            //g.drawRect(marginx - 1, marginy - 1, width - 2*marginx, height - 2*marginy);
            g.setColor(Color.WHITE);
            g.fillRect(marginx, marginy, thickness, height - 2*marginy - 1);  //left
            g.fillRect(marginx, marginy, width - 2*marginx - 1, thickness); //top
            g.fillRect(width - marginx - thickness - 1, marginy, thickness, height - 2*marginy - 1);    //right
            g.fillRect(marginx, height - marginy - thickness - 1, width - 2*marginx - 1, thickness);    //bot

        }

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);

        if(State.getState() != null) {
            State.getState().render(g);
        }

        //Stop drawing
        bs.show();
        g.dispose();
    }

    public void run() {
        init();

        int fps = 60;
        double timePerTick = 1000000000 / fps;  //nano seconds / fps to slow down
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        //Main gameloop
        while(running) {
            //Reduce how many times update and render are called
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;
            
            if(delta >= 1) {
                update();
                render();
                ticks++;
                delta--;
            }

            if(timer >= 1000000000) {
                timer = ticks = 0;
            }
        }

        stop();
    }
    
    public KeyManager getKeyManager() {
        return keyManager;
    }
    
    public GameCamera getGameCamera() {
        return gameCamera;
    }

    public synchronized void start() {
        if(running)
            return;
        running = true;
        //Initialize the Thread objects
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if(!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
