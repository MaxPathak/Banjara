package src.maps;

import java.awt.Color;
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
import src.entities.creatures.Player;
import src.entities.events.Event;
import src.gfx.Assets;
import src.gfx.Text;
import src.tiles.Layer;
import src.tiles.Tile;
import src.tiles.TileSet;

public class Map {

    private String name;
    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private EntityManager entityManager;

    public int regionIndex;
    public int regionLayer;
    public ArrayList<TileSet> tileSets;
    public ArrayList<Layer> layers;

    public Map(Handler handler, String name, int width, int height, int spawnX, int spawnY) {
        this.handler = handler;
        this.name = name;
        this.width = width;
        this.height = height;
        this.spawnX = spawnX;
        this.spawnY = spawnY;

        this.tileSets = new ArrayList<TileSet>();
        this.layers = new ArrayList<Layer>();

        entityManager = new EntityManager(handler, new Player(handler, spawnX, spawnY, null));

        // Temporary
        addEvents();

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

        for (int i = 0; i < layers.size(); i++) {
            if (i == layers.size() - 1) {
                entityManager.render(g);
                // break; // Hide
            }
            if (layers.get(i).isRegionLayer())
                continue;
            for (int y = yStart; y < yEnd; y++) {
                for (int x = xStart; x < xEnd; x++) {
                    if (layers.get(i).data[x][y] != 0)
                        for (TileSet tileSet : tileSets) {
                            if (layers.get(i).data[x][y] < tileSet.getFirstIndex() + tileSet.getTileCount()) {
                                int xPos = (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset());
                                int yPos = (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset());
                                g.drawImage(tileSet.crop(layers.get(i).data[x][y]), xPos, yPos, Tile.TILEWIDTH,
                                        Tile.TILEHEIGHT, null);
                                break;
                            }
                        }
                }
            }
        }

        // int x = handler.getMap().getEntityManager().getPlayer().getGridX(),
        // y = handler.getMap().getEntityManager().getPlayer().getGridY();
        // String str = String.format("%d, %d", x, y);
        // Text.drawString(g, str, handler.getWidth() -
        // g.getFontMetrics(Assets.regularFont.get(20)).stringWidth(str),
        // handler.getHeight() -
        // g.getFontMetrics(Assets.regularFont.get(20)).getAscent(), true, Color.white,
        // Assets.regularFont.get(20));
    }

