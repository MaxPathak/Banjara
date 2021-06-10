package src.inventory;

import java.io.Serializable;
import java.util.ArrayList;

import src.Handler;
import src.items.BaseItem;
import src.items.equip.Armor;
import src.items.equip.Weapon;
import src.items.usable.Item;

public class Inventory implements Serializable {

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

    // Inventory Methods

    public boolean changeItems(Item item) {
        int q = 0;
        int index = 0;

        for (Item i : items) {
            if (i.getId() == item.getId()) {
                q = i.getQuantity() + item.getQuantity();
                if (q < 0)
                    return false;
                else if (q == 0) {
                    items.remove(i);
                    return true;
                }
                items.get(index).setQuantity(q);
                return true;
            }
            index++;
        }
        if (item.getQuantity() + q > 0) {
            items.add(item);
            return true;
        }
        return false;
    }

    public boolean changeWeapons(Weapon weapon) {
        int q = 0;
        int index = 0;

        for (Weapon w : weapons) {
            if (w.getId() == weapon.getId()) {
                q = w.getQuantity() + weapon.getQuantity();
                if (q < 0)
                    return false;
                else if (q == 0) {
                    weapons.remove(w);
                    return true;
                }
                weapons.get(index).setQuantity(q);
                return true;
            }
            index++;
        }
        if (weapon.getQuantity() + q > 0) {
            weapons.add(weapon);
            return true;
        }
        return false;
    }

    public boolean changeArmors(Armor armor) {
        int q = 0;
        int index = 0;

        for (Armor a : armors) {
            if (a.getId() == armor.getId()) {
                q = a.getQuantity() + armor.getQuantity();
                if (q < 0)
                    return false;
                else if (q == 0) {
                    armors.remove(a);
                    return true;
                }
                armors.get(index).setQuantity(q);
                return true;
            }
            index++;
        }
        if (armor.getQuantity() + q > 0) {
            armors.add(armor);
            return true;
        }
        return false;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ArrayList<BaseItem> getAllItems() {
        ArrayList<BaseItem> all = new ArrayList<BaseItem>();
        for (BaseItem baseItem : items) {
            all.add(baseItem);
        }
        for (BaseItem baseItem : weapons) {
            all.add(baseItem);
        }
        for (BaseItem baseItem : armors) {
            all.add(baseItem);
        }

        return all;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(ArrayList<Weapon> weapons) {
        this.weapons = weapons;
    }

    public ArrayList<Armor> getArmors() {
        return armors;
    }

    public void setArmors(ArrayList<Armor> armors) {
        this.armors = armors;
    }

}
