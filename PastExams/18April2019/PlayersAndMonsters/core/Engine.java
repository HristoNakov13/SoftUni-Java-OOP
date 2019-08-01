package core;

import common.InputInterpreter;
import core.interfaces.ManagerController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Engine {
    private static final String END_PROGRAM_COMMAND = "Exit";
    private ManagerController managerController;
    private BufferedReader reader;
    private InputInterpreter inputInterpreter;

    public Engine(ManagerController managerController, InputInterpreter inputInterpreter) {
        this.managerController = managerController;
        this.inputInterpreter = inputInterpreter;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void run() throws IOException {
        String input = this.reader.readLine();
        String commandName = this.inputInterpreter.getCommandName(input);
        String[] commandArgs;

        while (!commandName.equals(END_PROGRAM_COMMAND)) {
            commandArgs = this.inputInterpreter.getCommandArguments(input);
            String output;
            try {
                output = this.handleCommand(commandName, commandArgs);
            } catch (Exception e) {
                output = e.getMessage();
            }
            System.out.println(output);
            input = this.reader.readLine();
            commandName = this.inputInterpreter.getCommandName(input);
        }
    }

    private String handleCommand(String commandName, String[] commandArgs) {
        String output = "";
        String name, type, player;
        switch (commandName) {
            case "AddPlayer":
                type = commandArgs[0];
                name = commandArgs[1];
                output = this.managerController.addPlayer(type, name);
                break;
            case "AddCard":
                type = commandArgs[0];
                name = commandArgs[1];
                output = this.managerController.addCard(type, name);
                break;
            case "AddPlayerCard":
                player = commandArgs[0];
                String card = commandArgs[1];
                output = this.managerController.addPlayerCard(player, card);
                break;
            case "Fight":
                player = commandArgs[0];
                String enemy = commandArgs[1];
                output = this.managerController.fight(player, enemy);
                break;
            case "Report":
                output = this.managerController.report();
                break;
        }
       return output;
    }
}
