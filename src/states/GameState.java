package src.states;

import src.Game;
import src.tiles.Tile;
import src.maps.Map;
import src.entities.creatures.Player;

import java.awt.Graphics;

public class GameState extends State {

    private Player player;
    private Map map;

    public GameState(Game game) {
        super(game);
        player = new Player(game, 0, 0);
        map = new Map(game, "data/maps/map01.txt");
        
        game.getGameCamera().move(0, 0);
    }

    @Override
    public void update() {
        map.update();
        player.update();
    }

    @Override
    public void render(Graphics g) {
        map.render(g);
        player.render(g);
        /*Tile.tiles[0].render(g, 0, 0);
        Tile.tiles[1].render(g, 48, 0);
        Tile.tiles[2].render(g, 48*2, 0);*/
        //g.drawImage(Assets.bush, 0, 48, null);
    }

}
