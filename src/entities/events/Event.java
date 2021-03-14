package src.entities.events;

import java.awt.Graphics;

import src.Handler;
import src.entities.Entity;
import src.entities.Page;
import src.entities.PageList;
import src.global.Global;
import src.global.Global.Direction;
import src.tiles.Tile;

public class Event extends Entity {

    private int id;
    private Page currentPage;
    private String currentSelfSwitch;

    public Event(Handler handler, int id, int x, int y, PageList pages) {
        super(handler, id, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT, pages);
        currentPage = pages.getPages().get(0);
        currentSelfSwitch = null;
        this.id = id;
    }

    @Override
    public void update() {
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
        int cDirection = currentPage.getDirection().ordinal();
        g.drawImage(currentPage.getAnimations()[cDirection].getCurrentFrame(),
                (int) (x - bounds.x - handler.getGameCamera().getxOffset()),
                (int) (y - bounds.y - handler.getGameCamera().getyOffset()), width, height, null);

    }

    @Override
    public void effect() {

        Direction oppDir = handler.getMap().getEntityManager().getPlayer().getOppositeDirection();

        if (currentPage.isdFix()) {
            if (currentPage.getDirection() != oppDir)
                return;
        } else {
            currentPage.setDirection(oppDir);
        }

        if (pageList != null) {
            currentPage.getList().execute();
        }

    }

    public String getCurrentSelfSwitch() {
        return currentSelfSwitch;
    }

    public void setCurrentSelfSwitch(String currentSelfSwitch) {
        this.currentSelfSwitch = currentSelfSwitch;
    }

}
