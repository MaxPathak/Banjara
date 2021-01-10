package src.commands;

import java.lang.reflect.Method;
import java.util.LinkedList;

import src.items.usable.Item;

public class CommandManager extends Command {
    LinkedList<Command> commands;

    public CommandManager(Command... commands) {
        this.commands = new LinkedList<Command>();
        for (Command command : commands) {
            this.commands.add(command);
        }
    }

    public void execute() {
        for (Command command : commands) {
            command.execute();
        }

    }

    public void addItem(Item item) {
        // map.getEntityManager().getPlayer().getInventory().addItem(item);
    }

}
