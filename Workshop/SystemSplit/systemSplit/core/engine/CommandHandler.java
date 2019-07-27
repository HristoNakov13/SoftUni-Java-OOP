package systemSplit.core.engine;

import systemSplit.core.commands.*;

import java.util.HashMap;
import java.util.Map;

public class CommandHandler {
private Map<String, Command> commands;

    public CommandHandler() {
        this.commands = new HashMap<>();
        this.init();
    }

    public void handleCommand(String commandName, String[] args) {
        this.commands.get(commandName).execute(args);
    }

    private void init() {
        this.commands.put("RegisterPowerHardware", new RegisterPowerHardware());
        this.commands.put("RegisterHeavyHardware", new RegisterHeavyHardware());
        this.commands.put("RegisterLightSoftware", new RegisterLightSoftware());
        this.commands.put("RegisterExpressSoftware", new RegisterExpressSoftware());
        this.commands.put("ReleaseSoftwareComponent", new ReleaseSoftwareComponent());
        this.commands.put("Analyze", new Analyze());
        this.commands.put("System Split", new SystemSplit());
        this.commands.put("Dump", new Dump());
        this.commands.put("Restore", new Restore());
        this.commands.put("Destroy", new Destroy());
        this.commands.put("DumpAnalyze", new DumpAnalyze());

    }
}
