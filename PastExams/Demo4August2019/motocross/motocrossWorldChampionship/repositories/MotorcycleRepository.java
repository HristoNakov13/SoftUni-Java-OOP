package motocrossWorldChampionship.repositories;

import motocrossWorldChampionship.common.ExceptionMessages;
import motocrossWorldChampionship.entities.interfaces.Motorcycle;
import motocrossWorldChampionship.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MotorcycleRepository implements Repository<Motorcycle> {
    private Collection<Motorcycle> models;

    public MotorcycleRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Motorcycle getByName(String name) {
        return this
                .getAll()
                .stream()
                .filter(motorcycle -> motorcycle.getModel().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<Motorcycle> getAll() {
        return Collections.unmodifiableCollection(this.models);
    }

    @Override
    public void add(Motorcycle model) {
        if (this.getAll().contains(model)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.MOTORCYCLE_EXISTS, model.getModel()));
        } else {
            this.models.add(model);
        }
    }

    @Override
    public boolean remove(Motorcycle model) {
        return this.models.remove(model);
    }
}
