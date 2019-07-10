package boatSimulator.models.boats;

import boatSimulator.models.ArgumentException;
import boatSimulator.models.engines.Engine;

public class Yacht extends BoatImpl implements MotorBoat {
    private Engine engine;
    private int cargoWeight;

    public Yacht(String model, int weight, Engine engine, int cargoWeight) throws ArgumentException {
        super(model, weight);
        this.setCargoWeight(cargoWeight);
        this.engine = engine;
    }

    private void setEngine(Engine engine) {
        this.engine = engine;
    }

    private void setCargoWeight(int cargoWeight) throws ArgumentException {
        if (cargoWeight <= 0) {
            throw new ArgumentException("Cargo weight must be a positive integer.");
        }
        this.cargoWeight = cargoWeight;
    }

    public Engine getEngine() {
        return engine;
    }

    public int getCargoWeight() {
        return cargoWeight;
    }

    @Override
    public double calculateSpeed(double oceanCurrent) {
        double current = oceanCurrent / 2;
        double weight = this.getWeight() + this.getCargoWeight();
        double engineOutput = this.getEngine().getOutput();
        return engineOutput - weight + current;
    }
}
