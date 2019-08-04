package motocrossWorldChampionship;

import motocrossWorldChampionship.core.ChampionshipControllerImpl;
import motocrossWorldChampionship.core.CommandHandler;
import motocrossWorldChampionship.core.EngineImpl;
import motocrossWorldChampionship.core.interfaces.ChampionshipController;
import motocrossWorldChampionship.core.interfaces.Engine;
import motocrossWorldChampionship.entities.PowerMotorcycle;
import motocrossWorldChampionship.entities.RiderImpl;
import motocrossWorldChampionship.entities.SpeedMotorcycle;
import motocrossWorldChampionship.entities.interfaces.Motorcycle;
import motocrossWorldChampionship.entities.interfaces.Rider;
import motocrossWorldChampionship.io.InputReaderImpl;
import motocrossWorldChampionship.io.OutputWriterImpl;
import motocrossWorldChampionship.io.interfaces.InputReader;
import motocrossWorldChampionship.io.interfaces.OutputWriter;
import motocrossWorldChampionship.repositories.RiderRepository;

public class Main {
    public static void main(String[] args) {
        InputReader inputReader = new InputReaderImpl();
        OutputWriter outputWriter = new OutputWriterImpl();

        ChampionshipController controller = new ChampionshipControllerImpl();
        CommandHandler commandHandler = new CommandHandler(controller);

        Engine engine = new EngineImpl(inputReader, outputWriter, commandHandler);
        engine.run();

    }
}