    public void addEvents() {
        if (this.name.equals("map01")) {
            // Chest Event
            entityManager.addEntity(new Event(handler, 1, 1, 4, 'T',
                    new PageList(
                            new Page(0, "!Chest", 0,
                                    new Conditions(0, false, 0, false, 0, 0, false, "A", false, 0, false, 0, false),
                                    true, 0, false,
                                    new CommandManager(new Command("setSelfSwitch", 1, "A"),
                                            new Command("changeItems", 1, 0, 2), new Command("changeWeapons", 1, 0, 1),
                                            new Command("showText",
                                                    "Received Items:\nDummy Item x1\nShort Sword x1/pTata"))),
                            new Page(2, "!Chest", 0,
                                    new Conditions(0, false, 0, false, 0, 0, false, "A", true, 0, false, 0, false),
                                    true, 0, false, null))));

            // Link Event B
            entityManager.addEntity(new Event(handler, 2, 18, 11, 'C',
                    new PageList(
                            new Page(3, "People1", 0,
                                    new Conditions(0, false, 0, false, 0, 0, false, "A", false, 0, false, 0, false),
                                    false, 0, false, new CommandManager(new Command("showText", "Who Are You?"))),
                            new Page(3, "People1", 0,
                                    new Conditions(0, true, 0, false, 0, 0, false, "A", false, 0, false, 0, false),
                                    false, 0, false,
                                    new CommandManager(
                                            new Command("showText",
                                                    "You Know my Grandpa\nHere, Have Some Gold/pReceived:\n100 Gold"),
                                            new Command("changeGold", 0, 100), new Command("setSelfSwitch", 2, "A"))),
                            new Page(3, "People1", 0,
                                    new Conditions(0, false, 0, false, 0, 0, false, "A", true, 0, false, 0, false),
                                    false, 0, false, new CommandManager(new Command("showText", "Hi"))))));

            // Portal Event
            entityManager.addEntity(new Event(handler, 3, 28, 8, 'T',
                    new PageList(new Page(0, "!Door2", 4,
                            new Conditions(0, false, 0, false, 0, 0, false, "A", false, 0, false, 0, false), true, 1,
                            true, new CommandManager(new Command("transferPlayer", 0, 2, 7, null))))));

            entityManager.addEntity(new Event(handler, 4, 28, 10, 'T',
                    new PageList(new Page(0, "!Door2", 4,
                            new Conditions(0, false, 0, false, 0, 0, false, "A", false, 0, false, 0, false), true, 1,
                            true, new CommandManager(new Command("transferPlayer", 0, 2, 7, null))))));

            // Choice Event
            entityManager.addEntity(new Event(handler, 5, 26, 5, 'C', new PageList(new Page(0, "People1", 3,
                    new Conditions(0, false, 0, false, 0, 0, false, "A", false, 0, false, 0, false), false, 0, false,
                    new CommandManager(new Command("showChoices", "Yes/cNo",
                            new CommandManagerList(new CommandManager(new Command("showText", "Yes")),
                                    new CommandManager(new Command("showText", "No")))))))));

            // Remove Event
            entityManager.addEntity(new Event(handler, 6, 19, 1, 'C', new PageList(new Page(0, "People1", 1,
                    new Conditions(0, false, 0, false, 0, 0, false, "A", false, 0, false, 0, false), false, 0, false,
                    new CommandManager(new Command("showText", "Im Going"), new Command("removeEvent", 6))))));

            // Enter House
            entityManager.addEntity(new Event(handler, 7, 5, 2, 'T',
                    new PageList(new Page(0, null, 0,
                            new Conditions(0, false, 0, false, 0, 0, false, "A", false, 0, false, 0, false), true, 1,
                            true, new CommandManager(new Command("transferPlayer", 0, 13, 18, "house01"))))));
        } else if (this.name.equals("house01")) {

            // Exit House
            entityManager.addEntity(new Event(handler, 1, 13, 19, 'T',
                    new PageList(new Page(0, null, 0,
                            new Conditions(0, false, 0, false, 0, 0, false, "A", false, 0, false, 0, false), true, 1,
                            true, new CommandManager(new Command("transferPlayer", 0, 5, 4, "map01"))))));

            // Link Event A
            entityManager.addEntity(new Event(handler, 2, 23, 6, 'C',
                    new PageList(new Page(3, "People1", 6,
                            new Conditions(0, false, 0, false, 0, 0, false, "A", false, 0, false, 0, false), false, 0,
                            false, new CommandManager(new Command("setSwitch", 2, true),
                                    new Command("showText", "Talk to the Boy"))))));

            // Trade Event
            entityManager.addEntity(new Event(handler, 3, 4, 6, 'C', new PageList(new Page(0, "People1", 2,
                    new Conditions(0, false, 0, false, 0, 0, false, "A", false, 0, false, 0, false), false, 0, false,
                    new CommandManager(new Command("showText", "Give me Dummy Item x1 and I will give you 3"),
                            new Command("changeItems", 1, 1, 1), new Command("changeItems", 1, 0, 3),
                            new Command("showText", "Removed:\nDummy Item x1/pAdded:\nDummy Item x3"))))));

        }
    }

    // public Tile getTile(int x, int y) {
    // if (x < 0 || y < 0 || x >= width || y >= height)
    // return Tile.grassTile;

    // Tile t = Tile.tiles[tiles[x][y]];
    // if (t == null) {
    // return Tile.dirtTile;
    // }
    // return t;
    // }

    // private void loadMap(String path) {
    // String file = Utils.loadFileAsString(path);
    // String[] tokens = file.split("\\s+");

    // width = Utils.parseInt(tokens[0]);
    // height = Utils.parseInt(tokens[1]);
    // spawnX = Utils.parseInt(tokens[2]);
    // spawnY = Utils.parseInt(tokens[3]);

    // tiles = new int[width][height];
    // for (int y = 0; y < height; y++) {
    // for (int x = 0; x < width; x++) {
    // tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
    // }
    // }
    // }

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

    public String getName() {
        return name;
    }

}
