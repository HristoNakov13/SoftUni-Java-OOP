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
import motocrossWorldChampionship.entities.interfaces.Race;
import motocrossWorldChampionship.entities.interfaces.Rider;
import motocrossWorldChampionship.io.InputReaderImpl;
import motocrossWorldChampionship.io.OutputWriterImpl;
import motocrossWorldChampionship.io.interfaces.InputReader;
import motocrossWorldChampionship.io.interfaces.OutputWriter;
import motocrossWorldChampionship.repositories.MotorcycleRepository;
import motocrossWorldChampionship.repositories.RaceRepository;
import motocrossWorldChampionship.repositories.RiderRepository;
import motocrossWorldChampionship.repositories.interfaces.Repository;

public class Main {
    public static void main(String[] args) {
        InputReader inputReader = new InputReaderImpl();
        OutputWriter outputWriter = new OutputWriterImpl();

        Repository<Race> raceRepository = new RaceRepository();
        Repository<Motorcycle> motorcycleRepository = new MotorcycleRepository();
        Repository<Rider> riderRepository = new RiderRepository();

        ChampionshipController controller = new ChampionshipControllerImpl(riderRepository, motorcycleRepository, raceRepository);
        CommandHandler commandHandler = new CommandHandler(controller);

        Engine engine = new EngineImpl(inputReader, outputWriter, commandHandler);
        engine.run();

    }
}
