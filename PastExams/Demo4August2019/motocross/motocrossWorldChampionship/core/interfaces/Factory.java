package motocrossWorldChampionship.core.interfaces;

import motocrossWorldChampionship.entities.interfaces.Motorcycle;
import motocrossWorldChampionship.entities.interfaces.Race;
import motocrossWorldChampionship.entities.interfaces.Rider;

public interface Factory {
    Motorcycle createMotorcycle(String type, String model, int horsePower);

    Race createRace(String name, int laps);

    Rider createRider(String name);
}
