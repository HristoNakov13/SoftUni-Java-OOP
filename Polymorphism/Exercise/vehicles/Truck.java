package vehicles;

import java.text.DecimalFormat;

public class Truck extends Vehicle{
    protected Truck(double fuelQuantity, double fuelConsumption) {
        super(fuelQuantity, fuelConsumption);
    }

    String drive(double distance) {
        double remainingFuel = this.getFuelQuantity() - this.getFuelConsumption() * distance;
        if (remainingFuel < 0) {
            return "Truck needs refueling";
        }
        this.setFuelQuantity(remainingFuel);
        DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_FORMAT_STR);
        return String.format("Truck travelled %s km", decimalFormat.format(distance));
    }

        @Override
    protected double getFuelConsumption() {
        return super.getFuelConsumption() + 1.6;
    }

    @Override
    void refuel(double fuel) {
        super.setFuelQuantity(super.getFuelQuantity() + fuel * 0.95);
    }
}
