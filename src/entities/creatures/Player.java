package src.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import src.Handler;
import src.gfx.Animation;
import src.gfx.Assets;
import src.global.Global;

public class Player extends Creature {

    // Animations
    private Animation anim_down, anim_left, anim_right, anim_up;
    private Global.Direction direction = Global.Direction.DOWN;
    private boolean isSnapped = true;

    public Player(Handler handler, int x, int y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        bounds.width = (int) (width * 0.6) - 1;
        bounds.height = (int) (height * 0.4);
        bounds.x = (width - bounds.width) / 2;
        bounds.y = height - bounds.height;

        // Animations
        int animSpeed = 375;
        anim_down = new Animation(animSpeed, Assets.player_down);
        anim_left = new Animation(animSpeed, Assets.player_left);
        anim_right = new Animation(animSpeed, Assets.player_right);
        anim_up = new Animation(animSpeed, Assets.player_up);
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;

        direction = handler.getKeyManager().direction;

        if (handler.getKeyManager().moving) {
            switch (direction) {
                case UP:
                    yMove -= speed;
                    break;
                case DOWN:
                    yMove += speed;
                    break;
                case LEFT:
                    xMove -= speed;
                    break;
                case RIGHT:
                    xMove += speed;
                    break;
            }
        }
    }

    @Override
    public void update() {
        // Animations
        anim_down.update();
        anim_left.update();
        anim_right.update();
        anim_up.update();

        // Movement
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
    }

    @Override
    public void render(Graphics g) {

        g.drawImage(getCurrentAnimationFrame(), (int) (pX - bounds.x - handler.getGameCamera().getxOffset()),
                (int) (pY - bounds.y - handler.getGameCamera().getyOffset()), width, height, null);

        // g.setColor(Color.red); g.fillRect((int) ( x -
        // handler.getGameCamera().getxOffset()), (int) (y -
        // handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
    }

    private BufferedImage getCurrentAnimationFrame() {
        if (xMove < 0) {
            return anim_left.getCurrentFrame();
        } else if (xMove > 0) {
            return anim_right.getCurrentFrame();
        } else if (yMove < 0) {
            return anim_up.getCurrentFrame();
        } else if (yMove > 0) {
            return anim_down.getCurrentFrame();
        } else {
            switch (direction) {
                case DOWN:
                    anim_down.setIndex(0);
                    return anim_down.getCurrentFrame();
                case LEFT:
                    anim_left.setIndex(0);
                    return anim_left.getCurrentFrame();
                case RIGHT:
                    anim_right.setIndex(0);
                    return anim_right.getCurrentFrame();
                case UP:
                    anim_up.setIndex(0);
                    return anim_up.getCurrentFrame();
            }
        }
        anim_down.setIndex(0);
        return anim_down.getCurrentFrame();
    }

}
