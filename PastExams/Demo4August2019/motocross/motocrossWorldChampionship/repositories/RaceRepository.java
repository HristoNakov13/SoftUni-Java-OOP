package motocrossWorldChampionship.repositories;

import motocrossWorldChampionship.common.ExceptionMessages;
import motocrossWorldChampionship.entities.interfaces.Motorcycle;
import motocrossWorldChampionship.entities.interfaces.Race;
import motocrossWorldChampionship.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RaceRepository implements Repository<Race> {
    private List<Race> models;

    public RaceRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Race getByName(String name) {
        return this
                .getAll()
                .stream()
                .filter(race -> race.getName().equals(name))
                .findFirst()
                .orElse(null);
    }


    @Override
    public Collection<Race> getAll() {
        return Collections.unmodifiableCollection(this.models);
    }

    @Override
    public void add(Race model) {
        if (this.getAll().contains(model)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_EXISTS, model.getName()));
        } else {
            this.models.add(model);
        }
    }

    @Override
    public boolean remove(Race model) {
        return this.models.remove(model);
    }
}
