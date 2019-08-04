package motocrossWorldChampionship.core;

import motocrossWorldChampionship.core.interfaces.Engine;
import motocrossWorldChampionship.io.interfaces.InputReader;
import motocrossWorldChampionship.io.interfaces.OutputWriter;

import java.io.IOException;

public class EngineImpl implements Engine {
    private static final String END_PROGRAM_COMMAND = "End";
    private InputReader inputReader;
    private OutputWriter outputWriter;
    private CommandHandler commandHandler;

    public EngineImpl(InputReader inputReader, OutputWriter outputWriter, CommandHandler commandHandler) {
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
        this.commandHandler = commandHandler;
    }

    @Override
    public void run() {
        String[] commandArgs = this.getCommandArguments();
        String commandName = commandArgs[0];

        while (!commandName.equals(END_PROGRAM_COMMAND)) {
            String output;
            try {
                output = this.commandHandler.handleCommand(commandName, commandArgs);
            } catch (IllegalArgumentException | NullPointerException e) {
                output = e.getMessage();
            }
           this.outputWriter.writeLine(output);

            commandArgs = this.getCommandArguments();
            commandName = commandArgs[0];
        }
    }

    private String[] getCommandArguments() {
        String[] commandArgs = null;
        try {
            commandArgs = this.inputReader.readLine().split(" ");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return commandArgs;
    }
}
