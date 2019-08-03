package cresla.models.reactors;

public class CryoReactor extends ReactorImpl {
    private int cryoProductionIndex;

    public CryoReactor(int id, int moduleCapacity, int cryoProductionIndex) {
        super(id, moduleCapacity);
        this.cryoProductionIndex = cryoProductionIndex;
    }

    @Override
    public long getTotalEnergyOutput() {
        long energyOutput = super.getTotalEnergyOutput() * this.cryoProductionIndex;
//        if (energyOutput > super.getTotalHeatAbsorbing()) {
//            energyOutput = 0;
//        }
       return energyOutput;
    }
}
