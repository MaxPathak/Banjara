package src.entities.creatures;

import src.Handler;
import src.entities.Entity;
import src.tiles.Tile;

public abstract class Creature extends Entity {

    public static final int DEFAULT_HEALTH = 10;
    public static final float DEFAULT_SPEED = 4.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 48, DEFAULT_CREATURE_HEIGHT = 48;

    protected int health;
    protected float speed;
    protected float xMove, yMove;

    public Creature(Handler handler, int x, int y, int width, int height) {
        super(handler, x, y, width, height);
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
        if (pX + xMove < 0) {
            pX = 0;
        } else if (pX + xMove + bounds.width > handler.getMap().getWidth() * Tile.TILEWIDTH) {
            pX = handler.getMap().getWidth() * Tile.TILEWIDTH - bounds.width;
        } else if (xMove > 0) { // Move right
            int tx = (int) (pX + xMove + bounds.width - 1) / Tile.TILEWIDTH;

            for (int i = 0; i < bounds.height; i++) {
                if (collisionWithTile(tx, (int) (pY + i) / Tile.TILEHEIGHT)) {
                    pX = tx * Tile.TILEWIDTH - bounds.width;
                    return;
                }
            }
            pX += xMove;
        } else if (xMove < 0) { // Move left
            int tx = (int) (pX + xMove) / Tile.TILEWIDTH;

            for (int i = 0; i < bounds.height; i++) {
                if (collisionWithTile(tx, (int) (pY + i) / Tile.TILEHEIGHT)) {
                    pX = tx * Tile.TILEWIDTH + Tile.TILEWIDTH;
                    return;
                }
            }
            pX += xMove;
        }
    }

    public void moveY() {
        if (pY + yMove < 0) {
            pY = 0;
        } else if (pY + yMove + bounds.height > handler.getMap().getHeight() * Tile.TILEHEIGHT) {
            pY = handler.getMap().getHeight() * Tile.TILEHEIGHT - bounds.height;
        } else if (yMove < 0) { // Move up
            int ty = (int) (pY + yMove) / Tile.TILEHEIGHT;

            for (int i = 0; i < bounds.width; i++) {
                if (collisionWithTile((int) (pX + i) / Tile.TILEWIDTH, ty)) {
                    pY = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT;
                    return;
                }
            }
            pY += yMove;
        } else if (yMove > 0) { // Move down
            int ty = (int) (pY + yMove + bounds.height - 1) / Tile.TILEHEIGHT;

            for (int i = 0; i < bounds.width; i++) {
                if (collisionWithTile((int) (pX + i) / Tile.TILEWIDTH, ty)) {
                    pY = ty * Tile.TILEHEIGHT - bounds.height;
                    return;
                }
            }
            pY += yMove;
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
