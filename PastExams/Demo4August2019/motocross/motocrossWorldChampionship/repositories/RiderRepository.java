package motocrossWorldChampionship.repositories;

import motocrossWorldChampionship.common.ExceptionMessages;
import motocrossWorldChampionship.entities.interfaces.Rider;

public class RiderRepository extends RepositoryImpl<Rider> {

    @Override
    public Rider getByName(String name) {
        Rider foundRider = null;

        for (Rider rider : super.getAll()) {
            if (rider.getName().equals(name)) {
                foundRider = rider;
                break;
            }
        }
        return foundRider;
    }

    public void add(Rider model) {

        if (super.getAll().contains(model)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RIDER_EXISTS, model.getName()));
        } else {
            super.add(model);
        }
    }

    @Override
    public boolean remove(Rider model) {
        return super.remove(model);
    }
}
