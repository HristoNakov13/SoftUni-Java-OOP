package barracksExtended.core.commands;

import barracksExtended.contracts.Repository;
import barracksExtended.contracts.UnitFactory;

public class Report extends Command{
    public Report(String[] data, Repository repository, UnitFactory unitFactory) {
        super(data, repository, unitFactory);
    }

    @Override
    public String execute() {
        return super.getRepository().getStatistics();
    }
}
