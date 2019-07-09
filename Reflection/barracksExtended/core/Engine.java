package barracksExtended.core;

import jdk.jshell.spi.ExecutionControl;
import barracksExtended.contracts.Repository;
import barracksExtended.contracts.Runnable;
import barracksExtended.contracts.Unit;
import barracksExtended.contracts.UnitFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
		String result = "fight";
		String toUpper = String.valueOf(commandName.charAt(0)).toUpperCase() + commandName.substring(1);
		String clazzName = String.format("barracksExtended.core.commands.%s", toUpper);
		try {
			Class clazz = Class.forName(clazzName);
			Constructor constructor = clazz.getDeclaredConstructors()[0];
			Object instance = constructor.newInstance(data, this.getRepository(), this.getUnitFactory());
			Method[] methods = clazz.getDeclaredMethods();

			for (Method method : methods) {
				if (method.getName().equals("execute")) {
					Object object = method.invoke(instance);
					result = (String) object;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException ignore) {
		}
		return result;
	}
	private String fightCommand(String[] data) {
		return "fight";
	}
}
