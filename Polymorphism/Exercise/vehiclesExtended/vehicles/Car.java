package vehiclesExtended.vehicles;

import vehiclesExtended.VehicleImpl;

public class Car extends VehicleImpl {
    public Car(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
    }

//    @Override
//    public String drive(double distance) {
//        double remainingFuel = this.getFuelQuantity() - this.getFuelConsumption() * distance;
//        if (remainingFuel < 0) {
//            return "Car needs refueling";
//        }
//        this.setFuelQuantity(remainingFuel);
//        DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_FORMAT_STR);
//        return String.format("Car travelled %s km", decimalFormat.format(distance));
//    }
}
