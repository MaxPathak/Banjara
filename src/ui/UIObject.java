package src.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;

public abstract class UIObject {

    protected float x, y;
    protected int width, height;
    protected Rectangle bounds;
    protected boolean focused = false;
    protected boolean blinking = false;

    public UIObject(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bounds = new Rectangle((int) x, (int) y, width, height);
    }

    public abstract void update();

    public abstract void render(Graphics g);

    public abstract void onClick();

    public void onMouseMove(MouseEvent e) {

        /*
         * if (bounds.contains(e.getX(), e.getY())) setFocused(true); else hovering =
         * false;
         */
    }

    public void onMouseRelease(MouseEvent e) {
        // if(hovering)
        if (bounds.contains(e.getX(), e.getY())) {
            if (isFocused()) {
                setFocused(false);
                onClick();
            } else {
                setFocused(true);
            }
        }
    }

    // Getters and Setters

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isFocused() {
        return focused;
    }

    public void setFocused(boolean focused) {
        this.focused = focused;
    }

    public boolean isBlinking() {
        return blinking;
    }

    public void setBlinking(boolean blinking) {
        this.blinking = blinking;
    }

    public void toggleBlinking() {
        blinking = !blinking;
    }

}
