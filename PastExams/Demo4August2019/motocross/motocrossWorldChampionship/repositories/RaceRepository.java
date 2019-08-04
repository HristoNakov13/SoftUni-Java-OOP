package motocrossWorldChampionship.repositories;

import motocrossWorldChampionship.common.ExceptionMessages;
import motocrossWorldChampionship.entities.interfaces.Race;

public class RaceRepository extends RepositoryImpl<Race> {

    @Override
    public Race getByName(String name) {
        Race foundRace = null;

        for (Race  race : super.getAll()) {
            if (race.getName().equals(name)) {
                foundRace = race;
                break;
            }
        }
        return foundRace;
    }

    public void add(Race model) {
        Race searchForRace = this.getByName(model.getName());
        if (searchForRace == null) {
            super.add(model);
        } else {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_EXISTS, model.getName()));
        }
    }

    @Override
    public boolean remove(Race model) {

        return super.getAll().removeIf(race -> race.getName().equals(model.getName()));
    }
}
