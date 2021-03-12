package src.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import src.Handler;
import src.commands.CommandManager;
import src.entities.Entity;
import src.gfx.Animation;
import src.gfx.Assets;
import src.global.Global;
import src.global.Global.Direction;
import src.inventory.Inventory;
import src.states.GameState;
import src.states.State;

public class Player extends Creature {

    private static final int DEFAULT_AR_SIZE = 5;
    private static final int DEFAULT_ANIMATION_SPEED = 375;

    private int arSize = DEFAULT_AR_SIZE;
    // Animations
    private Animation[] animations;
    private Global.Direction direction = Global.Direction.DOWN;

    // Inventory
    private Inventory inventory;

    public Player(Handler handler, int x, int y, CommandManager commandManager) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT, commandManager);

        ar = new Rectangle();

        // Animations
        animations = new Animation[4];
        animations[0] = new Animation(DEFAULT_ANIMATION_SPEED, Assets.player_up);
        animations[1] = new Animation(DEFAULT_ANIMATION_SPEED, Assets.player_down);
        animations[2] = new Animation(DEFAULT_ANIMATION_SPEED, Assets.player_left);
        animations[3] = new Animation(DEFAULT_ANIMATION_SPEED, Assets.player_right);

        inventory = new Inventory(handler);
    }

    private void action() {
        ar.width = ar.height = arSize;
        ar.x = (int) x;
        ar.y = (int) y;
        switch (direction) {
        case UP:
            ar.width = bounds.width;
            ar.y -= arSize;
            break;
        case DOWN:
            ar.width = bounds.width;
            ar.y += bounds.height;
            break;
        case LEFT:
            ar.height = bounds.height;
            ar.x -= arSize;
            break;
        case RIGHT:
            ar.height = bounds.height;
            ar.x += bounds.width;
            break;
        }

        Entity e;
        if ((e = checkEntityInteractions()) != null) {
            if (handler.getGame().getKeyManager().keyJustPressed(Global.KEY_Z))
                e.effect();
        }

    }

    private void getInput() {
        xMove = 0;
        yMove = 0;

        direction = handler.getKeyManager().playerDirection;

        float newSpeed = speed;

        if (handler.getKeyManager().keyHeld(KeyEvent.VK_SHIFT)) {
            float constant = 1.5f;
            newSpeed *= constant;
            for (Animation animation : animations) {
                animation.setSpeed((int) (DEFAULT_ANIMATION_SPEED / constant));
            }
        } else {
            for (Animation animation : animations) {
                animation.setSpeed(DEFAULT_ANIMATION_SPEED);
            }
        }

        if (handler.getKeyManager().moving) {
            switch (direction) {
            case UP:
                yMove -= newSpeed;
                break;
            case DOWN:
                yMove += newSpeed;
                break;
            case LEFT:
                xMove -= newSpeed;
                break;
            case RIGHT:
                xMove += newSpeed;
                break;
            }
        }
    }

    @Override
    public void update() {
        // Animations
        for (Animation animation : animations) {
            animation.update();
        }

        // Movement
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);

        action();
        // Inventory
        inventory.update();
    }

    @Override
    public void render(Graphics g) {

        g.drawImage(getCurrentAnimationFrame(), (int) (x - bounds.x - handler.getGameCamera().getxOffset()),
                (int) (y - bounds.y - handler.getGameCamera().getyOffset()), width, height, null);

        /*
         * g.setColor(Color.red); g.fillRect((int) (pX -
         * handler.getGameCamera().getxOffset()), (int) (pY -
         * handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
         * g.setColor(Color.black); g.fillRect((int) (ar.x -
         * handler.getGameCamera().getxOffset()), (int) (ar.y -
         * handler.getGameCamera().getyOffset()), ar.width, ar.height);
         */

        inventory.render(g);

    }

    @Override
    public void effect() {
        // TODO Auto-generated method stub

    }

    private BufferedImage getCurrentAnimationFrame() {
        if (xMove < 0) {
            return animations[2].getCurrentFrame();
        } else if (xMove > 0) {
            return animations[3].getCurrentFrame();
        } else if (yMove < 0) {
            return animations[0].getCurrentFrame();
        } else if (yMove > 0) {
            return animations[1].getCurrentFrame();
        }
        animations[direction.ordinal()].setIndex(0);
        return animations[direction.ordinal()].getCurrentFrame();
    }

    public Direction getOppositeDirection() {
        switch (direction) {
        case DOWN:
            return Direction.UP;
        case LEFT:
            return Direction.RIGHT;
        case RIGHT:
            return Direction.LEFT;
        case UP:
            return Direction.DOWN;
        }
        return Direction.UP;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Global.Direction getDirection() {
        return direction;
    }

    public void setDirection(Global.Direction direction) {
        this.direction = direction;
    }

}
