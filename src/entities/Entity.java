package src.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import src.Handler;
import src.tiles.Tile;

public abstract class Entity {

    protected Handler handler;
    protected int id;
    protected float x, y;
    protected int width, height;
    protected Rectangle bounds, ar;

    protected PageList pageList;
    // protected int trigger;
    // ! 0: Action Button, 1: Player Touch, 2: Parallel, 3: Autorun
    // protected ArrayList<Direction> dFix;
    // protected CommandManager pages;

    public Entity(Handler handler, int id, int x, int y, int width, int height, PageList pages) {
        this.handler = handler;
        this.id = id;
        this.x = x * Tile.TILEWIDTH;
        this.y = y * Tile.TILEHEIGHT;
        this.width = width;
        this.height = height;
        this.pageList = pages;

        // this.trigger = 0;
        // dFix = new ArrayList<Direction>();

        bounds = new Rectangle();
        createBounds();

        // System.out.println("Object:\nx: " + this.x + ", y: " + this.y + "\nw: " +
        // this.width + ", h: " + this.height);
        // System.out.println(
        // "Bounds:\nx: " + bounds.x + ", y: " + bounds.y + "\nw: " + bounds.width + ",
        // h: " + bounds.height);

    }

    public abstract void update();

    public abstract void render(Graphics g);

    public abstract void effect();

    public void createBounds() {
        bounds.width = (int) (width * 0.6) - 1;
        bounds.height = (int) (height * 0.39);
        bounds.x = (width - bounds.width) / 2;
        bounds.y = height - bounds.height;
    }

    public void adjustPosition() {
        this.x += (Tile.TILEWIDTH * 2 - bounds.width) / 2;
        this.y += Tile.TILEHEIGHT * 2 - bounds.height;
    }

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
        this.x = x * Tile.TILEWIDTH + (Tile.TILEWIDTH * 2 - bounds.width) / 2;
    }

    public float getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y * Tile.TILEHEIGHT + (Tile.TILEHEIGHT * 2 - bounds.height);
    }

    public int getGridX() {
        return (int) ((x + 6 - (Tile.TILEWIDTH * 2 - bounds.width) / 2) / Tile.TILEWIDTH);
    }

    public int getGridY() {
        return (int) ((y + 6 - (Tile.TILEHEIGHT * 2 - bounds.height)) / Tile.TILEHEIGHT);
    }

    public void setPosition(int x, int y) {
        this.x = x * Tile.TILEWIDTH * 2 + (Tile.TILEWIDTH * 2 - bounds.width) / 2;
        this.y = y * Tile.TILEHEIGHT * 2 + Tile.TILEHEIGHT * 2 - bounds.height;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PageList getPageList() {
        return pageList;
    }

    public void setPageList(PageList pageList) {
        this.pageList = pageList;
    }

}
