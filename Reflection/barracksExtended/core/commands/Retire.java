package barracksExtended.core.commands;

import barracksExtended.contracts.Repository;
import barracksExtended.contracts.UnitFactory;

public class Retire extends Command {
    public Retire(String[] data, Repository repository, UnitFactory unitFactory) {
        super(data, repository, unitFactory);
    }

    @Override
    public String execute() {
        String unitType = getData()[1];
        this.getRepository().removeUnit(unitType);
        return "";
    }
}
