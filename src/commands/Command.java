package src.commands;

import java.lang.reflect.Method;

import src.databases.DatabaseManager;
import src.entities.Entity;
import src.entities.EntityManager;
import src.entities.events.Event;
import src.global.Global;
import src.items.equip.Weapon;
import src.items.usable.Item;
import src.states.State;
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

    public void execute() {
        if (method == null)
            return;
        try {
            // System.out.println(method.getName());
            // System.out.print("Parameters: ");
            // for (Object object : parameters) {
            // System.out.print(object + " ");
            // }
            // System.exit(0);
            method.invoke(this.getClass().getDeclaredConstructor().newInstance(), parameters);
        } catch (Exception e) {
            throw new RuntimeException("Command Error");
        }
    }

    public void changeItems(int id, int operand, int operandVal) {
        int quantity = 0;
        switch (operand) {
        case 0:
            quantity = operandVal;
            break;
        case 1:
            quantity = -operandVal;
            break;
        }
        Item item = (Item) DatabaseManager.getItemsDatabase().getItemById(id);
        if (item == null)
            return;
        item.setQuantity(quantity);
        // System.out.println
        // asd
        State.getHandler().getMap().getEntityManager().getPlayer().getInventory().changeItems(item);
    }

    public void changeWeapons(int id, int operand, int operandVal) {
        int quantity = 0;
        switch (operand) {
        case 0:
            quantity = operandVal;
            break;
        case 1:
            quantity = -operandVal;
            break;
        }
        Weapon weapon = (Weapon) DatabaseManager.getWeaponsDatabase().getItemById(id);
        if (weapon == null)
            return;
        weapon.setQuantity(quantity);
        State.getHandler().getMap().getEntityManager().getPlayer().getInventory().changeWeapons(weapon);
    }

    public void showText(String text) {
        State.getState().changeState(new TextScene(text));
    }

    public void setSelfSwitch(int id, String currentSelfSwitch) {
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

    }

    public void setSwitch(int id, boolean value) {
        Global.switches[id] = value;
    }

}
