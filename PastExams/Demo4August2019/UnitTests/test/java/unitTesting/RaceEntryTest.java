package unitTesting;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RaceEntryTest {
    private static final String EXISTING_RIDER = "Rider %s is already added";
    private static final String RIDER_INVALID = "Rider cannot be null.";
    private static final String RIDER_ADDED = "Rider %s added in race.";
    private static final int MIN_PARTICIPANTS = 2;
    private static final String RACE_INVALID = "The race cannot start with less than %d participants.";

    private static final int HORSEPOWER = 50;
    private static final double CUBIC_CENTIMETERS = 100.0;
    private static final String MODEL = "Honda";
    private static final UnitMotorcycle UNIT_MOTORCYCLE = new UnitMotorcycle(MODEL, HORSEPOWER, CUBIC_CENTIMETERS);
    private static final String RIDER_NAME = "Pesho";
    private static final UnitRider RIDER = new UnitRider(RIDER_NAME, UNIT_MOTORCYCLE);
    private static final String DEFAULT_RIDER_NAME = "Default";
    private static final UnitRider DEFAULT_RIDER =
            new UnitRider(DEFAULT_RIDER_NAME,
                    new UnitMotorcycle("default", 50, 5));
    private static final double AVERAGE_HORSEPOWER = 50.0;
    private static final double DELTA_TEST = 0.00000000000000000000001;

    private RaceEntry raceEntry;

    @Before
    public void setUp() {
        this.raceEntry = new RaceEntry();
        this.raceEntry.addRider(RIDER);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowWhenAddingNullRiderToRace() {
        this.raceEntry.addRider(null);
    }

    @Test
    public void nullRacerThrowShouldReturnCorrectMessage() {
        String actual = "QWERTY";
        try {
            this.raceEntry.addRider(null);
        } catch (NullPointerException e) {
            actual = e.getMessage();
        }

        Assert.assertEquals(RIDER_INVALID, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenRiderAlreadyAddedToRace() {
        this.raceEntry.addRider(RIDER);
    }

    @Test
    public void addDuplicateRiderShouldThrowCorrectMessage() {
        String actual = "QWERTY";
        try {
            this.raceEntry.addRider(RIDER);
        } catch (IllegalArgumentException e) {
            actual = e.getMessage();
        }

        Assert.assertEquals(String.format(EXISTING_RIDER, RIDER_NAME), actual);
    }

    @Test
    public void whenAddIsSuccessfulShouldReturnCorrectMessage() {
        String actual = this.raceEntry.addRider(DEFAULT_RIDER);

        Assert.assertEquals(String.format(RIDER_ADDED, DEFAULT_RIDER_NAME), actual);
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldThrowWhenMinimumParticipantsRequirementNotMet() {
        this.raceEntry.calculateAverageHorsePower();
    }

    @Test
    public void calculateHorsePowerMinimumParticipantsNotMetExceptionShouldReturnCorrectMessage() {
        String actual = "QWERTY";
        try {
            this.raceEntry.calculateAverageHorsePower();
        } catch (IllegalArgumentException e) {
            actual = e.getMessage();
        }

        Assert.assertEquals(String.format(RACE_INVALID, MIN_PARTICIPANTS), actual);
    }

    @Test
    public void shouldReturnCorrectAverageHorsePower() {
        this.raceEntry.addRider(DEFAULT_RIDER);
        BigDecimal actualAverageHorsePower = BigDecimal.valueOf(this.raceEntry.calculateAverageHorsePower());

        Assert.assertEquals(BigDecimal.valueOf(AVERAGE_HORSEPOWER), actualAverageHorsePower);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowWhenModifyingReturnedCollection(){
        this.raceEntry.getRiders().remove(RIDER);
        this.raceEntry.getRiders().add(DEFAULT_RIDER);

    }
}
