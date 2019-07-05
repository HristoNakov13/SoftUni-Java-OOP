package vehiclesExtended;

public class Bus extends VehicleImpl {
    public Bus(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
    }


    public String drive(double distance) {
        double fuelConsumption = this.getFuelConsumptionWithPassengers();
        String status = super.drive(distance, fuelConsumption);
        return status;
    }

    public String driveEmpty(double distance) {
        double fuelConsumption = super.getFuelConsumption();
        String status = super.drive(distance, fuelConsumption);
        return status;
    }

    protected double getFuelConsumptionWithPassengers() {
        return super.getFuelConsumption() + 1.4;
    }
}
