package cresla.interfaces;

public interface Database {
    Reactor getReactorById(int id);

    void addReactor(Reactor reactor);

    boolean containsReactor(int id);

    int getTotalEnergyModules();

    int getTotalAbsorbingModules();

    void addEnergyModuleToReactor(int reactorID, EnergyModule energyModule);

    void addAbsorbingModuleToReactor(int reactorID, AbsorbingModule absorbingModule);

    Iterable<Reactor> getAllReactorsAsIterableCollection();
}
