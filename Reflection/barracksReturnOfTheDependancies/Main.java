package barracksReturnOfTheDependancies;

import barracksReturnOfTheDependancies.Initialization.Initialization;
import barracksReturnOfTheDependancies.contracts.CommandInterpreter;
import barracksReturnOfTheDependancies.contracts.Repository;
import barracksReturnOfTheDependancies.contracts.Runnable;
import barracksReturnOfTheDependancies.contracts.UnitFactory;
import barracksReturnOfTheDependancies.core.CommandInterpreterImpl;
import barracksReturnOfTheDependancies.core.Engine;
import barracksReturnOfTheDependancies.core.factories.UnitFactoryImpl;
import barracksReturnOfTheDependancies.data.UnitRepository;

public class Main {

	public static void main(String[] args) {
		Repository repository = Initialization.createUnitRepository();
		UnitFactory unitFactory = Initialization.createUnitFactory();
		CommandInterpreter commandInterpreter = Initialization.createCommandInterpreter(repository, unitFactory);
		Runnable engine = Initialization.createEngine(commandInterpreter);
		engine.run();
	}
}
