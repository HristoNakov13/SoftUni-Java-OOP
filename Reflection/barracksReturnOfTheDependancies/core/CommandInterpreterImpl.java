package barracksReturnOfTheDependancies.core;

import barracksReturnOfTheDependancies.contracts.CommandInterpreter;
import barracksReturnOfTheDependancies.contracts.Executable;
import barracksReturnOfTheDependancies.contracts.Repository;
import barracksReturnOfTheDependancies.contracts.UnitFactory;
import barracksReturnOfTheDependancies.core.commands.Command;
import barracksReturnOfTheDependancies.core.commands.Inject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CommandInterpreterImpl implements CommandInterpreter {
    private Repository unitRepository;
    private UnitFactory unitFactory;

    public CommandInterpreterImpl(Repository unitRepository, UnitFactory unitFactory) {
        this.unitRepository = unitRepository;
        this.unitFactory = unitFactory;
    }

    public Repository getUnitRepository() {
        return unitRepository;
    }

    public UnitFactory getUnitFactory() {
        return unitFactory;
    }

    @Override
    public Executable interpretCommand(String[] data, String commandName) {

        String toUpper = String.valueOf(commandName.charAt(0)).toUpperCase() + commandName.substring(1);
        String clazzName = String.format("barracksReturnOfTheDependancies.core.commands.%s", toUpper);
        Executable result = null;

        try {
            Class clazz = Class.forName(clazzName);
            Constructor constructor = clazz.getDeclaredConstructor(String[].class);
            Command instance = (Command) constructor.newInstance((Object) data);
            Method[] methods = clazz.getDeclaredMethods();

            for (Method method : methods) {
                if (method.isAnnotationPresent(Inject.class)) {
                    method.setAccessible(true);

                    String repoClassName = this.getUnitRepository().getClass().getName();
                    String expectedParameter = method.getParameterTypes()[0].getName();

                    if (repoClassName.equals(expectedParameter)) {
                        method.invoke(instance, this.getUnitRepository());
                    }else {
                        method.invoke(instance, this.getUnitFactory());
                    }
                }
            }
            result = instance;

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException ignore) {
        }
        return result;
    }
}
