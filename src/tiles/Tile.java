package src.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {

    public static Tile[] tiles = new Tile[256];
    public static Tile grassTile = new GrassTile(0);
    public static Tile dirtTile = new DirtTile(1);
    public static Tile bushTile = new BushTile(2);

    public static final int TILEWIDTH = 36 / 2, TILEHEIGHT = 36 / 2;

    protected BufferedImage img;
    protected final int id;

    public Tile(BufferedImage img, int id) {
        this.img = img;
        this.id = id;

        tiles[id] = this;
    }

    public void update() {

    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(img, x, y, TILEWIDTH, TILEHEIGHT, null);
    }

    public boolean isSolid() {
        return false;
    }

    public int getId() {
        return id;
    }

}
