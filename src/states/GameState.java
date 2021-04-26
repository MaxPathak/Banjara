package src.states;

import java.awt.Graphics;

import src.commands.Command;
import src.global.Global;
import src.maps.Map;
import src.utils.JSONMapReader;

public class GameState extends State {

    private Map map;

    public static final int STATE_ID = 1;

    public GameState() {
        super();
        setId(GameState.STATE_ID);
        // Load Maps
        map = JSONMapReader.loadMap(handler, "data/tiles/map01.json");

        // Set Starting Map
        handler.setMap(map);
        handler.getGameCamera().move(0, 0);

        Command.transferPlayer(0, 2, 7); // Map01 Starting Pos
        // Command.transferPlayer(3, 13, 18); // House01 Starting Pos

    }

    @Override
    public void update() {
        map.update();

        if (handler.getGame().getKeyManager().keyJustPressed(Global.KEY_X)) {
            if (handler.getGame().menuState == null)
                handler.getGame().menuState = new MenuState();
            handler.getGame().menuState.FOCUSED_ID = 0;
            changeState(handler.getGame().menuState);
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
