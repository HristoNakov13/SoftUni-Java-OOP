package vehiclesExtended.vehicles;

import vehiclesExtended.VehicleImpl;

import java.text.DecimalFormat;

public class Bus extends VehicleImpl {
    public Bus(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
    }

    public String driveEmpty(double distance) {

        double remainingFuel = super.getFuelQuantity() - super.getFuelConsumption() * distance;
        if (remainingFuel < 0) {
            return "Bus needs refueling";
        }
        this.setFuelQuantity(remainingFuel);
        DecimalFormat decimalFormat = new DecimalFormat("0.##");
        return String.format("Bus travelled %s km", decimalFormat.format(distance));
    }
}
