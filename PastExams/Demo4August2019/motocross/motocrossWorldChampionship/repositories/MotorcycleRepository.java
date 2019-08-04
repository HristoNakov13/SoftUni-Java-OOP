package motocrossWorldChampionship.repositories;

import motocrossWorldChampionship.common.ExceptionMessages;
import motocrossWorldChampionship.entities.interfaces.Motorcycle;

public class MotorcycleRepository extends RepositoryImpl<Motorcycle> {

    @Override
    public Motorcycle getByName(String name) {
        Motorcycle motorcycle = null;

        for (Motorcycle motor : super.getAll()) {
            if (motor.getModel().equals(name)) {
                motorcycle = motor;
                break;
            }
        }
        return motorcycle;
    }

    public void add(Motorcycle model) {
        if (super.getAll().contains(model)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.MOTORCYCLE_EXISTS, model.getModel()));
        } else {
            super.add(model);
        }
    }

    @Override
    public boolean remove(Motorcycle model) {
        return super.getAll().remove(model);
    }
}
