package motocrossWorldChampionship.repositories;

import motocrossWorldChampionship.common.ExceptionMessages;
import motocrossWorldChampionship.entities.interfaces.Motorcycle;
import motocrossWorldChampionship.entities.interfaces.Race;

public class RaceRepository extends RepositoryImpl<Race> {

    @Override
    public Race getByName(String name) {
        return super
                .getAll()
                .stream()
                .filter(race -> race.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void add(Race model) {
        if (super.getAll().contains(model)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_EXISTS, model.getName()));
        } else {
            super.add(model);
        }
    }
}
