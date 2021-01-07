package src.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import src.Handler;
import src.tiles.Tile;

public abstract class Entity {

    protected Handler handler;
    protected float pX, pY;
    protected int x, y;
    protected int width, height;
    protected Rectangle bounds;
    protected int trigger; // ! 0: Action Button, 1: Parallel, 2: Autorun

    public Entity(Handler handler, int x, int y, int width, int height) {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.pX = x * Tile.TILEWIDTH;
        this.pY = y * Tile.TILEHEIGHT;
        this.width = width;
        this.height = height;
        this.trigger = 0;

        bounds = new Rectangle(0, 0, width, height);
    }

    public abstract void update();

    public abstract void render(Graphics g);

    public abstract void event();

    public boolean checkEntityCollisions(float xOffset, float yOffset) {
        for (Entity e : handler.getMap().getEntityManager().getEntities()) {
            if (e.equals(this))
                continue;
            if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
                return true;
        }
        return false;
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset) {
        return new Rectangle((int) (pX + xOffset), (int) (pY + yOffset), bounds.width, bounds.height);
    }

    public float getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        this.pX = x * Tile.TILEWIDTH;
    }

    public float getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        this.pY = y * Tile.TILEHEIGHT;
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
