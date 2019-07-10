package boatSimulator.models.boats;

import boatSimulator.models.ArgumentException;
import boatSimulator.models.engines.Engine;

public class PowerBoat extends BoatImpl implements MotorBoat{
    private Engine firstEngine;
    private Engine secondEngine;

    public PowerBoat(String model, int weight, Engine firstEngine, Engine secondEngine) throws ArgumentException {
        super(model, weight);
        this.firstEngine = firstEngine;
        this.secondEngine = secondEngine;
    }

    public Engine getFirstEngine() {
        return firstEngine;
    }

    public Engine getSecondEngine() {
        return secondEngine;
    }

    @Override
    public double calculateSpeed(double oceanCurrent) {
        double output = this.getFirstEngine().getOutput() + this.getSecondEngine().getOutput();
        double current = oceanCurrent / 5;
        return output - this.getWeight() + current;
    }
}
