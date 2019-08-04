package motocrossWorldChampionship.core;

import motocrossWorldChampionship.core.interfaces.Factory;
import motocrossWorldChampionship.entities.PowerMotorcycle;
import motocrossWorldChampionship.entities.RaceImpl;
import motocrossWorldChampionship.entities.RiderImpl;
import motocrossWorldChampionship.entities.SpeedMotorcycle;
import motocrossWorldChampionship.entities.interfaces.Motorcycle;
import motocrossWorldChampionship.entities.interfaces.Race;
import motocrossWorldChampionship.entities.interfaces.Rider;

public class FactoryImpl implements Factory {
    @Override
    public Motorcycle createMotorcycle(String type, String model, int horsePower) {
        Motorcycle motorcycle = null;
        if (type.equals("Power")) {
            motorcycle = new PowerMotorcycle(model, horsePower);
        } else if (type.equals("Speed")) {
            motorcycle = new SpeedMotorcycle(model, horsePower);
        }
        return motorcycle;
    }

    @Override
    public Race createRace(String name, int laps) {
        return new RaceImpl(name, laps);
    }

    @Override
    public Rider createRider(String name) {
        return new RiderImpl(name);
    }
}
