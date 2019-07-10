package boatSimulator.core;

import boatSimulator.models.ArgumentException;
import boatSimulator.models.boats.*;
import boatSimulator.models.engines.Engine;
import boatSimulator.models.race.Race;
import boatSimulator.models.race.RaceImpl;
import boatSimulator.models.race.UnallowedBoatType;
import boatSimulator.repository.Database;
import boatSimulator.repository.DuplicateModelException;
import boatSimulator.repository.NonExistantModelException;

public class Controller {
    private Database database;
    private Race currentRace;

    public Controller() {
        this.database = new Database();
        currentRace = null;
    }

    public String signUpBoat(String model) throws NonExistantModelException, UnallowedBoatType {
        Boat participant = this.database.getBoat(model);
        if (participant != null && this.currentRace.SignUpBoat(participant)) {
            return String.format("Boat with model %s has signed up for the current Race.", model);
        } else {
            return null;
        }
    }

    public String createEngine(String model, int horsePower, int displacement, String type) throws ArgumentException, DuplicateModelException {
        Engine engine = new Engine(model, horsePower, displacement, type);
        this.database.addEngine(engine);
        return String.format("Engine model %s with %d HP and displacement %d cm3 created successfully."
                , model
                , horsePower
                , displacement);
    }

    public String createRowBoat(String model, int weight, int oars) throws ArgumentException, DuplicateModelException {
        Boat boat = new RowBoat(model, weight, oars);
        this.database.addBoat(boat);
        return String.format("Row Boat with model %s registered successfully.", model);
    }

    public String createSailBoat(String model, int weight, double sailEfficiency) throws ArgumentException, DuplicateModelException {
        Boat boat = new SailBoat(model, weight, sailEfficiency);
        this.database.addBoat(boat);
        return String.format("Sail Boat with model %s registered successfully.", model);
    }

    public String createPowerBoat(String model, int weight, String firstEngineModel, String secondEngineModel) throws ArgumentException, DuplicateModelException, NonExistantModelException {
        Engine firstEngine = this.database.getEngine(firstEngineModel);
        Engine secondEngine = this.database.getEngine(secondEngineModel);

        Boat boat = new PowerBoat(model, weight, firstEngine, secondEngine);
        this.database.addBoat(boat);
        return String.format("Power Boat with model %s registered successfully.", model);
    }

    public String createYacht(String model, int weight, String engineModel, int cargoWeight) throws ArgumentException, DuplicateModelException, NonExistantModelException {
        Engine engine = this.database.getEngine(engineModel);
        Boat boat = new Yacht(model, weight, engine, cargoWeight);
        this.database.addBoat(boat);
        return String.format("Yacht with model %s registered successfully.", model);
    }

    public String openRace(int distance, double windSpeed, double oceanCurrent, boolean motorBoatsAllowed) throws IllegalRaceException, ArgumentException {
        if (thereIsOngoingRace()) {
            throw new IllegalRaceException("There is already an ongoing race");
        }
        this.currentRace = new RaceImpl(distance, windSpeed, oceanCurrent, motorBoatsAllowed);
        return String.format("A new race with distance %d meters, wind speed %.0f m/s and ocean current speed %.0f m/s has been set."
        , distance, windSpeed, oceanCurrent);
    }

    public String startRace() throws IllegalRaceException {
        String result;

        if (thereIsOngoingRace()) {
            result = this.currentRace.StartRace();
        } else {
            throw new IllegalRaceException("None available races");
        }
        return result;
    }

    private boolean thereIsOngoingRace() {
        return this.currentRace != null;
    }
}
