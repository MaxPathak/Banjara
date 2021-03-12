package src.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
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

    private int arSize = DEFAULT_AR_SIZE;
    // Animations
    private Animation anim_down, anim_left, anim_right, anim_up;
    private Global.Direction direction = Global.Direction.DOWN;

    // Inventory
    private Inventory inventory;

    public Player(Handler handler, int x, int y, CommandManager commandManager) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT, commandManager);

        ar = new Rectangle();

        // Animations
        int animSpeed = 375;
        anim_down = new Animation(animSpeed, Assets.player_down);
        anim_left = new Animation(animSpeed, Assets.player_left);
        anim_right = new Animation(animSpeed, Assets.player_right);
        anim_up = new Animation(animSpeed, Assets.player_up);

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

        if (handler.getKeyManager().moving) {
            switch (direction) {
            case UP:
                yMove -= speed;
                break;
            case DOWN:
                yMove += speed;
                break;
            case LEFT:
                xMove -= speed;
                break;
            case RIGHT:
                xMove += speed;
                break;
            }
        }
    }

    @Override
    public void update() {
        // Animations
        anim_down.update();
        anim_left.update();
        anim_right.update();
        anim_up.update();

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
            return anim_left.getCurrentFrame();
        } else if (xMove > 0) {
            return anim_right.getCurrentFrame();
        } else if (yMove < 0) {
            return anim_up.getCurrentFrame();
        } else if (yMove > 0) {
            return anim_down.getCurrentFrame();
        } else {
            switch (direction) {
            case DOWN:
                anim_down.setIndex(0);
                return anim_down.getCurrentFrame();
            case LEFT:
                anim_left.setIndex(0);
                return anim_left.getCurrentFrame();
            case RIGHT:
                anim_right.setIndex(0);
                return anim_right.getCurrentFrame();
            case UP:
                anim_up.setIndex(0);
                return anim_up.getCurrentFrame();
            }
        }
        anim_down.setIndex(0);
        return anim_down.getCurrentFrame();
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
