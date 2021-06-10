package src.commands;

import java.io.Serializable;
import java.util.LinkedList;

public class CommandManagerList implements Serializable {
    private LinkedList<CommandManager> managers;

    public CommandManagerList(CommandManager... commandManagers) {
        this.managers = new LinkedList<CommandManager>();
        for (CommandManager commandManager : commandManagers) {
            this.managers.add(commandManager);
        }
    }

    public LinkedList<CommandManager> getManagers() {
        return managers;
    }

    public void setManagers(LinkedList<CommandManager> managers) {
        this.managers = managers;
    }

}
