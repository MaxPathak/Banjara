package src.commands;

import java.util.LinkedList;

public class CommandManager {
    private LinkedList<Command> commands;

    public CommandManager(Command... commands) {
        this.commands = new LinkedList<Command>();
        for (Command command : commands) {
            this.commands.add(command);
        }
    }

    public boolean execute() {
        for (Command command : commands) {
            if (command.execute() == false)
                return false;
        }
        return true;
    }

}
