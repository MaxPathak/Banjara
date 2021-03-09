package src.inventory;

import src.Handler;
import src.global.Global;
import src.items.equip.Armor;
import src.items.equip.Weapon;
import src.items.usable.Item;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.Graphics;

public class Inventory {

    private Handler handler;
    private boolean active = false;
    private ArrayList<Item> items;
    private ArrayList<Weapon> weapons;
    private ArrayList<Armor> armors;

    public Inventory(Handler handler) {
        this.handler = handler;
        items = new ArrayList<Item>();
        weapons = new ArrayList<Weapon>();
        armors = new ArrayList<Armor>();
    }

    public void update() {
        if (handler.getKeyManager().keyJustPressed(Global.KEY_X)) {
            active = !active;
        }
        if (!active)
            return;
    }

    public void render(Graphics g) {
        if (!active)
            return;
    }

    // Inventory Methods

    public boolean changeItems(Item item) {
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

    public boolean changeWeapons(Weapon weapon) {
        for (Weapon w : weapons) {
            if (w.getId() == weapon.getId()) {
                int q = w.getQuantity() + weapon.getQuantity();
                if (q < 0)
                    return false;
                else if (q == 0)
                    weapons.remove(w);
                w.setQuantity(q);
                return true;
            }
        }
        weapons.add(weapon);
        return true;
    }

    public boolean changeArmors(Armor armor) {
        for (Armor a : armors) {
            if (a.getId() == armor.getId()) {
                int q = a.getQuantity() + armor.getQuantity();
                if (q < 0)
                    return false;
                else if (q == 0)
                    armors.remove(a);
                a.setQuantity(q);
                return true;
            }
        }
        armors.add(armor);
        return true;
    }

    /*
     * public void addItem(Item item) { for (Item i : items) { if (i.getId() ==
     * item.getId()) { i.setQuantity(i.getQuantity() + item.getQuantity()); return;
     * } } items.add(item); }
     */

    /*
     * public boolean removeItem(Item item) { for (Item i : items) { if (i.getId()
     * == item.getId()) { int q = i.getQuantity() - item.getQuantity(); if (q < 0)
     * return false; else if (q == 0) items.remove(item);
     * 
     * i.setQuantity(q); break; } } return true; }
     */

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
        System.out.println("\nInventory: ");
        System.out.print("Items: [ ");
        if (items.size() == 0)
            System.out.print("<Empty>  ");
        else
            for (Item item : items) {
                System.out.print(item.getName() + " x" + item.getQuantity() + ", ");
            }
        System.out.println("\b\b ]");
        System.out.print("Weapons: [ ");
        if (weapons.size() == 0)
            System.out.print("<Empty>  ");
        else
            for (Weapon weapon : weapons) {
                System.out.print(weapon.getName() + " x" + weapon.getQuantity() + ", ");
            }
        System.out.println("\b\b ]");
        System.out.print("Armors: [ ");
        if (armors.size() == 0)
            System.out.print("<Empty>  ");
        else
            for (Armor armor : armors) {
                System.out.print(armor.getName() + " x" + armor.getQuantity() + ", ");
            }
        System.out.println("\b\b ]");
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
