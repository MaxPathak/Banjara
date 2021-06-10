package src.entities;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

import src.Handler;
import src.entities.creatures.Player;
import src.entities.events.Event;

public class EntityManager implements Serializable {

    private Handler handler;
    private Player player;
    private ArrayList<Entity> entities;

    private static Comparator<Entity> renderSorter = new Comparator<Entity>() {
        @Override
        public int compare(Entity a, Entity b) {
            if (a.getY() + a.getHeight() < b.getY() + b.getHeight())
                return -1;
            return 1;
        }
    };

    public EntityManager(Handler handler, Player player) {
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<Entity>();
        addEntity(player);
    }

    public void update() {
        for (int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            e.update();
            if (e instanceof Event) {
                if (((Event) e).getType() == 't' || ((Event) e).getType() == 'T')
                    ((Event) e).setCurrentDirection(((Event) e).getCurrentPage().getDirection());
            }
        }
        entities.sort(renderSorter);
    }

    public void render(Graphics g) {
        for (Entity e : entities) {
            e.render(g);
        }
    }

    public void addEntity(Entity e) {
        entities.add(e);
    }

    // Getters and Setters

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    public Entity getEntityById(int id) {
        for (Entity entity : entities) {
            if (entity.getId() == id)
                return entity;
        }
        return null;
    }

}
