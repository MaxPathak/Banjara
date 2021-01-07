package src.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.ListIterator;

import src.global.Global;

public class KeyManager implements KeyListener {

    private boolean[] keys;
    public Global.Direction direction = Global.Direction.DOWN;
    public boolean moving;
    public boolean interact;

    private int keyVals[];
    private ArrayList<Integer> keyArr = new ArrayList<Integer>();

    public KeyManager() {
        keys = new boolean[256];
        moving = false;
        interact = false;
        keyVals = new int[] { KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D };
    }

    public void update() {
        moving = false;

        interact = keys[KeyEvent.VK_Z];

        ListIterator<Integer> li = keyArr.listIterator(keyArr.size());

        while (li.hasPrevious()) {
            for (int i = 0, j = li.previous(); i < keyVals.length; i++) {
                if (keyVals[i] == j) {
                    direction = Global.Direction.values()[i];
                    moving = true;
                    return;
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!keyArr.contains(e.getKeyCode())) {
            keys[e.getKeyCode()] = true;
            keyArr.add(e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (keyArr.contains(e.getKeyCode())) {
            keys[e.getKeyCode()] = false;
            keyArr.remove(keyArr.indexOf(e.getKeyCode()));
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    public boolean isInteracting() {
        return interact;
    }

}