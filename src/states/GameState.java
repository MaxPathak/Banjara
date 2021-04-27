package src.states;

import java.awt.Graphics;
import java.util.ArrayList;

import src.commands.Command;
import src.entities.creatures.Player;
import src.global.Global;
import src.maps.Map;
import src.utils.JSONMapReader;

public class GameState extends State {

    private Map currentMap;

    private ArrayList<Map> maps;

    public static final int STATE_ID = 1;

    public GameState() {
        super();
        setId(GameState.STATE_ID);
        Player player = new Player(handler, 0, 0, null);
        // Load Maps
        maps = new ArrayList<Map>();
        maps.add(JSONMapReader.loadMap(handler, player, "map01"));
        maps.add(JSONMapReader.loadMap(handler, player, "house01"));

        currentMap = maps.get(0);

        // Set Starting Map
        handler.setMap(currentMap);
        handler.getGameCamera().move(0, 0);

        if (handler.getMap().getName().equals("map01"))
            Command.transferPlayer(0, 2, 7, null); // Map01 Starting Pos
        else if (handler.getMap().getName().equals("house01"))
            Command.transferPlayer(3, 13, 18, null); // House01 Starting Pos

    }

    @Override
    public void update() {
        currentMap.update();

        if (handler.getGame().getKeyManager().keyJustPressed(Global.KEY_X)) {
            if (handler.getGame().menuState == null)
                handler.getGame().menuState = new MenuState();
            handler.getGame().menuState.FOCUSED_ID = 0;
            changeState(handler.getGame().menuState);
        }

    }

    @Override
    public void render(Graphics g) {
        currentMap.render(g);
        /*
         * Tile.tiles[0].render(g, 0, 0); Tile.tiles[1].render(g, 48, 0);
         * Tile.tiles[2].render(g, 48*2, 0);
         */
        // g.drawImage(Assets.bush, 0, 48, null);
    }

    public ArrayList<Map> getMaps() {
        return maps;
    }

    public void setCurrentMap(Map currentMap) {
        this.currentMap = currentMap;
    }

}
