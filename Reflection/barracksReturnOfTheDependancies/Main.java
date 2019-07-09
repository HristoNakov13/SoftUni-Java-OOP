package barracksReturnOfTheDependancies;

import barracksReturnOfTheDependancies.contracts.Repository;
import barracksReturnOfTheDependancies.contracts.Runnable;
import barracksReturnOfTheDependancies.contracts.UnitFactory;
import barracksReturnOfTheDependancies.core.Engine;
import barracksReturnOfTheDependancies.core.factories.UnitFactoryImpl;
import barracksReturnOfTheDependancies.data.UnitRepository;

public class Main {

	public static void main(String[] args) {
		Repository repository = new UnitRepository();
		UnitFactory unitFactory = new UnitFactoryImpl();
		Runnable engine = new Engine(repository, unitFactory);
		engine.run();
	}
}
