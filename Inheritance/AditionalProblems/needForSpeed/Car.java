package needForSpeed;

public class Car extends Vehicle {
    static final int CAR_DEFAULT_FUEL_CONSUMPTION = 3;
    public Car(double fuel, int horsePower) {
        super(fuel, horsePower);
        super.setFuelConsumption(CAR_DEFAULT_FUEL_CONSUMPTION);
    }
}
