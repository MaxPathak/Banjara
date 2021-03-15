package src.states;

import java.awt.Graphics;

import src.Handler;
import src.ui.UIManager;
import src.utils.TimedEvent;

public abstract class State {

    private static State currentState = null;

    protected UIManager uiManager;
    protected TimedEvent timedEvent;
    protected static Handler handler;
    public int FOCUSED_ID;
    public int id;

    public static void setState(State state) {
        currentState = state;
    }

    public static State getState() {
        return currentState;
    }

    // Common methods

    public State() {
        uiManager = new UIManager(handler);
        timedEvent = new TimedEvent();
        FOCUSED_ID = 0;
    }

    public abstract void update();

    public abstract void render(Graphics g);

    public String getName() {
        return this.getClass().getName();
    }

    public static Handler getHandler() {
        return State.handler;
    }

    public static void setHandler(Handler handler) {
        State.handler = handler;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void changeState(State newState) {
        this.timedEvent.stopAll();
        handler.getTimedEvent().separate(this.timedEvent);
        State.setState(newState);
    }

    public void setFocused(int id) {
        uiManager.getObjects().get(id).setFocused(true);
    }

}
