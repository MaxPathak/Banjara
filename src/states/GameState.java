package src.states;

import java.awt.Graphics;

import src.Handler;
import src.entities.creatures.Player;
import src.entities.statics.Chest;
import src.global.Global;
import src.maps.Map;
import src.tiles.Tile;

public class GameState extends State {

    private Map map;

    public static final int STATE_ID = 1;

    public GameState() {
        super();
        setId(GameState.STATE_ID);
        map = new Map(handler, "data/maps/map01.txt");
        handler.setMap(map);
        handler.getGameCamera().move(0, 0);
    }

    @Override
    public void update() {
        map.update();

        if (handler.getGame().getKeyManager().keyJustPressed(Global.KEY_X)) {
            changeState(new MenuState());
        }

    }

    @Override
    public void render(Graphics g) {
        map.render(g);
        /*
         * Tile.tiles[0].render(g, 0, 0); Tile.tiles[1].render(g, 48, 0);
         * Tile.tiles[2].render(g, 48*2, 0);
         */
        // g.drawImage(Assets.bush, 0, 48, null);
    }

}
