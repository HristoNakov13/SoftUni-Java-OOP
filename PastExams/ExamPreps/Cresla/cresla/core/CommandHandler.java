package cresla.core;

import cresla.interfaces.Manager;

import java.util.List;

public class CommandHandler {
    Manager manager;

    public CommandHandler(Manager manager) {
        this.manager = manager;
    }

    public String handleCommand(String commandName, List<String> arguments) {
        String output = "";
        switch (commandName) {
            case "Report":
                output = this.manager.reportCommand(arguments);
                break;
            case "Exit":
                output = this.manager.exitCommand(arguments);
                break;
            case "Module":
                output = this.manager.moduleCommand(arguments);
                break;
            case "Reactor":
               output = this.manager.reactorCommand(arguments);
                break;
        }
        return output;
    }
}
