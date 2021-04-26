package src.global;

import java.awt.Color;
import java.awt.event.KeyEvent;

public class Global {
    public static enum Direction {
        DOWN, LEFT, RIGHT, UP
    };

    public static final float DEFAULT_FONTSIZE = 24;

    public static final int KEY_Z = KeyEvent.VK_Z, KEY_X = KeyEvent.VK_X;

    public static Direction direction;

    public static int[] variables;
    public static boolean[] switches;

    public static void resetData() {
        Global.variables = new int[30];
        Global.switches = new boolean[30];
        Global.direction = Direction.DOWN;
    }

    public static Color rgba(int r, int g, int b, float a) {
        return new Color(r, g, b, (int) (255 * a));
    }

}
