package barracksReturnOfTheDependancies.initializator;

import barracksReturnOfTheDependancies.contracts.CommandInterpreter;
import barracksReturnOfTheDependancies.contracts.Repository;
import barracksReturnOfTheDependancies.contracts.UnitFactory;
import barracksReturnOfTheDependancies.core.CommandInterpreterImpl;
import barracksReturnOfTheDependancies.core.Engine;
import barracksReturnOfTheDependancies.core.factories.UnitFactoryImpl;
import barracksReturnOfTheDependancies.data.UnitRepository;

public class Initialization {

    public static CommandInterpreter createCommandInterpreter (Repository repository, UnitFactory factory) {
        return new CommandInterpreterImpl(repository, factory);
    }

    public static Engine createEngine(CommandInterpreter interpreter) {
        return new Engine(interpreter);
    }

    public static UnitFactory createUnitFactory() {
        return new UnitFactoryImpl();
    }

    public static Repository createUnitRepository() {
        return new UnitRepository();
    }
}
