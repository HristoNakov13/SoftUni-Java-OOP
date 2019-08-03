package cresla.core.commands;

import cresla.interfaces.Database;
import cresla.interfaces.Factory;
import cresla.interfaces.Reactor;

import java.util.List;


public class ReactorCommand extends FactoryCommandImpl {
    public ReactorCommand(Database database, Factory factory) {
        super(database, factory);
    }

    @Override
    public String execute(List<String> args) {
        String reactorType = args.get(0);
        int reactorIndexModifier = Integer.parseInt(args.get(1));
        int moduleCapacity = Integer.parseInt(args.get(2));
        Reactor reactor = null;

        if (reactorType.equals("Heat")) {
            reactor = super.getFactory().createHeatReactor(reactorIndexModifier, moduleCapacity);
        } else if (reactorType.equals("Cryo")) {
            reactor = super.getFactory().createCryoReactor(reactorIndexModifier, moduleCapacity);
        }
        super.getDatabase().addReactor(reactor);

        return String.format("Created %s Reactor - %d", reactorType, reactor.getId());
    }
}
