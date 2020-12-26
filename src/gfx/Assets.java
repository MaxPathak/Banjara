package src.gfx;

import java.awt.image.BufferedImage;

public class Assets {
    private static final int width = 24, height = 24, icon_width = 32, icon_height = 32;

    private static final int characterWidth = 48, characterHeight = 48;

    public static BufferedImage player, grass, dirt, tree, bush;
    
    public static void init() {
        
        SpriteSheet Outside_A2 = new SpriteSheet(ImageLoader.loadImage("/img/tilesets/Outside_A2.png"));
        grass = Outside_A2.crop(width * 0, height * 0, width, height, 1);
        dirt = Outside_A2.crop(width * 0, height * 4 * 2, width, height, 1);
        
        SpriteSheet Outside_B = new SpriteSheet(ImageLoader.loadImage("/img/tilesets/Outside_B.png"));
        tree = Outside_B.crop(width * 0, height * 14, width, height, 4);
        bush = Outside_B.crop(width * 6 * 2, height * 12 * 2, width, height, 2);
        //bush = Outside_B.crop(780, width, height, 2);
        
        SpriteSheet Actor1 = new SpriteSheet(ImageLoader.loadImage("/img/characters/Actor1.png"));
        player = Actor1.crop(characterWidth * 1, characterHeight * 0, width, height, 2);
    }

}
