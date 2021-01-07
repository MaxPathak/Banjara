package src.entities.statics;

import java.awt.Graphics;

import javax.swing.Action;

import java.awt.Color;

import src.Handler;
import src.gfx.Assets;
import src.input.KeyManager;
import src.tiles.Tile;

public class Chest extends StaticEntity {

    private boolean open;

    public Chest(Handler handler, int x, int y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        open = false;

        bounds.width = (int) (width * 0.6) - 1;
        bounds.height = (int) (height * 0.4);
        bounds.x = (width - bounds.width) / 2;
        bounds.y = height - bounds.height;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        if (!open) {
            g.drawImage(Assets.cchest, (int) (pX - bounds.x - handler.getGameCamera().getxOffset()),
                    (int) (pY - bounds.y - handler.getGameCamera().getyOffset()), width, height, null);
        } else {
            g.drawImage(Assets.ochest, (int) (pX - bounds.x - handler.getGameCamera().getxOffset()),
                    (int) (pY - bounds.y - handler.getGameCamera().getyOffset()), width, height, null);
        }
        /*
         * g.setColor(Color.red); g.fillRect((int) ( x -
         * handler.getGameCamera().getxOffset()), (int) (y-
         * handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
         */
    }

    @Override
    public void event() {
        if (handler.getGame().getKeyManager().isInteracting()) {

        }

    }

}
