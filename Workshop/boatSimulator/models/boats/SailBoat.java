package boatSimulator.models.boats;

import boatSimulator.models.ArgumentException;

public class SailBoat extends BoatImpl {
    private double sailEfficiency;
    private double windSpeed;

    public SailBoat(String model, int weight, double sailEfficiency) throws ArgumentException {
        super(model, weight);
        this.sailEfficiency = sailEfficiency;
    }

    private double getSailEfficiency() {
        return sailEfficiency;
    }

    @Inject()
    private void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    private double getWindSpeed() {
        return windSpeed;
    }

    @Override
    public double calculateSpeed(double oceanCurrent) {
        double sailEfficiency = this.getWindSpeed() * (this.getSailEfficiency() / 100.0);
        double current = oceanCurrent / 2.0;
        return sailEfficiency - this.getWeight() + current;
    }
}
