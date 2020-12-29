package src.states;

import java.awt.Color;
import java.awt.Graphics;

import src.Handler;
import src.gfx.Assets;
import src.ui.ClickListener;
import src.ui.UIImageButton;
import src.ui.UIManager;

public class MenuState extends State {

    private UIManager uiManager;

    public MenuState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);

        uiManager.addObject(new UIImageButton(handler.getWidth()/2 - 110/2, handler.getHeight()/2 - 50/2, 110, 50, Assets.start_btn, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(null);
                State.setState(handler.getGame().gameState);
            }
        }));
    }
    
    @Override
    public void update() {
        /*if(handler.getMouseManager().isLeftPressed() && handler.getMouseManager().isRightPressed()) {
            State.setState(handler.getGame().gameState);
        }*/

        uiManager.update();
    }

    @Override
    public void render(Graphics g) {
        //Title Screen
        g.drawImage(Assets.title, 0, 0, handler.getWidth(), handler.getHeight(), null);

        uiManager.render(g);
        //g.setColor(Color.white);
        //g.fillRect(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY(), 8, 8);
    }

}
