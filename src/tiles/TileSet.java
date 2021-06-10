package src.tiles;

import java.awt.image.BufferedImage;
import java.io.Serializable;

import src.gfx.SpriteSheet;

public class TileSet implements Serializable {

    private SpriteSheet sheet;
    private int firstIndex;
    private int tileCount;

    public TileSet(SpriteSheet sheet, int firstIndex, int tileCount) {
        this.sheet = sheet;
        this.firstIndex = firstIndex;
        this.tileCount = tileCount;
    }

    public int getFirstIndex() {
        return firstIndex;
    }

    public int getTileCount() {
        return tileCount;
    }

    public BufferedImage crop(int val) {
        return this.sheet.crop(val - firstIndex, 24, 24, 1);
    }

}
