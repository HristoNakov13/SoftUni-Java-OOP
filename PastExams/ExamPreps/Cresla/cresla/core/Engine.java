package cresla.core;

import cresla.interfaces.InputReader;
import cresla.interfaces.OutputWriter;

import java.util.List;

public class Engine {
    private static final String END_PROGRAM_COMMAND = "Exit";
    private InputInterpreter inputInterpreter;
    private InputReader inputReader;
    private OutputWriter outputWriter;
   private CommandHandler commandHandler;


    public Engine(InputInterpreter inputInterpreter, InputReader inputReader, OutputWriter outputWriter, CommandHandler commandHandler) {
        this.inputInterpreter = inputInterpreter;
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
        this.commandHandler = commandHandler;
    }

    //adding public logic to interfaces not allowed
    public void run() {
        String input, commandName, output;
        List<String> commandArguments;
        do {
            input = this.inputReader.readLine();
            commandName = this.inputInterpreter.getCommandName(input);
            commandArguments = this.inputInterpreter.getCommandArguments(input);

            output = this.commandHandler.handleCommand(commandName, commandArguments);
            this.outputWriter.writeLine(output);

        } while (!commandName.equals(END_PROGRAM_COMMAND));
    }
}
