package src.entities.statics;

import java.awt.Graphics;

import javax.swing.Action;

import java.awt.Color;

import src.Handler;
import src.commands.CommandManager;
import src.gfx.Assets;
import src.global.Global.Direction;
import src.input.KeyManager;
import src.items.usable.Item;
import src.tiles.Tile;

public class Chest extends StaticEntity {

    private boolean open;

    public Chest(Handler handler, int x, int y, CommandManager commandManager) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT, commandManager);

        open = false;
        dFix.add(Direction.DOWN);
        // dFix.add(Direction.UP);

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
            g.drawImage(Assets.cchest, (int) (x - bounds.x - handler.getGameCamera().getxOffset()),
                    (int) (y - bounds.y - handler.getGameCamera().getyOffset()), width, height, null);
        } else {
            g.drawImage(Assets.ochest, (int) (x - bounds.x - handler.getGameCamera().getxOffset()),
                    (int) (y - bounds.y - handler.getGameCamera().getyOffset()), width, height, null);
        }
        /*
         * g.setColor(Color.red); g.fillRect((int) (pX -
         * handler.getGameCamera().getxOffset()), (int) (pY -
         * handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
         */
    }

    @Override
    public void effect() {
        boolean skip = true;
        Direction oppDir = handler.getMap().getEntityManager().getPlayer().getOppDirection();

        for (Direction d : dFix) {
            if (d == oppDir) {
                skip = false;
                break;
            }
        }
        if (skip)
            return;

        if (!open) {
            handler.getMap().getEntityManager().getPlayer().getInventory().print();

            open = true;
            System.out.println("\nOpened a chest!");
            if (commandManager != null)
                commandManager.execute();

            handler.getMap().getEntityManager().getPlayer().getInventory().print();

        }
    }

}
