package pr0304Barracks.core.factories;

import pr0304Barracks.contracts.Unit;
import pr0304Barracks.contracts.UnitFactory;
import pr0304Barracks.models.units.Archer;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class UnitFactoryImpl implements UnitFactory {


	private static final String UNITS_PACKAGE_NAME =
			"pr0304Barracks.models.units.";

	@Override
	public Unit createUnit(String unitType)  {
		String clazzName = "pr0304Barracks.models.units." + unitType;
		try {
			Class unit = Class.forName(clazzName);
			Constructor constructor = unit.getDeclaredConstructors()[0];
			constructor.setAccessible(true);
			Object trainUnit = constructor.newInstance();
			return (Unit)trainUnit;

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException ignore) {
		}
		throw new IllegalArgumentException("Wrong unit type: " + unitType);
	}
}
