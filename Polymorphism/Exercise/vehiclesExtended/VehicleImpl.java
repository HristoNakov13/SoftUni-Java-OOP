package vehiclesExtended;

import java.text.DecimalFormat;

public abstract class VehicleImpl implements Vehicle {
    private double fuelQuantity;
    private double fuelConsumption;
    private double tankCapacity;
    static final String DECIMAL_FORMAT_STR = "0.##";

    protected VehicleImpl(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption;
        this.tankCapacity = tankCapacity;
    }

    public double getFuelQuantity() {
        return fuelQuantity;
    }

    protected double getFuelConsumption() {
        return this.fuelConsumption;
    }

    protected double getTankCapacity() {
        return tankCapacity;
    }

    protected void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    protected String vehicleInfo() {
        return String.format("%s: %.2f", this.getClass().getSimpleName(), this.getFuelQuantity());
    }

    protected String drive(double distance, double fuelConsumption) {
        double remainingFuel = this.getFuelQuantity() - fuelConsumption * distance;
        if (remainingFuel < 0) {
            return String.format("%s needs refueling", this.getClass().getSimpleName());
        }
        this.setFuelQuantity(remainingFuel);
        DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_FORMAT_STR);
        return String.format("%s travelled %s km", this.getClass().getSimpleName(), decimalFormat.format(distance));
    }

    protected boolean needsToRefuel() {
        return this.fuelQuantity == 0;
    }

    protected boolean hasTankCapacityForRefuel(double refuelAmount) {
        return this.getFuelQuantity() + refuelAmount <= this.getTankCapacity();
    }

    protected boolean isValidFuelInput(double fuelQuantity) {
        return fuelQuantity > 0;
    }

    @Override
    public void refuel(double fuel) {
        if (!isValidFuelInput(fuel)) {
            throw new IllegalArgumentException("Fuel must be a positive number");
        }
        if (!hasTankCapacityForRefuel(fuel)) {
            throw new IllegalArgumentException("Cannot fit fuel in tank");
        }
        double added = this.getFuelQuantity() + fuel;
        this.setFuelQuantity(added);
    }
}
