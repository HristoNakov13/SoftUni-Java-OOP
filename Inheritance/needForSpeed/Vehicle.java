package needForSpeed;

public class Vehicle {
    static final double DEFAULT_FUEL_CONSUMPTION;
    private double fuelConsumption;
    private double fuel;
    private int horsePower;

    static {
        DEFAULT_FUEL_CONSUMPTION = 1.25;
    }

    public Vehicle(double fuel, int horsePower) {
        this.fuel = fuel;
        this.horsePower = horsePower;
        this.fuelConsumption = DEFAULT_FUEL_CONSUMPTION;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public void drive(double kilometers) {
        double fuelSpent = this.getFuel() - kilometers * this.getFuelConsumption();
        if (fuelSpent >= 0) {
            this.setFuel(fuelSpent);
        }
    }
}
