package src.states;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.ArrayList;

import src.gfx.Assets;
import src.gfx.Text;
import src.global.Global;
import src.global.Global.Direction;
import src.items.BaseItem;
import src.ui.ClickListener;
import src.ui.UIObject;
import src.ui.UITextButton;

public class MenuState extends State {

    public static final int STATE_ID = 2;

    private ArrayList<Rectangle> containers;

    public MenuState() {
        super();
        setId(MenuState.STATE_ID);
        // uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);

        System.out.println("Direction: " + handler.getMap().getEntityManager().getPlayer().getDirection());

        containers = new ArrayList<Rectangle>();

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

        // timedEvent.add(new TimerTask() {
        // @Override
        // public void run() {
        // if (uiManager.getFocusedObject() != null) {
        // uiManager.getFocusedObject().toggleBlinking();
        // }
        // }
        // }, 0, 200);

        handler.getTimedEvent().merge(timedEvent);

        final int totalButtons = 3;
        int buttonIndex = 0;
        int buttonWidth = 200, buttonHeight = (int) (Global.DEFAULT_FONTSIZE + 10);

        int totalHeight = 50 * (int) (totalButtons + totalButtons / 2);
        // Start
        uiManager.addObject(new UITextButton("Inventory", 3, 3 + buttonHeight * buttonIndex++, buttonWidth,
                buttonHeight, false, 3, new ClickListener() {
                    @Override
                    public void onClick() {
                        // TODO
                    }
                }));
        // Options
        uiManager.addObject(new UITextButton("Options", 3, 3 + buttonHeight * buttonIndex++, buttonWidth, buttonHeight,
                false, 3, new ClickListener() {
                    @Override
                    public void onClick() {
                        // TODO
                    }
                }));
        // Title Screen
        uiManager.addObject(new UITextButton("To Title Screen", 3, 3 + buttonHeight * buttonIndex++, buttonWidth,
                buttonHeight, false, 3, new ClickListener() {
                    @Override
                    public void onClick() {
                        changeState(new TitleState());
                    }
                }));
        // Exit
        uiManager.addObject(new UITextButton("Exit", 3, 3 + buttonHeight * buttonIndex++, buttonWidth, buttonHeight,
                false, 3, new ClickListener() {
                    @Override
                    public void onClick() {
                        changeState(handler.getGame().gameState);
                        // System.out.println("New Direction: " + handler.getGame().gameState);
                    }
                }));

        // uiManager.getObjects().get(0).setFocused(true);
        setFocused(FOCUSED_ID);

        containers.add(new Rectangle(1, 1, buttonWidth, buttonHeight * uiManager.getObjects().size()));
        containers
                .add(new Rectangle(1 + buttonWidth, 1, handler.getWidth() - 3 - buttonWidth, handler.getHeight() - 3));
        containers.add(new Rectangle(1, handler.getHeight() - buttonHeight - 2, buttonWidth, buttonHeight));
        // containers.add(new Rectangle(1, 1, handler.getWidth() - 3,
        // handler.getHeight() - 3));

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
        // g.drawImage(Assets.title, 0, 0, handler.getWidth(), handler.getHeight(),
        // null);

        handler.getGame().gameState.render(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        // GradientPaint blueToBlack = new GradientPaint(0, 0, Color.BLUE, 400, 400,
        // Color.BLACK);
        // g2.setPaint(blueToBlack);
        g2.setColor(Global.rgba(0, 0, 0, .9f));
        g2.fillRect(0, 0, handler.getWidth(), handler.getHeight());

        g2.setStroke(new BasicStroke(3));

        ArrayList<BaseItem> items = new ArrayList<BaseItem>();
        items = handler.getMap().getEntityManager().getPlayer().getInventory().getAllItems();

        int i = 0;
        for (BaseItem baseItem : items) {
            Text.drawString(g, baseItem.getName(), containers.get(1).x + 5, containers.get(1).y + 5 + 34 * i++, false,
                    Color.white, Assets.regularFont.get(20));
        }

        Text.drawString(g, "Gold: " + 10000, containers.get(2).x + 5, containers.get(2).y + 5, false, Color.white,
                Assets.regularFont.get(20));

        uiManager.render(g);

        // Borders
        g2.setColor(Color.white);
        for (Rectangle c : containers) {
            g2.drawRect(c.x, c.y, c.width, c.height);
        }

        State temp = new TitleState();
        temp.render(g);

    }

    public void getInput() {
        if (handler.getKeyManager().keyJustPressed(Global.KEY_Z)) {
            UIObject o = uiManager.getFocusedObject();
            if (o != null) {
                o.onClick();
                return;
            }
        } else if (handler.getKeyManager().keyJustPressed(Global.KEY_X)) {
            changeState(handler.getGame().gameState);
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

        FOCUSED_ID = i;

    }

}
