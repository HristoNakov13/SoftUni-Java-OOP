package motocrossWorldChampionship;

import motocrossWorldChampionship.core.ChampionshipControllerImpl;
import motocrossWorldChampionship.core.EngineImpl;
import motocrossWorldChampionship.core.interfaces.ChampionshipController;
import motocrossWorldChampionship.core.interfaces.Engine;
import motocrossWorldChampionship.io.InputReaderImpl;
import motocrossWorldChampionship.io.OutputWriterImpl;
import motocrossWorldChampionship.io.interfaces.InputReader;
import motocrossWorldChampionship.io.interfaces.OutputWriter;

public class Main {
    public static void main(String[] args) {
        InputReader inputReader = new InputReaderImpl();
        OutputWriter outputWriter = new OutputWriterImpl();
        ChampionshipController controller = new ChampionshipControllerImpl();

        Engine engine = new EngineImpl(inputReader, outputWriter, controller);
        engine.run();
    }
}
