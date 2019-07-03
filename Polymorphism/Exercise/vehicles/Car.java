package vehicles;

import java.text.DecimalFormat;

public class Car extends Vehicle {
    protected Car(double fuelQuantity, double fuelConsumption) {
        super(fuelQuantity, fuelConsumption);
    }


    String drive(double distance) {
        double remainingFuel = this.getFuelQuantity() - this.getFuelConsumption() * distance;
        if (remainingFuel < 0) {
            return "Car needs refueling";
        }
        this.setFuelQuantity(remainingFuel);
        DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_FORMAT_STR);
        return String.format("Car travelled %s km", decimalFormat.format(distance));
    }

    @Override
    protected double getFuelConsumption() {
        return super.getFuelConsumption() + 0.9;
    }

    @Override
    void refuel(double fuel) {
        double added = super.getFuelQuantity() + fuel;
        super.setFuelQuantity(fuel + super.getFuelQuantity());
    }
}
