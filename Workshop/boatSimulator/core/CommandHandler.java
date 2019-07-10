package boatSimulator.core;

import boatSimulator.models.ArgumentException;
import boatSimulator.models.race.UnallowedBoatType;
import boatSimulator.repository.DuplicateModelException;
import boatSimulator.repository.NonExistantModelException;

public class CommandHandler {
    private static Controller CONTROLLER = new Controller();


    //use reflection instead of switch?
   public String handleCommand(String[] data, String commandName) throws ArgumentException, DuplicateModelException, NonExistantModelException, IllegalRaceException, UnallowedBoatType {
        String result = null;
        String model;

        if (commandName.contains("Create")) {
            model = data[1];
            int weight = Integer.parseInt(data[2]);
            switch (commandName) {
                case "CreateRowBoat":
                    int oars = Integer.parseInt(data[3]);
                    result = CONTROLLER.createRowBoat(model, weight, oars);
                    break;
                case "CreateSailBoat":
                    int sailEfficiency = Integer.parseInt(data[3]);
                    result = CONTROLLER.createSailBoat(model, weight, sailEfficiency);
                    break;
                case "CreatePowerBoat":
                    String firstEngineModel = data[3];
                    String secondEngineModel = data[4];
                    result = CONTROLLER.createPowerBoat(model, weight, firstEngineModel, secondEngineModel);
                    break;
                case "CreateYacht":
                    String engineModel = data[3];
                    int cargoWeight = Integer.parseInt(data[4]);
                    result = CONTROLLER.createYacht(model, weight, engineModel, cargoWeight);
                    break;
                case "CreateBoatEngine":
                    int horsePower = weight;
                    int displacement = Integer.parseInt(data[3]);
                    String type = data[4];
                    result = CONTROLLER.createEngine(model, horsePower, displacement, type);
                    break;

            }
        } else {
            switch (commandName) {
                case "OpenRace":
                    int distance = Integer.parseInt(data[1]);
                    double windSpeed = Double.parseDouble(data[2]);
                    double oceanCurrent = Double.parseDouble(data[3]);
                    boolean allowedMotorBoats = false;
                    if (data[4].equals("true")) {
                        allowedMotorBoats = true;
                    }
                    result = CONTROLLER.openRace(distance, windSpeed, oceanCurrent, allowedMotorBoats);
                    break;
                case "StartRace":
                    result = CONTROLLER.startRace();
                    break;
                case "SignUpBoat":
                    model = data[1];
                    result = CONTROLLER.signUpBoat(model);
                    break;
            }
        }
        return result;
    }
}
