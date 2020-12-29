package src.maps;

import java.awt.Graphics;

import src.Handler;
import src.entities.EntityManager;
import src.entities.creatures.Player;
import src.entities.statics.Chest;
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
        entityManager = new EntityManager(handler, new Player(handler, 0, 0));
        entityManager.addEntity(new Chest(handler, 5 * Tile.TILEWIDTH, 5 * Tile.TILEHEIGHT));

        loadMap(path);

        entityManager.getPlayer().setX((float) spawnX * Tile.TILEWIDTH);
        entityManager.getPlayer().setY((float) spawnY * Tile.TILEHEIGHT);
    }

    public void update() {
        entityManager.update();
    }

    public void render(Graphics g) {
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);

        for(int y = yStart; y < yEnd; y++) {
            for(int x = xStart; x < xEnd; x++) {
                getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
                     (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
            }
        }
        entityManager.render(g);
    }

    public Tile getTile(int x, int y) {
        if(x < 0 || y < 0 || x >= width || y >= height)
            return Tile.grassTile;

        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null) {
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
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
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
