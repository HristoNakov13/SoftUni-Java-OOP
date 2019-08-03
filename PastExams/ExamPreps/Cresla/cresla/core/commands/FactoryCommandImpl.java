package cresla.core.commands;

import cresla.interfaces.Database;
import cresla.interfaces.Factory;

abstract class FactoryCommandImpl extends CommandImpl {
    private Factory factory;

    protected FactoryCommandImpl(Database database, Factory factory) {
        super(database);
        this.factory = factory;
    }

    protected Factory getFactory() {
        return factory;
    }
}
