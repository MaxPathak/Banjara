package src.states.scenes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.TimerTask;

import src.commands.CommandManager;
import src.commands.CommandManagerList;
import src.global.Global;
import src.global.Global.Direction;
import src.states.State;
import src.ui.ClickListener;
import src.ui.UIObject;
import src.ui.UITextButton;

public class ChoiceScene extends State {

    public static final int STATE_ID = 12;

    private ArrayList<String> choices;

    private static final int DEFAULT_X, DEFAULT_Y;
    private int x, y;
    private int currentChoice;
    private boolean blink;

    private ArrayList<CommandManager> commandManagers;

    private Rectangle container;

    static {
        DEFAULT_X = 700;
        DEFAULT_Y = handler.getHeight() / 2 - 150 / 2;
    }

    public ChoiceScene(String text, CommandManagerList list) {
        super();
        setId(ChoiceScene.STATE_ID);
        choices = new ArrayList<String>();
        this.commandManagers = new ArrayList<CommandManager>();
        for (CommandManager cm : list.getManagers()) {
            this.commandManagers.add(cm);
        }
        for (String string : text.split("/c")) {
            choices.add(string);
        }
        currentChoice = 0;
        blink = false;

        handler.getMouseManager().setUIManager(uiManager);

        x = DEFAULT_X;
        y = DEFAULT_Y;

        timedEvent.add(new TimerTask() {
            @Override
            public void run() {
                blink = !blink;
            }
        }, 0, 400);

        handler.getTimedEvent().merge(timedEvent);

        int buttonIndex = 0;
        int buttonWidth = 200 - 2, buttonHeight = (int) (Global.DEFAULT_FONTSIZE + 10);

        container = new Rectangle(DEFAULT_X, DEFAULT_Y, buttonWidth, buttonHeight * choices.size() + 2);

        for (String choice : choices) {

            uiManager.addObject(new UITextButton(choice, x, y + buttonHeight * buttonIndex++, buttonWidth, buttonHeight,
                    false, 3, new ClickListener() {
                        @Override
                        public void onClick() {
                            commandManagers.get(currentChoice).execute();
                        }
                    }));
        }

        setFocused(FOCUSED_ID);

    }

    @Override
    public void update() {
        getInput();
        uiManager.update();
    }

    @Override
    public void render(Graphics g) {
        handler.getGame().gameState.render(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Global.rgba(0, 0, 102, .9f));
        g2.fillRect(container.x, container.y, container.width, container.height);

        uiManager.render(g);

        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.white);
        g2.drawRect(container.x, container.y, container.width, container.height);

    }

    public void getInput() {
        if (handler.getKeyManager().keyJustPressed(Global.KEY_Z)) {
            UIObject o = uiManager.getFocusedObject();
            if (o != null) {
                o.onClick();
                return;
            }
        }
        
        int i = handler.getKeyManager().getArrowKey();
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
                currentChoice--;
                uiManager.getObjects().get(i).setFocused(false);
                uiManager.getObjects().get(i - 1).setFocused(true);
            }
            break;
        case DOWN:
            if (i < size - 1) {
                currentChoice++;
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
