package src.commands;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Method;

import src.databases.DatabaseManager;
import src.entities.Entity;
import src.entities.EntityManager;
import src.entities.creatures.Player;
import src.entities.events.Event;
import src.global.Global;
import src.global.Global.Direction;
import src.items.BaseItem;
import src.items.equip.Armor;
import src.items.equip.Weapon;
import src.items.usable.Item;
import src.maps.Map;
import src.states.GameState;
import src.states.State;
import src.states.scenes.ChoiceScene;
import src.states.scenes.TextScene;

public class Command implements Serializable {

    private Object[] parameters;
    transient private Method method;

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

    public static boolean changeItems(int id, int operand, int operandVal) {
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

    public static boolean changeWeapons(int id, int operand, int operandVal) {
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

    public static boolean changeArmors(int id, int operand, int operandVal) {
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

    public static boolean showText(String text) {
        State.getState().changeState(new TextScene(text));
        return true;
    }

    public static boolean setSelfSwitch(int id, String currentSelfSwitch) {
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
        State.getHandler().getGame().update();
        return true;
    }

    public static boolean setSwitch(int id, boolean value) {
        Global.switches[id] = value;
        return true;
    }

    public static boolean changeGold(int operand, int operandVal) {
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

    public static boolean removeEvent(int id) {
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

    public static boolean transferPlayer(int direction, int x, int y, String mapName) {

        Map prevMap = State.getHandler().getMap();

        if (mapName != null) {
            for (Map m : ((GameState) State.getState()).getMaps()) {
                if (m.getName() == mapName) {
                    State.getHandler().setMap(m);
                }
            }
        }

        int regionLayer = State.getHandler().getMap().regionLayer;

        if (State.getHandler().getMap().layers.get(regionLayer).data[x][y]
                - State.getHandler().getMap().regionIndex == 1) {
            if (mapName == null)
                State.getHandler().setMap(prevMap);
            return false;
        }
        State.getHandler().getMap().getEntityManager().getPlayer().setX(x);
        State.getHandler().getMap().getEntityManager().getPlayer().setY(y);
        State.getHandler().getMap().getEntityManager().getPlayer().setDirection(Direction.values()[direction]);
        if (mapName != null)
            State.getHandler().getGame().gameState.setCurrentMap(State.getHandler().getMap());

        for (Entity e : prevMap.getEntityManager().getEntities()) {
            if (e.getClass() != Player.class) {
                Event event = (Event) e;
                event.setCurrentDirection(event.getCurrentPage().getDirection());
            }
        }

        return true;
    }

    public static boolean showChoices(String choices, CommandManagerList commandManagerList) {
        State.getState().changeState(new ChoiceScene(choices, commandManagerList));
        return true;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(method.getDeclaringClass());
        out.writeUTF(method.getName());
        out.writeObject(method.getParameterTypes());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        Class<?> declaringClass = (Class<?>) in.readObject();
        String methodName = in.readUTF();
        Class<?>[] parameterTypes = (Class<?>[]) in.readObject();
        try {
            method = declaringClass.getMethod(methodName, parameterTypes);
        } catch (Exception e) {
            throw new IOException(String.format("Error occurred resolving deserialized method '%s.%s'",
                    declaringClass.getSimpleName(), methodName), e);
        }
    }

}
