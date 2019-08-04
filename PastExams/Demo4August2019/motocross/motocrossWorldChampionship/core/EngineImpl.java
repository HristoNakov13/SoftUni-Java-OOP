package motocrossWorldChampionship.core;

import motocrossWorldChampionship.core.interfaces.ChampionshipController;
import motocrossWorldChampionship.core.interfaces.Engine;
import motocrossWorldChampionship.io.interfaces.InputReader;
import motocrossWorldChampionship.io.interfaces.OutputWriter;

import java.io.IOException;
import java.util.Calendar;

public class EngineImpl implements Engine {
    private static final String END_PROGRAM_COMMAND = "End";
    private InputReader inputReader;
    private OutputWriter outputWriter;
    private ChampionshipController controller;

    public EngineImpl(InputReader inputReader, OutputWriter outputWriter, ChampionshipController controller) {
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
        this.controller = controller;
    }

    @Override
    public void run() {
        String input = "";
        try {
            input = inputReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] split = input.split(" ");
        String commandName = split[0];

        while (!commandName.equals(END_PROGRAM_COMMAND)) {
            String output;
            try {
                output = this.handleCommand(commandName, split);
            } catch (IllegalArgumentException  | NullPointerException e) {
                output = e.getMessage();
            }
            outputWriter.writeLine(output);

            try {
                input = inputReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            split = input.split(" ");
            commandName = split[0];
        }
    }

    private String handleCommand(String commandName, String[] arguments) {
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
