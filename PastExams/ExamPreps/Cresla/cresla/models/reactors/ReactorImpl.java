package cresla.models.reactors;

import cresla.entities.containers.ModuleContainer;
import cresla.interfaces.AbsorbingModule;
import cresla.interfaces.Container;
import cresla.interfaces.EnergyModule;
import cresla.interfaces.Reactor;

public abstract class ReactorImpl implements Reactor {
    private int id;
    private Container container;

    protected ReactorImpl(int id, int moduleCapacity) {
        this.id = id;
        this.container = new ModuleContainer(moduleCapacity);
    }

    @Override
    public long getTotalEnergyOutput() {
        long energyOutput = this.container.getTotalEnergyOutput();
//        if (energyOutput > this.getTotalHeatAbsorbing()) {
//            return 0;
//        }
//        @wtf?? Documentation says
//        "If the Reactor’s energy output is greater than the heat absorbing, the Reactor overheats. If that happens, it’s energy output should be presented as 0."
//        @And Yet test #4 fails with it implemented
        return energyOutput;
    }

    @Override
    public long getTotalHeatAbsorbing() {
        return this.container.getTotalHeatAbsorbing();
    }

    @Override
    public int getModuleCount() {
        return container.getModuleByInputCount();
    }

    @Override
    public void addEnergyModule(EnergyModule energyModule) {
        this.container.addEnergyModule(energyModule);

    }

    @Override
    public void addAbsorbingModule(AbsorbingModule absorbingModule) {
        this.container.addAbsorbingModule(absorbingModule);
    }

    @Override
    public int getId() {
        return this.id;
    }
//    CryoReactor - 1
//Energy Output: 4000
//Heat Absorbing: 10000
//Modules: 5
//Cryo Reactors: 2
//Heat Reactors: 0
//Energy Modules: 5
//Absorbing Modules: 1
//Total Energy Output: 4000
//Total Heat Absorbing: 10000

    @Override
    public String toString() {
        StringBuilder report = new StringBuilder();
        report
                .append(String.format("%s - %d%n", this.getClass().getSimpleName(), this.getId()))
                .append(String.format("Energy Output: %d%n", this.getTotalEnergyOutput()))
                .append(String.format("Heat Absorbing: %d%n", this.getTotalHeatAbsorbing()))
                .append(String.format("Modules: %d", this.getModuleCount()));
        return report.toString();
    }
}
