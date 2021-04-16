package src.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class Text {

    public static void drawString(Graphics g, String str, int xPos, int yPos, boolean center, Color c, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        g.setColor(c);
        g.setFont(font);
        int x = xPos;
        int y = yPos + metrics.getAscent();
        if (center) {
            x = xPos - metrics.stringWidth(str) / 2;
            y = (yPos - metrics.getHeight() / 2) + metrics.getAscent();
        }
        g.drawString(str, x, y);
    }

    public static void drawString(Graphics g, String str, int xPos, int yPos, boolean centerX, boolean centerY, Color c,
            Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        g.setColor(c);
        g.setFont(font);
        int x = xPos;
        int y = yPos + metrics.getAscent();
        if (centerX)
            x = xPos - metrics.stringWidth(str) / 2;
        if (centerY)
            y = (yPos - metrics.getHeight() / 2) + metrics.getAscent();
        g.drawString(str, x, y);
    }

}
