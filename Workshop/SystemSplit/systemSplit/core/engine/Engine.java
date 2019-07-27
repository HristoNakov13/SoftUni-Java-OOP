package systemSplit.core.engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Engine {
    private CommandHandler commandHandler;
    private CommandInterpreter commandInterpreter;

    public Engine() {
        this.commandHandler = new CommandHandler();
        this.commandInterpreter = new CommandInterpreter();
    }

    public void run() throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        String input;

        do {
            input = bfr.readLine();
            String[] commandArgs = this.commandInterpreter.interpretCommand(input);
            String commandName = this.commandInterpreter.getCommandName(input);
            this.commandHandler.handleCommand(commandName, commandArgs);

        } while (!input.equals("System Split"));
    }
}
