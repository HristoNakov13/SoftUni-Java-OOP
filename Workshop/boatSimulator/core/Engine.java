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
            String output;
            try {
                output = this.handleCommand(data, commandName);
                System.out.println(output);
            } catch (DuplicateModelException
                    | UnallowedBoatType
                    | NonExistantModelException
                    | IllegalRaceException
                    | ArgumentException e) {
                System.out.println(e.getMessage());
            }


        }
    }

    public String handleCommand(String[] data, String commandName) throws DuplicateModelException, UnallowedBoatType, NonExistantModelException, IllegalRaceException, ArgumentException {
        return this.commandHandler.handleCommand(data, commandName);
    }
}
