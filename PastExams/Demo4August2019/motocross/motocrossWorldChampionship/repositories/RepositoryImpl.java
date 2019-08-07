package motocrossWorldChampionship.repositories;

import motocrossWorldChampionship.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class RepositoryImpl<T> implements Repository<T> {
    private List<T> collection;

    protected RepositoryImpl() {
        this.collection = new ArrayList<>();
    }

    @Override
    public T getByName(String name) {
        return null;
    }

    @Override
    public Collection<T> getAll() {
        return this.collection;
    }

    @Override
    public void add(T model) {
        this.collection.add(model);
    }

    @Override
    public boolean remove(T model) {
        return this.collection.remove(model);
    }
}
