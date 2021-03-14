package src.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {
    private static final int width = 24, height = 24, icon_width = 32, icon_height = 32;

    private static final int characterWidth = 48, characterHeight = 48;

    private static SpriteSheet iconSet;

    public static CustomFont regularFont;

    public static BufferedImage player, grass, dirt, tree, bush, cchest, ochest, title;
    public static BufferedImage[] player_down, player_left, player_right, player_up;

    public static void init() {

        title = ImageLoader.loadImage("/img/title/title.jpg");

        regularFont = FontLoader.loadFont("/fonts/comic.ttf");

        iconSet = new SpriteSheet(ImageLoader.loadImage("/img/system/IconSet.png"));

        SpriteSheet Outside_A2 = new SpriteSheet(ImageLoader.loadImage("/img/tilesets/Outside_A2.png"));
        grass = Outside_A2.crop(width * 0, height * 0, width, height, 1);
        dirt = Outside_A2.crop(width * 0, height * 4 * 2, width, height, 1);

        SpriteSheet Outside_B = new SpriteSheet(ImageLoader.loadImage("/img/tilesets/Outside_B.png"));
        tree = Outside_B.crop(width * 0, height * 14, width, height, 4);
        bush = Outside_B.crop(width * 6 * 2, height * 12 * 2, width, height, 2);
        // bush = Outside_B.crop(780, width, height, 2);

        SpriteSheet Actor1 = new SpriteSheet(ImageLoader.loadImage("/img/characters/Actor1.png"));
        player_down = new BufferedImage[3];
        player_down[0] = Actor1.crop(characterWidth * 1, characterHeight * 0, width, height, 2);
        player_down[1] = Actor1.crop(characterWidth * 0, characterHeight * 0, width, height, 2);
        player_down[2] = Actor1.crop(characterWidth * 2, characterHeight * 0, width, height, 2);

        player_left = new BufferedImage[3];
        player_left[0] = Actor1.crop(characterWidth * 1, characterHeight * 1, width, height, 2);
        player_left[1] = Actor1.crop(characterWidth * 0, characterHeight * 1, width, height, 2);
        player_left[2] = Actor1.crop(characterWidth * 2, characterHeight * 1, width, height, 2);

        player_right = new BufferedImage[3];
        player_right[0] = Actor1.crop(characterWidth * 1, characterHeight * 2, width, height, 2);
        player_right[1] = Actor1.crop(characterWidth * 0, characterHeight * 2, width, height, 2);
        player_right[2] = Actor1.crop(characterWidth * 2, characterHeight * 2, width, height, 2);

        player_up = new BufferedImage[3];
        player_up[0] = Actor1.crop(characterWidth * 1, characterHeight * 3, width, height, 2);
        player_up[1] = Actor1.crop(characterWidth * 0, characterHeight * 3, width, height, 2);
        player_up[2] = Actor1.crop(characterWidth * 2, characterHeight * 3, width, height, 2);

        SpriteSheet Chest = new SpriteSheet(ImageLoader.loadImage("/img/characters/!Chest.png"));
        cchest = Chest.crop(characterWidth * 0, characterHeight * 0, width, height, 2);
        ochest = Chest.crop(characterWidth * 0, characterHeight * 2, width, height, 2);

    }

    public static BufferedImage getIcon(int iconIndex) {
        return iconSet.crop(iconIndex, icon_width, icon_height, 1);
    }

    public static BufferedImage[][] getSprites(String characterName, int characterIndex) {
        String path = "/img/characters/" + characterName + ".png";
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage(path));
        sheet.setSheet(sheet.crop(characterIndex, characterWidth * 3, characterHeight * 4, 1));
        BufferedImage[][] images = new BufferedImage[4][3];
        int index = 0;
        for (BufferedImage[] pattern : images) {
            pattern[0] = sheet.crop(index++, characterWidth, characterHeight, 1);
            pattern[1] = sheet.crop(index++, characterWidth, characterHeight, 1);
            pattern[2] = sheet.crop(index++, characterWidth, characterHeight, 1);
        }
        return images;
    }

}
