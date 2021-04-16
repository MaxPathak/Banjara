package src.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class CompoundTile {

    private BufferedImage img[];

    public CompoundTile(int tile0, int tile1, int tile2, int tile3) {
        this.img = new BufferedImage[4];
        img[0] = Tile.tiles[tile0].img;
        img[1] = Tile.tiles[tile1].img;
        img[2] = Tile.tiles[tile2].img;
        img[3] = Tile.tiles[tile3].img;
    }

    public void update() {

    }

    public void render(Graphics g, int x, int y) {
        int width = Tile.TILEWIDTH / 2, height = Tile.TILEHEIGHT / 2;
        g.drawImage(img[0], x, y, width, height, null);
        g.drawImage(img[1], x + width, y, width, height, null);
        g.drawImage(img[2], x, y + height, width, height, null);
        g.drawImage(img[3], x + width, y + height, width, height, null);
    }

}
