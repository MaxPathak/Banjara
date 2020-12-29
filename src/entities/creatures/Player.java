package src.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import src.Handler;
import src.gfx.Animation;
import src.gfx.Assets;
import src.global.Global;

public class Player extends Creature {

    // Animations
    private Animation animDown, animLeft, animRight, animUp;
    private Global.Direction direction = Global.Direction.DOWN;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        bounds.width = (int) (width * 0.6) - 1;
        bounds.height = (int) (height * 0.4);
        bounds.x = (width - bounds.width) / 2;
        bounds.y = height - bounds.height;

        // Animations
        int animSpeed = 375;
        animDown = new Animation(animSpeed, Assets.player_down);
        animLeft = new Animation(animSpeed, Assets.player_left);
        animRight = new Animation(animSpeed, Assets.player_right);
        animUp = new Animation(animSpeed, Assets.player_up);
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;

        direction = handler.getKeyManager().direction;

        if(handler.getKeyManager().moving) {
            switch (direction) {
                case UP:    yMove -= speed;
                    break;
                case DOWN:  yMove += speed;
                    break;
                case LEFT:  xMove -= speed;
                    break;
                case RIGHT: xMove += speed;
                    break;
            }
        }
    }
    
    @Override
    public void update() {
        // Animations
        animDown.update();
        animLeft.update();
        animRight.update();
        animUp.update();

        // Movement
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
    }

    @Override
    public void render(Graphics g) {

        g.drawImage(getCurrentAnimationFrame(), (int) (x - bounds.x - handler.getGameCamera().getxOffset()), (int) (y - bounds.y - handler.getGameCamera().getyOffset()), width, height, null);

        g.setColor(Color.red);
        g.fillRect((int) ( x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
    }
    
    private BufferedImage getCurrentAnimationFrame() {
        if(xMove < 0) {
            return animLeft.getCurrentFrame();
        } else if(xMove > 0) {
            return animRight.getCurrentFrame();
        } else if(yMove < 0) {
            return animUp.getCurrentFrame();
        } else if(yMove > 0) {
            return animDown.getCurrentFrame();
        }else {
            switch(direction) {
                case DOWN: 
                    animDown.setIndex(0);
                    return animDown.getCurrentFrame();
                case LEFT: 
                    animLeft.setIndex(0);
                    return animLeft.getCurrentFrame();
                case RIGHT: 
                    animRight.setIndex(0);
                    return animRight.getCurrentFrame();
                case UP: 
                    animUp.setIndex(0);
                    return animUp.getCurrentFrame();
            }
        }
        animDown.setIndex(0);
        return animDown.getCurrentFrame();
    }

}
