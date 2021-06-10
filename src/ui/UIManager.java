package src.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;

import src.Handler;

public class UIManager implements Serializable {

    private Handler handler;
    private ArrayList<UIObject> objects;

    public UIManager(Handler handler) {
        this.handler = handler;
        objects = new ArrayList<UIObject>();
    }

    public void update() {
        for (UIObject o : objects)
            o.update();
    }

    public void render(Graphics g) {
        for (UIObject o : objects)
            o.render(g);
    }

    public void onMouseMove(MouseEvent e) {
        for (UIObject o : objects)
            o.onMouseMove(e);
    }

    public void onMouseRelease(MouseEvent e) {
        for (UIObject o : objects) {
            o.onMouseRelease(e);
            if (o.isFocused()) {
                unfocusAll();
                o.setFocused(true);
            }
        }
    }

    public void addObject(UIObject o) {
        objects.add(o);
    }

    public void removeObject(UIObject o) {
        objects.remove(o);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ArrayList<UIObject> getObjects() {
        return objects;
    }

    public void setObjects(ArrayList<UIObject> objects) {
        this.objects = objects;
    }

    public void unfocusAll() {
        for (UIObject o : objects) {
            o.setFocused(false);
        }
    }

    public UIObject getFocusedObject() {
        for (UIObject o : objects) {
            if (o.isFocused())
                return o;
        }
        return null;
    }

}
