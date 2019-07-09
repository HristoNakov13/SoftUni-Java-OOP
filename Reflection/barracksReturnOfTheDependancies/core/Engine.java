package barracksReturnOfTheDependancies.core;

import barracksReturnOfTheDependancies.contracts.*;
import barracksReturnOfTheDependancies.contracts.Runnable;
import barracksReturnOfTheDependancies.core.commands.Command;
import barracksReturnOfTheDependancies.core.commands.Inject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Engine implements Runnable {
    private CommandInterpreter commandInterpreter;

    public Engine(CommandInterpreter commandInterpreter) {
        this.commandInterpreter = commandInterpreter;
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        while (true) {
            try {
                String input = reader.readLine();
                String[] data = input.split("\\s+");
                String commandName = data[0];
                String result = interpretCommand(data, commandName);
                if (result.equals("fight")) {
                    break;
                }
                System.out.println(result);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String interpretCommand(String[] data, String commandName) {
        Executable command = this.commandInterpreter.interpretCommand(data, commandName);
        if (command != null) {
            return command.execute();
        }else {
            return "Invalid command";
        }
    }
}
