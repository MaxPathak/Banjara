package src.entities.creatures;

import src.Game;
import src.gfx.Assets;

import java.awt.Graphics;

public class Player extends Creature {

    public Player(Game game, float x, float y) {
        super(game, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;

        if(game.getKeyManager().moving) {
            switch (game.getKeyManager().direction) {
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
        g.drawImage(Assets.player, (int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), width, height, null);
    }

    @Override
    public void update() {
        getInput();
        move();
        game.getGameCamera().centerOnEntity(this);
    }
    
}
