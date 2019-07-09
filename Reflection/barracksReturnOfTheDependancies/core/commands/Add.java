package barracksReturnOfTheDependancies.core.commands;

import barracksReturnOfTheDependancies.contracts.Unit;
import barracksReturnOfTheDependancies.contracts.UnitFactory;
import barracksReturnOfTheDependancies.data.UnitRepository;

public class Add extends Command {
    private UnitRepository repository;
    private UnitFactory unitFactory;

    public Add(String[] data) {
        super(data);
    }

    public UnitRepository getRepository() {
        return repository;
    }

    @Inject
    public void setRepository(UnitRepository repository) {
        this.repository = repository;
    }

    public UnitFactory getUnitFactory() {
        return unitFactory;
    }

    @Inject
    public void setUnitFactory(UnitFactory unitFactory) {
        this.unitFactory = unitFactory;
    }

    @Override
    public String execute() {
        String unitType = super.getData()[1];
        Unit unitToAdd = this.getUnitFactory().createUnit(unitType);
        this.getRepository().addUnit(unitToAdd);
        return unitType + " added!";
    }
}
