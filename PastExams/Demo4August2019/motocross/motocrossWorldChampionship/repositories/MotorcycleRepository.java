package motocrossWorldChampionship.repositories;

import motocrossWorldChampionship.common.ExceptionMessages;
import motocrossWorldChampionship.entities.interfaces.Motorcycle;

public class MotorcycleRepository extends RepositoryImpl<Motorcycle> {

    @Override
    public Motorcycle getByName(String name) {
        return super
                .getAll()
                .stream()
                .filter(motorcycle -> motorcycle.getModel().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void add(Motorcycle model) {
        if (super.getAll().contains(model)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.MOTORCYCLE_EXISTS, model.getModel()));
        } else {
            super.add(model);
        }
    }
}
