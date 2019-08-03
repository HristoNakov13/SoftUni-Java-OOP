package cresla.interfaces;

import java.util.List;

public interface Factory {
    AbsorbingModule createCoolDownSystem(int heatAbsorbing);

    AbsorbingModule createHeatProcessor(int heatAbsorbing);

    EnergyModule createCryogenRod(int energyOutput);

    Reactor createCryoReactor(int cryoIndex, int moduleCapacity);

    Reactor createHeatReactor(int heatReductionIndex, int moduleCapacity);
}
