package src.gfx;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class FontLoader {

    public static CustomFont loadFont(String path) {
        try {
            return new CustomFont(Font.createFont(Font.TRUETYPE_FONT, new File('.' + path)).deriveFont(24));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

}
