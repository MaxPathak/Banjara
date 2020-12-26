package src.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;

import src.Handler;
import src.gfx.Assets;

public class Player extends Creature {

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        bounds.width = 40;
        bounds.height = 40;
        bounds.x = 4;
        bounds.y = 8;
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;

        if(handler.getKeyManager().moving) {
            switch (handler.getKeyManager().direction) {
                case UP:    yMove -= speed;
                    break;
                case DOWN:  yMove += speed;
                    break;
                case LEFT:  xMove -= speed;
                    break;
                case RIGHT: xMove += speed;
                    break;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        //g.drawImage(Assets.player, (int) (x - bounds.x - handler.getGameCamera().getxOffset()), (int) (y - bounds.y - handler.getGameCamera().getyOffset()), width, height, null);
        
        /*g.setColor(Color.white);
        //g.drawRect((int) (x - 2*bounds.x - handler.getGameCamera().getxOffset()), (int) (y - 2*bounds.y - handler.getGameCamera().getyOffset()), width + 2*bounds.x, height + bounds.y);
        g.drawRect((int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset() + bounds.height), 32, 32);
        g.drawRect((int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset() - bounds.height), 32, 32);
        g.drawRect((int) (x - bounds.x - handler.getGameCamera().getxOffset()), (int) (y - bounds.y - handler.getGameCamera().getyOffset()), width, height);*/
        g.setColor(Color.red);
        g.fillRect((int) ( x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
        g.drawImage(Assets.player, (int) (x - bounds.x - handler.getGameCamera().getxOffset()), (int) (y - bounds.y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    @Override
    public void update() {
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
    }
    
}
