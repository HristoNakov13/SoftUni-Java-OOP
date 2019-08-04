package motocrossWorldChampionship.repositories;


import motocrossWorldChampionship.common.ExceptionMessages;
import motocrossWorldChampionship.entities.interfaces.Motorcycle;

public class MotorcycleRepository<T> extends RepositoryImpl<Motorcycle> {

    @Override
    public Motorcycle getByName(String name) {
        Motorcycle motorcycle = null;
        for (Motorcycle motor : super.getAll()) {

            if (motor.getModel().equals(name)) {
                motorcycle = motor;
            }
        }
        return motorcycle;
    }

    public void add(Motorcycle model) {
        Motorcycle searchForMotorcycle = this.getByName(model.getModel());
        if (searchForMotorcycle == null) {
            super.add(model);
        } else {
            throw new IllegalArgumentException(String.format(ExceptionMessages.MOTORCYCLE_EXISTS, model.getModel()));
        }
    }

    @Override
    public boolean remove(Motorcycle model) {
        return super.getAll().removeIf(motorcycle -> motorcycle.getModel().equals(model.getModel()));
    }
}
