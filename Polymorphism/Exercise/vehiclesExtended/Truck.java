package vehiclesExtended;

public class Truck extends VehicleImpl {
    public Truck(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
    }


    public String drive(double distance) {
        double fuelConsumption = this.getFuelConsumption();
        String status = super.drive(distance, fuelConsumption);
        return status;
    }

    @Override
    protected double getFuelConsumption() {
        return super.getFuelConsumption() + 1.6;
    }

    @Override
    public void refuel(double fuel) {
        if (!isValidFuelInput(fuel)) {
            throw new IllegalArgumentException("Fuel must be a positive number");
        }
        if (!hasTankCapacityForRefuel(fuel)) {
            throw new IllegalArgumentException("Cannot fit fuel in tank");
        }
        super.setFuelQuantity(super.getFuelQuantity() + fuel * 0.95);
    }
}
