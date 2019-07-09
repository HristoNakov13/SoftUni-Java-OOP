package pr02PrivateClassFiddling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		String findClass = "pr02PrivateClassFiddling.com.BlackBoxInt";
		Class blackBox = Class.forName(findClass);

		Constructor constructor = blackBox.getDeclaredConstructors()[1];
		constructor.setAccessible(true);
		Object instance = constructor.newInstance();

		String input;
		while (!"END".equals(input = bfr.readLine())) {
			String[] line = input.split("_");
			String methodName = line[0];
			int value = Integer.parseInt(line[1]);

			try {
				Method method = blackBox.getDeclaredMethod(methodName, int.class);
				method.setAccessible(true);
				method.invoke(instance, value);
				Field field = blackBox.getDeclaredFields()[1];
				field.setAccessible(true);
				Object fieldValue = field.get(instance);
				int result = (int) fieldValue;
				System.out.println(result);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
	}
}
