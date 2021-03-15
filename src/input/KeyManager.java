package src.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;

import src.global.Global;
import src.global.Global.Direction;
import src.states.GameState;
import src.states.State;

public class KeyManager implements KeyListener {

    private boolean[] keys, justPressed, cantPress;
    public Direction playerDirection = Direction.DOWN;
    public boolean moving;

    // Direction Values
    private int keyVals[];
    private ArrayList<Integer> keyArr = new ArrayList<Integer>();

    public KeyManager() {
        keys = new boolean[256];
        justPressed = new boolean[keys.length];
        cantPress = new boolean[keys.length];

        moving = false;
        keyVals = new int[] { KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_UP };
    }

    public void update() {
        moving = false;
        if (State.getHandler().getMap() == null)
            playerDirection = Direction.DOWN;
        else
            playerDirection = State.getHandler().getMap().getEntityManager().getPlayer().getDirection();

        /*
         * zKey = keys[KeyEvent.VK_Z] || keys[KeyEvent.VK_ENTER];
         * 
         * xKey = keys[KeyEvent.VK_X] || keys[KeyEvent.VK_ESCAPE];
         */

        for (int i = 0; i < keys.length; i++) {
            if (cantPress[i] && !keys[i]) {
                cantPress[i] = false;
            } else if (justPressed[i]) {
                cantPress[i] = true;
                justPressed[i] = false;
            }
            if (!cantPress[i] && keys[i]) {
                justPressed[i] = true;
            }
        }

        // if (keyJustPressed(KeyEvent.VK_SHIFT)) {
        // System.out.println("Pressed SHIFT");
        // }
        // if (keyHeld(KeyEvent.VK_SHIFT)) {
        // System.out.println("SHIFT");
        // }

        ListIterator<Integer> li = keyArr.listIterator(keyArr.size());

        while (li.hasPrevious()) {
            for (int i = 0, j = li.previous(); i < keyVals.length; i++) {
                if (keyVals[i] == j) {
                    if (State.getState().getId() == GameState.STATE_ID)
                        playerDirection = Global.Direction.values()[i];
                    moving = true;
                    return;
                }
            }
        }

    }

    public boolean keyJustPressed(int keyCode) {
        if (keyCode < 0 || keyCode >= keys.length)
            return false;

        return justPressed[keyCode];
    }

    public boolean keyHeld(int keyCode) {
        return keys[keyCode];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
            return;

        if (!keyArr.contains(e.getKeyCode())) {

            // if (State.getState().getClass().getName() != GameState.class.getName())
            if (keyJustPressed(e.getKeyCode()))
                return;
            keys[e.getKeyCode()] = true;
            keyArr.add(e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
            return;

        if (keyArr.contains(e.getKeyCode())) {
            keys[e.getKeyCode()] = false;
            keyArr.remove(keyArr.indexOf(e.getKeyCode()));
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO
    }

    public int getArrowKey() {
        ListIterator<Integer> li = keyArr.listIterator(keyArr.size());

        while (li.hasPrevious()) {
            for (int i = 0, j = li.previous(); i < keyVals.length; i++) {
                if (keyVals[i] == j) {
                    if (keyJustPressed(j))
                        return i;
                    /*
                     * // Continuous Update removeAll(); return
                     * Global.Direction.values()[i].ordinal();
                     */
                }
            }
        }
        return -1;
    }

    public boolean emptyKeys() {
        return keyArr.size() == 0;
    }

    public void removeAll() {
        for (int i = 0; i < keys.length; i++) {
            keys[i] = false;
        }
        keyArr.removeAll(keyArr);
    }

}