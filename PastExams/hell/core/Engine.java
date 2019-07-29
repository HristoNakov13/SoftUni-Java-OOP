package hell.core;

import hell.core.inputOutput.InputReaderImpl;
import hell.core.inputOutput.OutputWriterImpl;
import hell.interfaces.InputReader;
import hell.interfaces.OutputWriter;

import java.util.List;

public class Engine {
    private static final String STOP_PROGRAM_COMMAND = "Quit";
    private CommandHandler commandHandler;
    private CommandInterpreter interpreter;
    private InputReader reader;
    private OutputWriter outputWriter;

    public Engine() {
        this.commandHandler = new CommandHandler();
        this.reader = new InputReaderImpl();
        this.outputWriter = new OutputWriterImpl();
        this.interpreter = new CommandInterpreter();
    }

// commandName should call interpreter.getCommandName AFTER interpreter.interpretCommand
// because commandName is set inside that method and will return null if called before

    public void run() {
        String commandName, input, output;
        List<String> commandArguments;

        do {
            input = this.reader.readLine();
            commandArguments = this.interpreter.interpretCommand(input);
            commandName = this.interpreter.getCommandName();

            output = this.commandHandler.handleCommand(commandName, commandArguments);
            this.outputWriter.writeLine(output);

        } while (!commandName.equals(STOP_PROGRAM_COMMAND));
    }
}
