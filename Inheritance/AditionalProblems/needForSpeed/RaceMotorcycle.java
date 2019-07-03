package needForSpeed;

public class RaceMotorcycle extends Motorcycle {
    static final int RACE_MOTORCYCLE_DEFAULT_FUEL_CONSUMPTION = 8;
    public RaceMotorcycle(double fuel, int horsePower) {
        super(fuel, horsePower);
        super.setFuelConsumption(RACE_MOTORCYCLE_DEFAULT_FUEL_CONSUMPTION);
    }
}
