package src;

import java.util.TimerTask;

import src.gfx.GameCamera;
import src.maps.Map;
import src.input.KeyManager;
import src.input.MouseManager;
import src.utils.TimedEvent;

public class Handler {

    private Game game;
    private Map map;
    private TimedEvent timedEvent;

    public Handler(Game game) {
        this.game = game;
        timedEvent = new TimedEvent();

        /*
         * timedEvent.add(new TimerTask() {
         * 
         * @Override public void run() { game.getKeyManager().getArrowKey(); } }, 0,
         * 170);
         */
        /*
         * timedEvent.add(new TimerTask() {
         * 
         * @Override public void run() { System.out.println("Handler 500"); } }, 0,
         * 500);
         */
    }

    public GameCamera getGameCamera() {
        return game.getGameCamera();
    }

    public KeyManager getKeyManager() {
        return game.getKeyManager();
    }

    public MouseManager getMouseManager() {
        return game.getMouseManager();
    }

    public int getWidth() {
        return game.getWidth();
    }

    public int getHeight() {
        return game.getHeight();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public TimedEvent getTimedEvent() {
        return timedEvent;
    }

    public void setTimedEvent(TimedEvent timedEvent) {
        this.timedEvent = timedEvent;
    }

}
