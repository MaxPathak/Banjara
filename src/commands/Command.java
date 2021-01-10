package src.commands;

import java.lang.reflect.Method;

import src.databases.DatabaseManager;
import src.items.equip.Weapon;
import src.items.usable.Item;
import src.states.State;

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
                quantity = operandVal;
                break;
        }
        Item item = (Item) DatabaseManager.getItemsDatabase().getItemById(id);
        if (item == null)
            return;
        item.setQuantity(quantity);
        State.getHandler().getMap().getEntityManager().getPlayer().getInventory().changeItems(item);
    }

    public void changeWeapons(int id, int operand, int operandVal) {
        int quantity = 0;
        switch (operand) {
            case 0:
                quantity = operandVal;
                break;
            case 1:
                quantity = operandVal;
                break;
        }
        Weapon weapon = (Weapon) DatabaseManager.getWeaponsDatabase().getItemById(id);
        if (weapon == null)
            return;
        weapon.setQuantity(quantity);
        State.getHandler().getMap().getEntityManager().getPlayer().getInventory().changeWeapons(weapon);
    }

}
