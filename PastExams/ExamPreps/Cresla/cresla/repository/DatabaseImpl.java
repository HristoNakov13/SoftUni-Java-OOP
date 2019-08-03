package cresla.repository;

import cresla.interfaces.AbsorbingModule;
import cresla.interfaces.Database;
import cresla.interfaces.EnergyModule;
import cresla.interfaces.Reactor;

import java.util.LinkedHashMap;
import java.util.Map;

public class DatabaseImpl implements Database {
    private Map<Integer, Reactor> reactors;
    private int totalEnergyModules;
    private int totalAbsorbingModules;

    public DatabaseImpl() {
        this.reactors = new LinkedHashMap<>();
    }

    @Override
    public Reactor getReactorById(int id) {
        return this.reactors.get(id);
    }

    @Override
    public void addReactor(Reactor reactor) {
        if (reactor == null) {
            throw new NullPointerException("Cannot add null reactor to database");
        }
        this.reactors.put(reactor.getId(), reactor);
    }

    @Override
    public boolean containsReactor(int id) {
        return this.reactors.containsKey(id);
    }

    @Override
    public int getTotalEnergyModules() {
        return this.totalEnergyModules;
    }

    @Override
    public int getTotalAbsorbingModules() {
        return this.totalAbsorbingModules;
    }

    @Override
    public void addEnergyModuleToReactor(int reactorID, EnergyModule energyModule) {
        this.getReactorById(reactorID).addEnergyModule(energyModule);
        this.totalEnergyModules++;

    }

    @Override
    public void addAbsorbingModuleToReactor(int reactorID, AbsorbingModule absorbingModule) {
        this.getReactorById(reactorID).addAbsorbingModule(absorbingModule);
        this.totalAbsorbingModules++;
    }

    @Override
    public Iterable<Reactor> getAllReactorsAsIterableCollection() {
        return this.reactors.values();
    }
}
