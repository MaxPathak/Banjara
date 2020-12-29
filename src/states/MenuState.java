package src.states;

import java.awt.Color;
import java.awt.Graphics;

import src.Handler;

public class MenuState extends State {

    public MenuState(Handler handler) {
        super(handler);
    }
    
    @Override
    public void update() {
        if(handler.getMouseManager().isLeftPressed() && handler.getMouseManager().isRightPressed()) {
            State.setState(handler.getGame().gameState);
        }

    }

    @Override
    public void render(Graphics g) {
        //g.setColor(Color.white);
        //g.fillRect(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY(), 8, 8);
    }

}
