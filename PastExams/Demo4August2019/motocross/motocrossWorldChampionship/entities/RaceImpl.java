package motocrossWorldChampionship.entities;

import motocrossWorldChampionship.common.ExceptionMessages;
import motocrossWorldChampionship.entities.interfaces.Race;
import motocrossWorldChampionship.entities.interfaces.Rider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RaceImpl implements Race {
    private static final int MINIMUM_VALID_NAME_LENGTH = 5;
    private static final int MINIMUM_LAPS_ALLOWED = 1;
    private String name;
    private int laps;
    private List<Rider> riders;

    public RaceImpl(String name, int laps) {
        this.setName(name);
        this.setLaps(laps);
        this.riders = new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLaps() {
        return this.laps;
    }

    @Override
    public Collection<Rider> getRiders() {
        return this.riders;
    }

    @Override
    public void addRider(Rider rider) {
        this.validateRider(rider);
        this.riders.add(rider);
    }

    private void validateRider(Rider rider) {
        if (rider == null) {
            throw new NullPointerException(ExceptionMessages.RIDER_INVALID);
        }
        String riderName = rider.getName();

        if (!rider.getCanParticipate()) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RIDER_NOT_PARTICIPATE, riderName));
        }

        if (this.riders.contains(rider)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RIDER_ALREADY_ADDED, riderName, this.getName()));
        }
    }

    private void setName(String name) {
        if (!isValidName(name)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_NAME, name, MINIMUM_VALID_NAME_LENGTH));
        }
        this.name = name;
    }

    private boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty() && name.length() >= MINIMUM_VALID_NAME_LENGTH;
    }

    private void setLaps(int laps) {
        if (laps < MINIMUM_LAPS_ALLOWED) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NUMBER_OF_LAPS);
        }
        this.laps = laps;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Race compare = (Race) o;
        return this.getName().equals(compare.getName());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
