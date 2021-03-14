package src.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import src.Handler;
import src.commands.CommandManager;
import src.entities.Entity;
import src.entities.PageList;
import src.entities.events.Event;
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
    private int gold;

    public Player(Handler handler, int x, int y, PageList pages) {
        super(handler, 0, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT, pages);

        ar = new Rectangle();

        // Animations
        animations = new Animation[4];
        BufferedImage[][] images = Assets.getSprites("Actor1", 0);
        for (int i = 0; i < animations.length; i++)
            animations[i] = new Animation(DEFAULT_ANIMATION_SPEED, images[i]);

        inventory = new Inventory(handler);

        gold = 100;

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
            if (handler.getGame().getKeyManager().keyJustPressed(Global.KEY_Z)) {
                if (e instanceof Event) {
                    if (((Event) e).getCurrentPage().getTrigger() == 0)
                        e.effect();
                }
            }
        }

        if ((e = checkEntityInteractions()) != null) {
            if (e instanceof Event) {
                if (((Event) e).getCurrentPage().getTrigger() == 1)
                    e.effect();
            }
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

    }

    @Override
    public void effect() {
        // TODO Auto-generated method stub

    }

    private BufferedImage getCurrentAnimationFrame() {
        if (xMove < 0) {
            return animations[Direction.LEFT.ordinal()].getCurrentFrame();
        } else if (xMove > 0) {
            return animations[Direction.RIGHT.ordinal()].getCurrentFrame();
        } else if (yMove < 0) {
            return animations[Direction.UP.ordinal()].getCurrentFrame();
        } else if (yMove > 0) {
            return animations[Direction.DOWN.ordinal()].getCurrentFrame();
        }
        animations[direction.ordinal()].setIndex(1);
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
        return null;
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

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

}
