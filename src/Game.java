package src;

import src.databases.DatabaseManager;
import src.display.Display;
import src.gfx.Assets;
import src.gfx.GameCamera;
import src.input.KeyManager;
import src.input.MouseManager;
import src.states.GameState;
import src.states.MenuState;
import src.states.State;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {
    private Display display;
    public String title;
    private int width, height;

    private boolean running = false;
    private boolean released = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    // States
    public State gameState;
    public State menuState;

    // Input
    private KeyManager keyManager;
    private MouseManager mouseManager;

    // Camera
    private GameCamera gameCamera;

    // Handler
    private Handler handler;

    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
        new DatabaseManager();
    }

    private void init() {
        // Initialize the Display object
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();

        handler = new Handler(this);
        gameCamera = new GameCamera(handler, 0, 0);
        State.setHandler(handler);
        gameState = new GameState();
        menuState = new MenuState();
        State.setState(menuState);

        // System.out.println("Fields: ");
        // for (Field f : this.getClass().getDeclaredFields()) {
        // if (!f.getType().isPrimitive()) {
        // System.out.print(f.getType().getName());
        // System.out.println(": " + f.getDeclaringClass().getName());
        // }
        // }

        // System.out.println("Methods: ");
        // for (Method method : this.getClass().getDeclaredMethods()) {
        // System.out.print(method.getName());
        // System.out.println(": " + method.getDeclaringClass().getName());
        // }

    }

    private void update() {
        setReleased(false);

        keyManager.update();

        if (State.getState() != null) {
            State.getState().update();
        }
    }

    private void render() {
        // Initialize the BufferStrategy object
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        // Initialize the Graphics object
        g = bs.getDrawGraphics();
        // Clear Screen
        g.clearRect(0, 0, width, height);
        // Start drawing
        {
            g.setColor(Color.WHITE);

            int marginx = 10, marginy = 10, thickness = 5;
            // g.setColor(Color.RED);
            // g.drawRect(marginx - 1, marginy - 1, width - 2*marginx, height - 2*marginy);
            g.setColor(Color.WHITE);
            g.fillRect(marginx, marginy, thickness, height - 2 * marginy - 1); // left
            g.fillRect(marginx, marginy, width - 2 * marginx - 1, thickness); // top
            g.fillRect(width - marginx - thickness - 1, marginy, thickness, height - 2 * marginy - 1); // right
            g.fillRect(marginx, height - marginy - thickness - 1, width - 2 * marginx - 1, thickness); // bot

        }

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);

        if (State.getState() != null) {
            State.getState().render(g);
        }

        // Stop drawing
        bs.show();
        g.dispose();
    }

    public void run() {
        init();

        int fps = 60;
        double timePerTick = 1000000000 / fps; // nano seconds / fps to slow down
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        // Main gameloop
        while (running) {
            // Reduce how many times update and render are called
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                update();
                render();
                ticks++;
                delta--;
            }

            if (timer >= 1000000000) {
                timer = ticks = 0;
            }
        }

        stop();
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    public synchronized void start() {
        if (running)
            return;
        running = true;
        // Initialize the Thread objects
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running)
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

    public boolean isReleased() {
        return released;
    }

    public void setReleased(boolean release) {
        this.released = release;
    }

}
