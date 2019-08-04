package motocrossWorldChampionship.repositories;

import motocrossWorldChampionship.common.ExceptionMessages;
import motocrossWorldChampionship.entities.interfaces.Motorcycle;
import motocrossWorldChampionship.entities.interfaces.Race;

public class RaceRepository extends RepositoryImpl<Race> {

    @Override
    public Race getByName(String name) {
        Race foundRace = null;

        for (Race race : super.getAll()) {
            if (race.getName().equals(name)) {
                foundRace = race;
                break;
            }
        }
        return foundRace;
    }

    public void add(Race model) {
        if (super.getAll().contains(model)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_EXISTS, model.getName()));
        } else {
            super.add(model);
        }
    }

    @Override
    public boolean remove(Race model) {
        return super.remove(model);
    }
}
