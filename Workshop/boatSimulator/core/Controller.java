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

    public String signUpBoat(String model) throws NonExistantModelException, UnallowedBoatType, IllegalRaceException {
        if (!thereIsOngoingRace()) {
            throw new IllegalRaceException("There is currently no race set.");
        }
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
        return boatSuccessfullyCreated(boat);
    }

    public String createSailBoat(String model, int weight, double sailEfficiency) throws ArgumentException, DuplicateModelException {
        Boat boat = new SailBoat(model, weight, sailEfficiency);
        return boatSuccessfullyCreated(boat);
    }

    public String createPowerBoat(String model, int weight, String firstEngineModel, String secondEngineModel) throws ArgumentException, DuplicateModelException, NonExistantModelException {
        Engine firstEngine = this.database.getEngine(firstEngineModel);
        Engine secondEngine = this.database.getEngine(secondEngineModel);

        Boat boat = new PowerBoat(model, weight, firstEngine, secondEngine);
        return boatSuccessfullyCreated(boat);
    }

    public String createYacht(String model, int weight, String engineModel, int cargoWeight) throws ArgumentException, DuplicateModelException, NonExistantModelException {
        Engine engine = this.database.getEngine(engineModel);
        Boat boat = new Yacht(model, weight, engine, cargoWeight);
        return boatSuccessfullyCreated(boat);
    }

    private String boatSuccessfullyCreated(Boat boat) throws DuplicateModelException {
        String boatType = new Throwable()
                .getStackTrace()[1]
                .getMethodName()
                .replace("create", "");
        this.database.addBoat(boat);
        return String.format("%s with model %s registered successfully.", boatType, boat.getModel());
    }

    public String openRace(int distance, double windSpeed, double oceanCurrent, boolean motorBoatsAllowed) throws IllegalRaceException, ArgumentException {
        String result = "";
        if (this.currentRace == null) {
            this.currentRace = new RaceImpl(distance, windSpeed, oceanCurrent, motorBoatsAllowed);
            result = String.format("A new race with distance %d meters, wind speed %.0f m/s and ocean current speed %.0f m/s has been set."
                    , distance
                    , windSpeed
                    , oceanCurrent);
        } else {
            if (motorBoatsAllowed == this.currentRace.motorBoatsAllowed()) {
                result = "The current race has already been set.";
            }else {
                this.updateRace(distance, windSpeed, oceanCurrent, motorBoatsAllowed);
            }
        }
        return result;
    }

    private void updateRace(int distance, double windSpeed, double oceanCurrent, boolean motorBoatsAllowed) throws ArgumentException {
        this.currentRace.setDistance(distance);
        this.currentRace.setWindSpeed(windSpeed);
        this.currentRace.setOceanCurrent(oceanCurrent);
        this.currentRace.setMotorBoatsAllowed(motorBoatsAllowed);
    }

    public String startRace() throws IllegalRaceException {
        String result;

        if (thereIsOngoingRace()) {
            if (this.currentRace.hasEnoughParticipants()) {
                result = this.currentRace.StartRace();
                this.currentRace = null;
            } else {
                result = "Not enough contestants for the race.";
            }
        } else {
            throw new IllegalRaceException("There is currently no race set.");
        }
        return result;
    }

    private boolean thereIsOngoingRace() {
        return this.currentRace != null;
    }
}
