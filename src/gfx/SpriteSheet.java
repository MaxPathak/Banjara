package src.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {
    private BufferedImage sheet;

    public SpriteSheet(BufferedImage sheet) {
        this.sheet = sheet;
    }

    public BufferedImage crop(int x, int y, int width, int height, int scale) {
        // Return sheet object cropped to required dimensions
        return sheet.getSubimage(x, y, width * scale, height * scale);
    }

    public BufferedImage crop(int id, int width, int height, int scale) {
        int cols = sheet.getWidth() / width;
        int x = (id % cols) * width, y = (id / cols) * height;
        return sheet.getSubimage(x, y, width * scale, height * scale);
    }
}
