package src.commands;

import java.lang.reflect.Method;

import src.databases.DatabaseManager;
import src.entities.Entity;
import src.entities.EntityManager;
import src.entities.events.Event;
import src.global.Global;
import src.global.Global.Direction;
import src.items.BaseItem;
import src.items.equip.Armor;
import src.items.equip.Weapon;
import src.items.usable.Item;
import src.states.State;
import src.states.scenes.ChoiceScene;
import src.states.scenes.TextScene;

public class Command {

    private Object[] parameters;
    private Method method;

    public Command() {
        parameters = null;
        method = null;
    }

    public Command(String name, Object... parameters) {
        for (Method method : this.getClass().getDeclaredMethods()) {
            if (method.getName() == name) {
                this.parameters = parameters;
                try {
                    this.method = this.getClass().getDeclaredMethod(name, method.getParameterTypes());
                    return;
                } catch (Exception e) {
                    throw new RuntimeException("Command Error");
                }
            }
        }
    }

    public boolean execute() {
        if (method == null)
            return false;
        try {
            // System.out.println(method.getName());
            // System.out.print("Parameters: ");
            // for (Object object : parameters) {
            // System.out.print(object + " ");
            // }
            // System.exit(0);
            return (Boolean) method.invoke(this.getClass().getDeclaredConstructor().newInstance(), parameters);
        } catch (Exception e) {
            throw new RuntimeException("Command Error");
        }
    }

    public boolean changeItems(int id, int operand, int operandVal) {
        int quantity = 0;
        switch (operand) {
        case 0:
            quantity = operandVal;
            break;
        case 1:
            quantity = -operandVal;
            break;
        }
        Item item = (Item) BaseItem.copy(DatabaseManager.getItemsDatabase().getItemById(id));
        if (item == null)
            return false;
        item.setQuantity(quantity);
        return State.getHandler().getMap().getEntityManager().getPlayer().getInventory().changeItems(item);
    }

    public boolean changeWeapons(int id, int operand, int operandVal) {
        int quantity = 0;
        switch (operand) {
        case 0:
            quantity = operandVal;
            break;
        case 1:
            quantity = -operandVal;
            break;
        }
        Weapon weapon = (Weapon) BaseItem.copy(DatabaseManager.getWeaponsDatabase().getItemById(id));
        if (weapon == null)
            return false;
        weapon.setQuantity(quantity);
        return State.getHandler().getMap().getEntityManager().getPlayer().getInventory().changeWeapons(weapon);
    }

    public boolean changeArmors(int id, int operand, int operandVal) {
        int quantity = 0;
        switch (operand) {
        case 0:
            quantity = operandVal;
            break;
        case 1:
            quantity = -operandVal;
            break;
        }
        Armor armor = (Armor) BaseItem.copy(DatabaseManager.getArmorDatabase().getItemById(id));
        if (armor == null)
            return false;
        armor.setQuantity(quantity);
        return State.getHandler().getMap().getEntityManager().getPlayer().getInventory().changeArmors(armor);
    }

    public boolean showText(String text) {
        State.getState().changeState(new TextScene(text));
        return true;
    }

    public boolean setSelfSwitch(int id, String currentSelfSwitch) {
        EntityManager entityManager = State.getHandler().getMap().getEntityManager();
        int i = 0;
        for (Entity entity : entityManager.getEntities()) {
            if (entity.getId() == id) {
                break;
            }
            i++;
        }
        if (i < entityManager.getEntities().size()) {
            if (entityManager.getEntities().get(i) instanceof Event) {
                Event e = (Event) entityManager.getEntities().get(i);
                e.setCurrentSelfSwitch(currentSelfSwitch);
            }
        }
        return true;
    }

    public boolean setSwitch(int id, boolean value) {
        Global.switches[id] = value;
        return true;
    }

    public boolean changeGold(int operand, int operandVal) {
        int amount = 0;
        switch (operand) {
        case 0:
            amount = operandVal;
            break;
        case 1:
            amount = -operandVal;
            break;
        }
        int gold = State.getHandler().getMap().getEntityManager().getPlayer().getGold();
        if (gold + amount >= 0) {
            State.getHandler().getMap().getEntityManager().getPlayer().setGold(gold + amount);
            return true;
        }
        return false;
    }

    public boolean removeEvent(int id) {
        EntityManager entityManager = State.getHandler().getMap().getEntityManager();
        int i = 0;
        for (Entity entity : entityManager.getEntities()) {
            if (entity.getId() == id) {
                break;
            }
            i++;
        }
        if (i < entityManager.getEntities().size()) {
            if (entityManager.getEntities().get(i) instanceof Event) {
                entityManager.getEntities().remove(i);
                return true;
            }
        }

        return false;

    }

    public boolean transferPlayer(int direction, int x, int y) {
        // if (State.getHandler().getMap().getTile(x, y).isSolid())
        // return false;
        if (State.getHandler().getMap().layers.get(0).data[x][y] - State.getHandler().getMap().regionIndex == 1)
            return false;
        State.getHandler().getMap().getEntityManager().getPlayer().setX(x);
        State.getHandler().getMap().getEntityManager().getPlayer().setY(y);
        State.getHandler().getMap().getEntityManager().getPlayer().setDirection(Direction.values()[direction]);
        return true;
    }

    public boolean showChoices(String text, CommandManagerList list) {
        State.getState().changeState(new ChoiceScene(text, list));
        return true;
    }

}
