package src.ui;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import src.gfx.Assets;
import src.gfx.Text;
import src.global.Global;

public class UITextButton extends UIObject {

    private String text;
    private int strokeValue;
    private ClickListener clicker;

    public UITextButton(String text, float x, float y, int width, int height, int strokeValue, ClickListener clicker) {
        super(x, y, width, height);
        this.text = text;
        this.strokeValue = strokeValue;
        this.clicker = clicker;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Color color = Color.white;
        int borderRadius = 5;
        int padding = 5;

        // Set Stroke and Color
        g2.setStroke(new java.awt.BasicStroke(strokeValue));
        g2.setColor(new Color(0, 0, 0, .7f));
        // color = color.darker();

        if (isFocused() && isBlinking()) {
            // color = color.brighter();
            g2.setColor(new Color(0, 0, 0, .5f));
        } else {
            // asd
        }

        // Background
        g2.fillRoundRect((int) x, (int) y, width, height, borderRadius, borderRadius);

        // Border
        g2.setColor(color);
        g2.drawRoundRect((int) x, (int) y, width, height, borderRadius, borderRadius);

        Text.drawString(g, text, (int) (x + padding), (int) (y + height / 2), false, true, color,
                Assets.regularFont.get(24));

        // GradientPaint blueToBlack = new GradientPaint(0, 0, Color.BLUE, 400, 400,
        // Color.BLACK);
        // g2.setPaint(blueToBlack);
        // g2.fill(new Rectangle2D.Double(50, 160, 500, 100));
        // g2.setPaint(new GradientPaint(0, 0, Color.BLUE, 200, 200, Color.BLACK));
        // g2.fill(new Rectangle2D.Double((int) x, (int) y, width, height));

    }

    @Override
    public void onClick() {
        clicker.onClick();

    }

}
