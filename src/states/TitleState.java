package src.states;

import java.awt.Graphics;
import java.util.TimerTask;

import src.gfx.Assets;
import src.global.Global;
import src.global.Global.Direction;
import src.ui.ClickListener;
import src.ui.UIObject;
import src.ui.UITextButton;

public class TitleState extends State {

    public static final int STATE_ID = 0;

    public TitleState() {
        super();
        setId(TitleState.STATE_ID);
        // uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);

        /*
         * // Continuous Update timedEvent.add(new TimerTask() {
         * 
         * @Override public void run() {
         * handler.getGame().getKeyManager().getArrowKey(); } }, 0, 170);
         */
        /*
         * timedEvent.add(new TimerTask() {
         * 
         * @Override public void run() { System.out.println("MenuState 500"); } }, 0,
         * 500);
         */

        timedEvent.add(new TimerTask() {
            @Override
            public void run() {
                if (uiManager.getFocusedObject() != null) {
                    uiManager.getFocusedObject().toggleBlinking();
                }
            }
        }, 0, 400);

        handler.getTimedEvent().merge(timedEvent);

        int buttonWidth = 150, buttonHeight = (int) (Global.DEFAULT_FONTSIZE + 10);

        // New Game
        uiManager.addObject(new UITextButton("New Game", handler.getWidth() / 2 - buttonWidth / 2, 350, buttonWidth,
                buttonHeight, true, 3, new ClickListener() {
                    @Override
                    public void onClick() {
                        // handler.getMouseManager().setUIManager(null);
                        // System.out.println("Stopped Menu State Events");
                        handler.getGame().gameState = new GameState();
                        changeState(handler.getGame().gameState);
                        handler.getKeyManager().playerDirection = Direction.DOWN;
                    }
                }));
        // Continue
        uiManager.addObject(new UITextButton("Continue", handler.getWidth() / 2 - buttonWidth / 2, 400, buttonWidth,
                buttonHeight, true, 3, new ClickListener() {
                    @Override
                    public void onClick() {
                        // TODO
                    }
                }));
        // Options
        uiManager.addObject(new UITextButton("Options", handler.getWidth() / 2 - buttonWidth / 2, 450, buttonWidth,
                buttonHeight, true, 3, new ClickListener() {
                    @Override
                    public void onClick() {
                        // TODO
                    }
                }));
        // Exit
        uiManager.addObject(new UITextButton("Exit", handler.getWidth() / 2 - buttonWidth / 2, 500, buttonWidth,
                buttonHeight, true, 3, new ClickListener() {
                    @Override
                    public void onClick() {
                        System.exit(0);
                    }
                }));

        setFocused(FOCUSED_ID);

    }

    @Override
    public void update() {
        /*
         * if(handler.getMouseManager().isLeftPressed() &&
         * handler.getMouseManager().isRightPressed()) {
         * State.setState(handler.getGame().gameState); }
         */

        /*
         * // Reduce how many times update and render are called now =
         * System.nanoTime(); delta += (now - lastTime) / timePerTick /
         * ((handler.getKeyManager().emptyKeys()) ? 1 : 5); timer += now - lastTime;
         * lastTime = now;
         * 
         * if (delta >= 0.1) { getInput(); delta--; }
         * 
         * if (timer >= 1000000000) { timer = 0; }
         */

        getInput();

        uiManager.update();
    }

    @Override
    public void render(Graphics g) {
        // Title Screen
        g.drawImage(Assets.title, 0, 0, handler.getWidth(), handler.getHeight(), null);

        // Text.drawString(g, "Title", 0, 0, false, Color.white,
        // Assets.regularFont.get(24));

        uiManager.render(g);
        // g.setColor(Color.white);
        // g.fillRect(handler.getMouseManager().getMouseX(),
        // handler.getMouseManager().getMouseY(), 8, 8);
    }

    public void getInput() {
        if (handler.getKeyManager().keyJustPressed(Global.KEY_Z)) {
            UIObject o = uiManager.getFocusedObject();
            if (o != null)
                o.onClick();
        }
        int i = handler.getKeyManager().getArrowKey();
        // System.out.println(i);
        if (i == -1)
            return;

        Direction direction = Direction.values()[i];

        final int size = uiManager.getObjects().size();
        for (i = 0; i < size; i++) {
            if (uiManager.getObjects().get(i).isFocused())
                break;
        }

        if (i == size) {
            return;
        }

        switch (direction) {
        case UP:
            if (i > 0) {
                // System.out.println("UP");
                uiManager.getObjects().get(i).setFocused(false);
                uiManager.getObjects().get(i - 1).setFocused(true);
            }
            break;
        case DOWN:
            if (i < size - 1) {
                // System.out.println("DOWN");
                uiManager.getObjects().get(i).setFocused(false);
                uiManager.getObjects().get(i + 1).setFocused(true);
            }
            break;
        case LEFT:
            break;
        case RIGHT:
            break;
        }
    }

}
