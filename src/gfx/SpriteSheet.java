package src.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {
    private BufferedImage sheet;

    public SpriteSheet(BufferedImage sheet) {
        this.sheet = sheet;
    }

    public BufferedImage crop(int x, int y, int width, int height, int size) {
        //Return sheet object cropped to required dimensions
        return sheet.getSubimage(x, y, width * size, height * size);
    }

    public BufferedImage crop(int id, int width, int height, int size) {
        int cols = sheet.getWidth() / width;
        int x = (id % cols) * width, y = (id / cols) * height;
        return sheet.getSubimage(x, y, width * size, height * size);
    }
}
