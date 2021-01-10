package src.inventory;

import src.Handler;
import src.items.usable.Item;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.Graphics;

public class Inventory {

    private Handler handler;
    private boolean active = false;
    private ArrayList<Item> items;

    public Inventory(Handler handler) {
        this.handler = handler;
        items = new ArrayList<Item>();
    }

    public void update() {
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_X))
            active = !active;
        if (!active)
            return;
    }

    public void render(Graphics g) {
        if (!active)
            return;
    }

    // Inventory Methods

    public boolean changeItem(Item item) {
        for (Item i : items) {
            if (i.getId() == item.getId()) {
                int q = i.getQuantity() + item.getQuantity();
                if (q < 0)
                    return false;
                else if (q == 0)
                    items.remove(i);
                i.setQuantity(q);
                return true;
            }
        }
        items.add(item);
        return true;
    }

    public void addItem(Item item) {
        for (Item i : items) {
            if (i.getId() == item.getId()) {
                i.setQuantity(i.getQuantity() + item.getQuantity());
                return;
            }
        }
        items.add(item);
    }

    public boolean removeItem(Item item) {
        for (Item i : items) {
            if (i.getId() == item.getId()) {
                int q = i.getQuantity() - item.getQuantity();
                if (q < 0)
                    return false;
                else if (q == 0)
                    items.remove(item);

                i.setQuantity(q);
                break;
            }
        }
        return true;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void print() {
        System.out.print("\nInventory: [ ");
        if (items.size() == 0)
            System.out.print("<Empty>  ");
        for (Item item : items) {
            System.out.print(item.getName() + " x" + item.getQuantity() + ", ");
        }
        System.out.println("\b\b ]");
    }

}
