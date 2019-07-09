package barracksReturnOfTheDependancies.core.commands;

import barracksReturnOfTheDependancies.data.UnitRepository;

public class Retire extends Command {
    private UnitRepository repository;

    public Retire(String[] data) {
        super(data);
    }

    private UnitRepository getRepository() {
        return repository;
    }

    @Inject
    private void setRepository(UnitRepository repository) {
        this.repository = repository;
    }

    @Override
    public String execute() {
        String unitType = getData()[1];
        this.getRepository().removeUnit(unitType);
        return "";
    }
}
