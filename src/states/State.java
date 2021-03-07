package src.states;

import java.awt.Graphics;

import src.Handler;

public abstract class State {

    private static State currentState = null;

    protected static Handler handler;

    public static void setState(State state) {
        currentState = state;
    }

    public static State getState() {
        return currentState;
    }

    // Common methods

    public State() {
        // TODO
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

}
