package src.maps;

import java.awt.Graphics;
import java.util.ArrayList;

import src.Handler;
import src.commands.Command;
import src.commands.CommandManager;
import src.commands.CommandManagerList;
import src.entities.Conditions;
import src.entities.EntityManager;
import src.entities.Page;
import src.entities.PageList;
import src.entities.creatures.Creature;
import src.entities.creatures.Player;
import src.entities.events.Event;
import src.global.Global.Direction;
import src.items.usable.Item;
import src.tiles.Tile;
import src.utils.Utils;

public class Map {

    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int[][] tiles;
    private EntityManager entityManager;

    public Map(Handler handler, String path) {
        this.handler = handler;

        loadMap(path);

        entityManager = new EntityManager(handler, new Player(handler, spawnX, spawnY, null));
        entityManager.addEntity(new Event(handler, 1, 5, 4, Tile.TILEWIDTH, Tile.TILEWIDTH, new PageList(
                new Page(0, "!Chest", 0,
                        new Conditions(0, false, 0, false, 0, 0, false, "A", false, 0, false, 0, false), true, 0,
                        new CommandManager(new Command("setSelfSwitch", 1, "A"), new Command("changeItems", 1, 0, 2),
                                new Command("changeWeapons", 1, 0, 1),
                                new Command("showText",
                                        "Received Items:\nDummy Item x1\nShort Sword x1/pMehul Noob hai"))),
                new Page(2, "!Chest", 0, new Conditions(0, false, 0, false, 0, 0, false, "A", true, 0, false, 0, false),
                        true, 0, null))));

        entityManager.addEntity(new Event(handler, 2, 10, 5, Creature.DEFAULT_CREATURE_WIDTH,
                Creature.DEFAULT_CREATURE_HEIGHT,
                new PageList(new Page(0, "People1", 6,
                        new Conditions(0, false, 0, false, 0, 0, false, "A", false, 0, false, 0, false), false, 0,
                        new CommandManager(new Command("setSwitch", 0, true),
                                new Command("showText", "Talk to the Boy"), new Command("changeItems", 1, 1, 1),
                                new Command("changeGold", 0, 100), new Command("removeEvent", 2))))));

        entityManager
                .addEntity(
                        new Event(handler, 3, 12, 5, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT,
                                new PageList(
                                        new Page(0, "People1", 0,
                                                new Conditions(0, false, 0, false, 0, 0, false, "A", false, 0, false, 0,
                                                        false),
                                                false, 0, new CommandManager(new Command("showText", "Who Are You?"))),
                                        new Page(0, "People1", 0,
                                                new Conditions(0, true, 0, false, 0, 0, false, "A", false, 0, false, 0,
                                                        false),
                                                false, 0,
                                                new CommandManager(new Command("showText", "You Know my Grandpa"))))));

        // Portal Event
        entityManager.addEntity(new Event(handler, 4, 27, 2, Tile.TILEWIDTH, Tile.TILEHEIGHT,
                new PageList(new Page(0, "!Door2", 4,
                        new Conditions(0, false, 0, false, 0, 0, false, "A", false, 0, false, 0, false), true, 1,
                        new CommandManager(new Command("transferPlayer", 0, 1, 1))))));
        ((Event) entityManager.getEntities().get(4)).getCurrentPage().setPassable(true);

        entityManager.addEntity(new Event(handler, 5, 20, 6, Creature.DEFAULT_CREATURE_WIDTH,
                Creature.DEFAULT_CREATURE_HEIGHT,
                new PageList(new Page(0, "People1", 3,
                        new Conditions(0, false, 0, false, 0, 0, false, "A", false, 0, false, 0, false), false, 0,
                        new CommandManager(new Command("showChoices", "Yes/cNo",
                                new CommandManagerList(new CommandManager(new Command("showText", "Yes")),
                                        new CommandManager(new Command("showText", "No")))))))));

    }

    public void update() {
        entityManager.update();
    }

    public void render(Graphics g) {
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
        int xEnd = (int) Math.min(width,
                (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
        int yEnd = (int) Math.min(height,
                (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);

        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
                        (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
            }
        }
        entityManager.render(g);
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height)
            return Tile.grassTile;

        Tile t = Tile.tiles[tiles[x][y]];
        if (t == null) {
            return Tile.dirtTile;
        }
        return t;
    }

    private void loadMap(String path) {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");

        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        tiles = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
            }
        }
    }

    public int getSpawnX() {
        return spawnX;
    }

    public void setSpawnX(int spawnX) {
        this.spawnX = spawnX;
    }

    public int getSpawnY() {
        return spawnY;
    }

    public void setSpawnY(int spawnY) {
        this.spawnY = spawnY;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
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

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
