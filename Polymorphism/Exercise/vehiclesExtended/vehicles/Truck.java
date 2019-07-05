package vehiclesExtended.vehicles;

import vehiclesExtended.VehicleImpl;

public class Truck extends VehicleImpl {
    public Truck(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
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
