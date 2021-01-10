package src.states;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ListIterator;
import java.util.TimerTask;

import src.Handler;
import src.gfx.Assets;
import src.global.Global.Direction;
import src.ui.ClickListener;
import src.ui.UIImageButton;
import src.ui.UIManager;
import src.ui.UIObject;
import src.utils.TimedEvent;

public class MenuState extends State {

    private UIManager uiManager;
    private TimedEvent timedEvent;

    public MenuState() {
        super();
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);

        timedEvent = new TimedEvent();
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
        }, 0, 200);

        handler.getTimedEvent().merge(timedEvent);

        final int totalButtons = 3;
        int buttonIndex = 0;

        int totalHeight = 50 * (int) (totalButtons + totalButtons / 2);
        // Start
        uiManager.addObject(new UIImageButton(handler.getWidth() / 2 - 110 / 2,
                handler.getHeight() / 2 - totalHeight / 2 + 50 * buttonIndex + 25 * buttonIndex++, 110, 50,
                Assets.start_btn, new ClickListener() {
                    @Override
                    public void onClick() {
                        handler.getMouseManager().setUIManager(null);
                        timedEvent.stopAll();
                        // System.out.println("Stopped Menu State Events");
                        handler.getTimedEvent().separate(timedEvent);
                        State.setState(handler.getGame().gameState);
                        handler.getKeyManager().playerDirection = Direction.DOWN;
                    }
                }));
        // Options
        uiManager.addObject(new UIImageButton(handler.getWidth() / 2 - 110 / 2,
                handler.getHeight() / 2 - totalHeight / 2 + 50 * buttonIndex + 25 * buttonIndex++, 110, 50,
                Assets.start_btn, new ClickListener() {
                    @Override
                    public void onClick() {
                        // TODO
                    }
                }));

        // Exit
        uiManager.addObject(new UIImageButton(handler.getWidth() / 2 - 110 / 2,
                handler.getHeight() / 2 - totalHeight / 2 + 50 * buttonIndex + 25 * buttonIndex++, 110, 50,
                Assets.start_btn, new ClickListener() {
                    @Override
                    public void onClick() {
                        System.exit(0);
                    }
                }));

        uiManager.getObjects().get(0).setFocused(true);
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

        uiManager.render(g);
        // g.setColor(Color.white);
        // g.fillRect(handler.getMouseManager().getMouseX(),
        // handler.getMouseManager().getMouseY(), 8, 8);
    }

    public void getInput() {
        if (handler.getKeyManager().isInteracting()) {
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
