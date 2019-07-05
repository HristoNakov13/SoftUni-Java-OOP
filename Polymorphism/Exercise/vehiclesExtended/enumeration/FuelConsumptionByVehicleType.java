package vehiclesExtended.enumeration;

public enum FuelConsumptionByVehicleType {
    Truck(1.6),

    Car(0.9),

    Bus(1.4);

    private double value;

    FuelConsumptionByVehicleType(double value) {
        this.value = value;
    }

    public double getFuelConsumption() {
        return this.value;
    }
}
