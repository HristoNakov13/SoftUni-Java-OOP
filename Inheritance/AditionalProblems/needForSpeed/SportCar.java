package needForSpeed;

public class SportCar extends Car {
    static final int SPORT_CAR_DEFAULT_FUEL_CONSUMPTION = 10;
    public SportCar(double fuel, int horsePower) {
        super(fuel, horsePower);
        super.setFuelConsumption(SPORT_CAR_DEFAULT_FUEL_CONSUMPTION);
    }
}
