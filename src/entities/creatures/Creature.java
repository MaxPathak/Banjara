package src.entities.creatures;

import src.Handler;
import src.commands.CommandManager;
import src.entities.Entity;
import src.tiles.Tile;

public abstract class Creature extends Entity {

    public static final int DEFAULT_HEALTH = 10;
    public static final float DEFAULT_SPEED = 4.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 48, DEFAULT_CREATURE_HEIGHT = 48;

    protected int health;
    protected float speed;
    protected float xMove, yMove;

    public Creature(Handler handler, int x, int y, int width, int height, CommandManager commandManager) {
        super(handler, x, y, width, height, commandManager);
        health = DEFAULT_HEALTH;
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }

    public void move() {
        if (!checkEntityCollisions(xMove, 0))
            moveX();
        if (!checkEntityCollisions(0, yMove))
            moveY();
        // System.out.println("x: " + x + ", y: " + y);
    }

    public void moveX() {
        if (x + xMove < 0) {
            x = 0;
        } else if (x + xMove + bounds.width > handler.getMap().getWidth() * Tile.TILEWIDTH) {
            x = handler.getMap().getWidth() * Tile.TILEWIDTH - bounds.width;
        } else if (xMove > 0) { // Move right
            int tx = (int) (x + xMove + bounds.width - 1) / Tile.TILEWIDTH;

            for (int i = 0; i < bounds.height; i++) {
                if (collisionWithTile(tx, (int) (y + i) / Tile.TILEHEIGHT)) {
                    x = tx * Tile.TILEWIDTH - bounds.width;
                    return;
                }
            }
            x += xMove;
        } else if (xMove < 0) { // Move left
            int tx = (int) (x + xMove) / Tile.TILEWIDTH;

            for (int i = 0; i < bounds.height; i++) {
                if (collisionWithTile(tx, (int) (y + i) / Tile.TILEHEIGHT)) {
                    x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH;
                    return;
                }
            }
            x += xMove;
        }
    }

    public void moveY() {
        if (y + yMove < 0) {
            y = 0;
        } else if (y + yMove + bounds.height > handler.getMap().getHeight() * Tile.TILEHEIGHT) {
            y = handler.getMap().getHeight() * Tile.TILEHEIGHT - bounds.height;
        } else if (yMove < 0) { // Move up
            int ty = (int) (y + yMove) / Tile.TILEHEIGHT;

            for (int i = 0; i < bounds.width; i++) {
                if (collisionWithTile((int) (x + i) / Tile.TILEWIDTH, ty)) {
                    y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT;
                    return;
                }
            }
            y += yMove;
        } else if (yMove > 0) { // Move down
            int ty = (int) (y + yMove + bounds.height - 1) / Tile.TILEHEIGHT;

            for (int i = 0; i < bounds.width; i++) {
                if (collisionWithTile((int) (x + i) / Tile.TILEWIDTH, ty)) {
                    y = ty * Tile.TILEHEIGHT - bounds.height;
                    return;
                }
            }
            y += yMove;
        }
    }

    protected boolean collisionWithTile(int x, int y) {
        return handler.getMap().getTile(x, y).isSolid();
    }

    // Getters and Setters

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }

}
