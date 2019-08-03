package cresla.core;

import cresla.interfaces.InputReader;
import cresla.interfaces.Manager;
import cresla.interfaces.OutputWriter;

import java.util.List;

public class Engine {
    private static final String END_PROGRAM_COMMAND = "Exit";
    private InputInterpreter inputInterpreter;
    private InputReader inputReader;
    private OutputWriter outputWriter;
   private Manager manager;


    public Engine(InputInterpreter inputInterpreter, InputReader inputReader, OutputWriter outputWriter, Manager manager) {
        this.inputInterpreter = inputInterpreter;
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
        this.manager = manager;

    }

    //adding public logic to interfaces not allowed for second problem?
    public void run() {
        String input, commandName, output;
        List<String> commandArguments;
        do {
            input = this.inputReader.readLine();
            commandName = this.inputInterpreter.getCommandName(input);
            commandArguments = this.inputInterpreter.getCommandArguments(input);

            output = this.manager.handleCommand(commandName, commandArguments);
            this.outputWriter.writeLine(output);

        } while (!commandName.equals(END_PROGRAM_COMMAND));
    }
}
