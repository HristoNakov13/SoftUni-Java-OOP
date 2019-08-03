package cresla.core;

import cresla.core.commands.ExitCommand;
import cresla.core.commands.ModuleCommand;
import cresla.core.commands.ReactorCommand;
import cresla.core.commands.ReportCommand;
import cresla.core.factory.FactoryImpl;
import cresla.interfaces.Command;
import cresla.interfaces.Database;
import cresla.interfaces.Factory;
import cresla.interfaces.Manager;
import cresla.repository.DatabaseImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerImpl implements Manager {
    private static final String REACTOR_COMMAND = "Reactor";
    private static final String MODULE_COMMAND = "Module";
    private static final String REPORT_COMMAND = "Report";
    private static final String EXIT_COMMAND = "Exit";
    private Map<String, Command> commands;

    public ManagerImpl() {
        this.init();
    }

    private void init() {
        Database database = new DatabaseImpl();
        this.commands = new HashMap<>();
        Factory factory = new FactoryImpl();

        this.commands.put(REACTOR_COMMAND, new ReactorCommand(database, factory));
        this.commands.put(MODULE_COMMAND, new ModuleCommand(database, factory));
        this.commands.put(REPORT_COMMAND, new ReportCommand(database));
        this.commands.put(EXIT_COMMAND, new ExitCommand(database));
    }

//    private String handleCommand(String commandName, List<String> arguments) {
//       String output = this.commands.get(commandName).execute(arguments);
//       return output;
//    }

    @Override
    public String reactorCommand(List<String> arguments) {
        return this.commands.get(REACTOR_COMMAND).execute(arguments);
    }

    @Override
    public String moduleCommand(List<String> arguments) {
        return this.commands.get(MODULE_COMMAND).execute(arguments);
    }

    @Override
    public String reportCommand(List<String> arguments) {
        return this.commands.get(REPORT_COMMAND).execute(arguments);
    }

    @Override
    public String exitCommand(List<String> arguments) {
        return this.commands.get(EXIT_COMMAND).execute(arguments);
    }
}
