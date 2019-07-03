package vehicles;

public abstract class Vehicle {
    private double fuelQuantity;
    private double fuelConsumption;
    static final String DECIMAL_FORMAT_STR = "0.##";

    protected Vehicle(double fuelQuantity, double fuelConsumption) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption;
    }

    protected double getFuelQuantity() {
        return fuelQuantity;
    }

    protected double getFuelConsumption() {
        return this.fuelConsumption;
    }

    protected void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    public String vehicleInfo() {
        return String.format("%s: %.2f", this.getClass().getSimpleName(), this.getFuelQuantity());
    }

    protected boolean needsToRefuel() {
        return this.fuelQuantity == 0;
    }

    abstract String drive(double distance);
    abstract void refuel(double fuel);
}
