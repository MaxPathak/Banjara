package src.states.scenes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.TimerTask;

import src.gfx.Assets;
import src.gfx.Text;
import src.global.Global;
import src.states.State;

public class TextScene extends State {

    public static final int STATE_ID = 11;

    private ArrayList<String> pages;

    private static final int DEFAULT_X, DEFAULT_Y;
    private int x, y;
    private int currentPage;
    private boolean blink;

    private Rectangle container;

    static {
        DEFAULT_X = 1;
        DEFAULT_Y = handler.getHeight() - 150 - 2;
    }

    public TextScene(String text) {
        super();
        setId(TextScene.STATE_ID);
        pages = new ArrayList<String>();
        for (String string : text.split("/p")) {
            pages.add(string);
        }
        currentPage = 0;
        blink = false;

        // handler.getMouseManager().setUIManager(uiManager);

        x = DEFAULT_X + 5;
        y = DEFAULT_Y + 5;

        timedEvent.add(new TimerTask() {
            @Override
            public void run() {
                blink = !blink;
            }
        }, 0, 400);

        handler.getTimedEvent().merge(timedEvent);

        container = new Rectangle(DEFAULT_X, DEFAULT_Y, handler.getWidth() - 3, 150);

    }

    @Override
    public void update() {
        if (handler.getKeyManager().keyJustPressed(Global.KEY_Z)) {
            if (currentPage < pages.size() - 1)
                currentPage++;
            else
                changeState(handler.getGame().gameState);
        } else if (handler.getKeyManager().keyJustPressed(Global.KEY_X)) {
            if (currentPage > 0)
                currentPage--;
        }
    }

    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub

        handler.getGame().gameState.render(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Global.rgba(0, 0, 102, .9f));
        g2.fillRect(container.x, container.y, container.width, container.height);

        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.white);
        g2.drawRect(container.x, container.y, container.width, container.height);

        int line = 0;
        for (String text : pages.get(currentPage).split("\n")) {
            Text.drawString(g, text, x, y + line * g.getFontMetrics(Assets.regularFont.get(20)).getAscent(), false,
                    Color.white, Assets.regularFont.get(20));
            line++;
        }

        // Text.drawString(g, "test", 100, 100, false, Color.white,
        // Assets.regularFont.get(30));

        int w = g.getFontMetrics(Assets.regularFont.get(30)).charWidth('<');
        int h = g.getFontMetrics(Assets.regularFont.get(30)).getHeight();

        Color color = blink ? Color.white : Global.rgba(0, 0, 102, .9f);

        if (currentPage > 0)
            Text.drawString(g, "<", w, handler.getHeight() - h, true, false, color, Assets.regularFont.get(30));

        if (currentPage < pages.size() - 1)
            Text.drawString(g, ">", handler.getWidth() - w, handler.getHeight() - h, true, false, color,
                    Assets.regularFont.get(30));

    }

}
