package barracksReturnOfTheDependancies;

import barracksReturnOfTheDependancies.initializator.Initialization;
import barracksReturnOfTheDependancies.contracts.CommandInterpreter;
import barracksReturnOfTheDependancies.contracts.Repository;
import barracksReturnOfTheDependancies.contracts.Runnable;
import barracksReturnOfTheDependancies.contracts.UnitFactory;

public class Main {

	public static void main(String[] args) {
		Repository repository = Initialization.createUnitRepository();
		UnitFactory unitFactory = Initialization.createUnitFactory();
		CommandInterpreter commandInterpreter = Initialization.createCommandInterpreter(repository, unitFactory);
		Runnable engine = Initialization.createEngine(commandInterpreter);
		engine.run();
	}
}
