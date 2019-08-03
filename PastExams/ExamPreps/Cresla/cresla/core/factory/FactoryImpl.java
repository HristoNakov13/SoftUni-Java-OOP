package cresla.core.factory;

import cresla.interfaces.AbsorbingModule;
import cresla.interfaces.EnergyModule;
import cresla.interfaces.Factory;
import cresla.interfaces.Reactor;
import cresla.models.modules.CooldownSystem;
import cresla.models.modules.CryogenRod;
import cresla.models.modules.HeatProcessor;
import cresla.models.reactors.CryoReactor;
import cresla.models.reactors.HeatReactor;


public class FactoryImpl implements Factory {
    private static final int STARTING_NUMBER_OF_IDS = 1;
    private int id;

    public FactoryImpl() {
        this.id = STARTING_NUMBER_OF_IDS;
    }

    @Override
    public AbsorbingModule createCoolDownSystem(int heatAbsorbing) {
        return new CooldownSystem(this.assignID(), heatAbsorbing);
    }

    @Override
    public AbsorbingModule createHeatProcessor(int heatAbsorbing) {
        return new HeatProcessor(this.assignID(), heatAbsorbing);
    }

    @Override
    public EnergyModule createCryogenRod(int energyOutput) {
        return new CryogenRod(this.assignID(), energyOutput);
    }

    @Override
    public Reactor createCryoReactor(int cryoIndex, int moduleCapacity) {
        return new CryoReactor(this.assignID(), moduleCapacity, cryoIndex);
    }

    @Override
    public Reactor createHeatReactor(int heatReductionIndex, int moduleCapacity) {
        return new HeatReactor(this.assignID(), moduleCapacity, heatReductionIndex);
    }

    private int assignID() {
        return this.id++;
    }
}
