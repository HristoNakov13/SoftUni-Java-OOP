package boatSimulator.core;

import boatSimulator.models.ArgumentException;
import boatSimulator.models.race.UnallowedBoatType;
import boatSimulator.repository.DuplicateModelException;
import boatSimulator.repository.NonExistantModelException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Engine {
    private CommandHandler commandHandler;

    public Engine() {
        this.commandHandler = new CommandHandler();
    }

    public void run() throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        String input;

        while (!"End".equals(input = bfr.readLine())) {
            String[] data = input.split("\\\\");
            String commandName = data[0];
            String output = this.handleCommand(data, commandName);
            System.out.println(output);
        }
    }

    public String handleCommand(String[]data, String commandName) {
        String result = null;
        try {
            result = this.commandHandler.handleCommand(data, commandName);
        } catch (ArgumentException
                | DuplicateModelException
                | NonExistantModelException
                | IllegalRaceException
                | UnallowedBoatType e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
