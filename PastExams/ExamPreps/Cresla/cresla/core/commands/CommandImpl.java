package cresla.core.commands;

import cresla.interfaces.Command;
import cresla.interfaces.Database;

import java.util.List;

abstract class CommandImpl implements Command {
    private Database database;

    protected CommandImpl(Database database) {
        this.database = database;
    }

    protected Database getDatabase() {
        return database;
    }
}
