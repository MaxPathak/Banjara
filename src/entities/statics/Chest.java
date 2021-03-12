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
import src.states.State;
import src.states.scenes.TextScene;
import src.tiles.Tile;

public class Chest extends StaticEntity {

    private boolean open;

    public Chest(Handler handler, int x, int y, CommandManager commandManager) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT, commandManager);

        open = false;
        dFix.add(Direction.DOWN);
        // dFix.add(Direction.UP);
        System.out.println(this.x / Tile.TILEWIDTH + ", " + this.y / Tile.TILEHEIGHT);
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
        Direction oppDir = handler.getMap().getEntityManager().getPlayer().getOppositeDirection();

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
            if (pages != null)
                pages.execute();

            handler.getMap().getEntityManager().getPlayer().getInventory().print();
        }
    }

}
