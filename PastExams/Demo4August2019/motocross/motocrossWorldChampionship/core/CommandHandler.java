package motocrossWorldChampionship.core;

import motocrossWorldChampionship.core.interfaces.ChampionshipController;

public class CommandHandler {
    private ChampionshipController controller;

    public CommandHandler(ChampionshipController controller) {
        this.controller = controller;
    }

    public String handleCommand(String commandName, String[] arguments) {
        String output = "";
        String name = arguments[1];
        String type;
        int horsePower, laps;

        switch (commandName) {
            case "CreateRider":
                output = this.controller.createRider(name);
                break;
            case "CreateMotorcycle":
                type = arguments[1];
                name = arguments[2];
                horsePower = Integer.parseInt(arguments[3]);
                output = this.controller.createMotorcycle(type, name, horsePower);
                break;
            case "AddMotorcycleToRider":
                String motorcycleName = arguments[2];
                output = this.controller.addMotorcycleToRider(name, motorcycleName);
                break;
            case "AddRiderToRace":
                String riderName = arguments[2];
                output = this.controller.addRiderToRace(name, riderName);
                break;
            case "CreateRace":
                laps = Integer.parseInt(arguments[2]);
                output = this.controller.createRace(name, laps);
                break;
            case "StartRace":
                output = this.controller.startRace(name);
                break;
        }
        return output;
    }
}
