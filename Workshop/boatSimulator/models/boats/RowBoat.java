package boatSimulator.models.boats;

import boatSimulator.models.ArgumentException;

public class RowBoat extends BoatImpl {
    private int oars;

    public RowBoat(String model, int weight, int oars) throws ArgumentException {
        super(model, weight);
        this.setOars(oars);
    }

    private int getOars() {
        return oars;
    }

    private void setOars(int oars) throws ArgumentException {
        if (oars <= 0) {
            throw new ArgumentException("Oars must be a positive integer.");
        }
        this.oars = oars;
    }

    @Override
    public double calculateSpeed(double oceanCurrent) {
        double oarsSpeed = this.getOars() * 100;
        return oarsSpeed - this.getWeight() + oceanCurrent;
    }
}
