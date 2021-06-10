package src.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class SpriteSheet implements Serializable {
    transient private BufferedImage sheet;

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

    public BufferedImage getSheet() {
        return sheet;
    }

    public void setSheet(BufferedImage sheet) {
        this.sheet = sheet;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        ImageIO.write(sheet, "png", out); // png is lossless
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        sheet = ImageIO.read(in);
    }

}
