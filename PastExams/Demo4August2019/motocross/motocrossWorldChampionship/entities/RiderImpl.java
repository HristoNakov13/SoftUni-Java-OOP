package motocrossWorldChampionship.entities;

import motocrossWorldChampionship.common.ExceptionMessages;
import motocrossWorldChampionship.entities.interfaces.Motorcycle;
import motocrossWorldChampionship.entities.interfaces.Rider;

public class RiderImpl implements Rider {
    private static final int MINIMUM_VALID_NAME_LENGTH = 5;
    private String name;
    private Motorcycle motorcycle;
    private int numberOfWins;
    private boolean canParticipate;

    public RiderImpl(String name) {
        this.setName(name);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Motorcycle getMotorcycle() {
        return this.motorcycle;
    }

    @Override
    public int getNumberOfWins() {
        return this.numberOfWins;
    }

    @Override
    public void addMotorcycle(Motorcycle motorcycle) {
        if (motorcycle == null) {
            throw new IllegalArgumentException(ExceptionMessages.MOTORCYCLE_INVALID);
        }
        this.motorcycle = motorcycle;
        this.canParticipate = true;
    }

    @Override
    public void winRace() {
        this.numberOfWins++;
    }

    @Override
    public boolean getCanParticipate() {
        return this.canParticipate;
    }

    private void setName(String name) {
        if (this.isValidName(name)) {
            this.name = name;
        } else {
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_NAME, name, MINIMUM_VALID_NAME_LENGTH));
        }
    }

    private boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty() && name.length() >= MINIMUM_VALID_NAME_LENGTH;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Rider compare = (Rider)o;
        return this.getName().equals(compare.getName());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
