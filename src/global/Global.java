package src.global;

import java.awt.Color;
import java.awt.event.KeyEvent;

public class Global {
    public static enum Direction {
        DOWN, LEFT, RIGHT, UP
    };

    public static Direction direction = Direction.DOWN;

    public static final float DEFAULT_FONTSIZE = 24;

    public static int KEY_Z = KeyEvent.VK_Z, KEY_X = KeyEvent.VK_X;

    public static Color rgba(int r, int g, int b, float a) {
        return new Color(r, g, b, (int) (255 * a));
    }

    public static int[] variables = new int[30];
    public static boolean[] switches = new boolean[30];

}
