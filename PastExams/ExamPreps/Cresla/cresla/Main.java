package cresla;

import cresla.core.Engine;
import cresla.core.InputInterpreter;
import cresla.core.ManagerImpl;
import cresla.inputOutput.InputReaderImpl;
import cresla.inputOutput.OutputWriterImpl;
import cresla.interfaces.InputReader;
import cresla.interfaces.Manager;
import cresla.interfaces.OutputWriter;

public class Main {
    public static void main(String[] args) {

        InputReader reader = new InputReaderImpl();
        InputInterpreter inputInterpreter = new InputInterpreter();
        OutputWriter outputWriter = new OutputWriterImpl();

        Manager manager = new ManagerImpl();
        Engine engine = new Engine(inputInterpreter, reader, outputWriter, manager);
        engine.run();
    }
}
