package boatSimulator.repository;

import boatSimulator.models.boats.Boat;
import boatSimulator.models.engines.Engine;

public class Database {
    private BoatRepository boatRepository;
    private EngineRepository engineRepository;

    public Database() {
        this.boatRepository = new BoatRepository();
        this.engineRepository = new EngineRepository();
    }

    public void addBoat(Boat boat) throws DuplicateModelException {
        this.boatRepository.addBoat(boat);
    }

    public void addEngine(Engine engine) throws DuplicateModelException {
        this.engineRepository.addEngine(engine);
    }

    public Engine getEngine(String model) throws NonExistantModelException {
        return this.engineRepository.getEngine(model);
    }

    public Boat getBoat(String model) throws NonExistantModelException {
        return this.boatRepository.getBoat(model);
    }
}
