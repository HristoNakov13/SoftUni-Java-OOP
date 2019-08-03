package cresla.core.commands;

import cresla.interfaces.Database;

import java.util.List;

public class ReportCommand extends CommandImpl {
    public ReportCommand(Database database) {
        super(database);
    }

    @Override
    public String execute(List<String> args) {
        int reactorID = Integer.parseInt(args.get(0));
        return super.getDatabase().getReactorById(reactorID).toString();
    }
}
