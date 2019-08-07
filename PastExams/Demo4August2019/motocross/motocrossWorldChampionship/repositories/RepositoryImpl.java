package motocrossWorldChampionship.repositories;

import motocrossWorldChampionship.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class RepositoryImpl<T> implements Repository<T> {
    private List<T> models;

    protected RepositoryImpl() {
        this.models = new ArrayList<>();
    }

    @Override
    public T getByName(String name) {
        return null;
    }

    @Override
    public Collection<T> getAll() {
        return this.models;
    }

    @Override
    public void add(T model) {
        this.models.add(model);
    }

    @Override
    public boolean remove(T model) {
        return this.models.remove(model);
    }
}
