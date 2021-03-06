package src.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class UIImageButton extends UIObject {

    private Image[] images;
    private ClickListener clicker;

    public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener clicker) {
        super(x, y, width, height);
        this.images = (Image[]) images;
        this.clicker = clicker;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    @Override
    public void render(Graphics g) {
        if (isFocused() && isBlinking())
            g.drawImage(images[1], (int) x, (int) y, width, height, null);
        else
            g.drawImage(images[0], (int) x, (int) y, width, height, null);

    }

    @Override
    public void onClick() {
        clicker.onClick();

    }

}
