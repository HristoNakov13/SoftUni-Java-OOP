package barracksReturnOfTheDependancies.core;

import barracksReturnOfTheDependancies.contracts.Repository;
import barracksReturnOfTheDependancies.contracts.Runnable;
import barracksReturnOfTheDependancies.contracts.UnitFactory;
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

    private Repository repository;
    private UnitFactory unitFactory;

    public Engine(Repository repository, UnitFactory unitFactory) {
        this.repository = repository;
        this.unitFactory = unitFactory;
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

    private Repository getRepository() {
        return repository;
    }

    private UnitFactory getUnitFactory() {
        return unitFactory;
    }

    private String interpretCommand(String[] data, String commandName) {
        String result = null;

        String toUpper = String.valueOf(commandName.charAt(0)).toUpperCase() + commandName.substring(1);
        String clazzName = String.format("barracksReturnOfTheDependancies.core.commands.%s", toUpper);

        try {
            Class clazz = Class.forName(clazzName);
            Constructor constructor = clazz.getDeclaredConstructor(String[].class);
            Command instance = (Command) constructor.newInstance((Object) data);
            Method[] methods = clazz.getDeclaredMethods();

            for (Method method : methods) {
                if (method.isAnnotationPresent(Inject.class)) {

                    String repoClassName = this.getRepository().getClass().getName();
                    String expectedParameter = method.getParameterTypes()[0].getName();

                    if (repoClassName.equals(expectedParameter)) {
                        method.invoke(instance, this.getRepository());
                    }else {
                        method.invoke(instance, this.getUnitFactory());
                    }
                }
            }
            result = instance.execute();

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException ignore) {
        }
        return result;
    }
}
