package src.commands;

import java.util.LinkedList;

import src.items.usable.Item;

public class CommandManager extends Command {
    private LinkedList<Command> commands;

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

}
