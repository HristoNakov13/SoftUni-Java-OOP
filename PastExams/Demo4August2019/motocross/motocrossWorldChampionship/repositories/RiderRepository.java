package motocrossWorldChampionship.repositories;

import motocrossWorldChampionship.common.ExceptionMessages;
import motocrossWorldChampionship.entities.interfaces.Rider;

public class RiderRepository extends RepositoryImpl<Rider> {

    @Override
    public Rider getByName(String name) {
        return super
                .getAll()
                .stream()
                .filter(rider -> rider.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void add(Rider model) {
        if (super.getAll().contains(model)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RIDER_EXISTS, model.getName()));
        } else {
            super.add(model);
        }
    }
}
