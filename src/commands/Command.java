package src.commands;

import java.lang.reflect.Method;

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
                } catch (Exception e) {
                    throw new RuntimeException("Command Error");
                }
            }

        }
    }

    public void execute() {
        try {
            method.invoke(this.getClass().getDeclaredConstructor().newInstance(), parameters);
        } catch (Exception e) {
            throw new RuntimeException("Command Error");
        }
    }

    public void addItem(Item item) {
        State.getHandler().getMap().getEntityManager().getPlayer().getInventory().addItem(item);
    }

}
