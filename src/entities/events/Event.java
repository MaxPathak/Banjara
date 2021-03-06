package src.entities.events;

import java.awt.Graphics;

import src.Handler;
import src.entities.Entity;
import src.entities.creatures.Creature;
import src.global.Global;
import src.global.Global.Direction;

public class Event extends Entity {

    private Page currentPage;
    private String currentSelfSwitch;
    private Direction currentDirection;
    private char type;

    public Event() {
        super();
    }

    public Event(Handler handler, int id, int x, int y, int width, int height, PageList pages) {
        super(handler, id, x, y, width, height, pages);
        currentPage = pages.getPages().get(0);
        currentSelfSwitch = null;
        currentDirection = currentPage.getDirection();
    }

    public Event(Handler handler, int id, int x, int y, char type, PageList pages) {
        this(handler, id, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT, pages);
        this.type = type;
        if (type == 't' || type == 'T') {
            width = Creature.DEFAULT_CREATURE_WIDTH * 3 / 4;
            height = Creature.DEFAULT_CREATURE_HEIGHT * 3 / 4;
        }

        this.createBounds();
        this.adjustPosition();
    }

    @Override
    public void update() {
        if (this.pageList == null) {
            return;
        }
        for (Page page : pageList.getPages()) {
            // if (page.getConditions().isActorValid()) {}
            // if (page.getConditions().isItemValid()) {}
            if (page.getConditions().isSelfswitchValid())
                if (page.getConditions().getSelfSwitchCh() != currentSelfSwitch)
                    continue;
            if (page.getConditions().isSwitch1Valid())
                if (!Global.switches[page.getConditions().getSwitch1Id()])
                    continue;
            if (page.getConditions().isSwitch2Valid())
                if (!Global.switches[page.getConditions().getSwitch2Id()])
                    continue;
            if (page.getConditions().isVariableValid())
                if (Global.variables[page.getConditions().getVariableId()] != page.getConditions().getVariableValue())
                    continue;
            currentPage = page;
        }

    }

    @Override
    public void render(Graphics g) {
        if (currentPage.getAnimations() == null)
            return;
        int cDirection = currentDirection.ordinal();
        if (this.bounds == null) {
            return;
        }
        g.drawImage(currentPage.getAnimations()[cDirection].getCurrentFrame(),
                (int) (x - bounds.x - handler.getGameCamera().getxOffset()),
                (int) (y - bounds.y - handler.getGameCamera().getyOffset()), width, height, null);

    }

    @Override
    public void effect() {

        Direction oppDir = handler.getMap().getEntityManager().getPlayer().getOppositeDirection();
        // Passable temporary
        if (currentPage.isPassable()) {
            // nothings
        } else if (currentPage.isdFix()) {
            if (currentPage.getDirection() != oppDir)
                return;
        } else {
            currentDirection = oppDir;
        }

        if (pageList != null) {
            // System.out.println(this.id);
            if (currentPage.getList() != null)
                currentPage.getList().execute();
        }

    }

    public String getCurrentSelfSwitch() {
        return currentSelfSwitch;
    }

    public void setCurrentSelfSwitch(String currentSelfSwitch) {
        this.currentSelfSwitch = currentSelfSwitch;
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Page currentPage) {
        this.currentPage = currentPage;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    public char getType() {
        return type;
    }

}
