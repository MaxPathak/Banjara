package src.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import src.Handler;
import src.commands.CommandManager;
import src.global.Global.Direction;
import src.tiles.Tile;

public abstract class Entity {

    protected Handler handler;
    protected float x, y;
    protected int width, height;
    protected Rectangle bounds, ar;
    protected int trigger; // ! 0: Action Button, 1: Parallel, 2: Autorun

    protected ArrayList<Direction> dFix;

    protected CommandManager pages;

    public Entity(Handler handler, int x, int y, int width, int height, CommandManager pages) {
        this.handler = handler;
        this.x = x * Tile.TILEWIDTH;
        this.y = y * Tile.TILEHEIGHT;
        this.width = width;
        this.height = height;
        this.pages = pages;

        this.trigger = 0;
        dFix = new ArrayList<Direction>();

        bounds = new Rectangle();
        bounds.width = (int) (width * 0.6) - 1;
        bounds.height = (int) (height * 0.4);
        bounds.x = (width - bounds.width) / 2;
        bounds.y = height - bounds.height;

        this.x += (Tile.TILEWIDTH - bounds.width) / 2;
        this.y += Tile.TILEHEIGHT - bounds.height;

        System.out.println("Object:\nx: " + this.x + ", y: " + this.y + "\nw: " + this.width + ", h: " + this.height);
        System.out.println(
                "Bounds:\nx: " + bounds.x + ", y: " + bounds.y + "\nw: " + bounds.width + ", h: " + bounds.height);

    }

    public abstract void update();

    public abstract void render(Graphics g);

    public abstract void effect();

    public Entity checkEntityInteractions() {
        for (Entity e : handler.getMap().getEntityManager().getEntities()) {
            if (e.equals(this))
                continue;
            if (e.getCollisionBounds(0f, 0f).intersects(ar))
                return e;
        }
        return null;
    }

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
        return new Rectangle((int) (x + xOffset), (int) (y + yOffset), bounds.width, bounds.height);
    }

    public float getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        this.x = x * Tile.TILEWIDTH;
        this.x += (Tile.TILEWIDTH - bounds.width) / 2;
    }

    public float getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        this.y = y * Tile.TILEHEIGHT;
        this.y += Tile.TILEHEIGHT - bounds.height;
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
