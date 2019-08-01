package parkingSystem;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class SoftParkTest {
    private static final Car DEFAULT_CAR = new Car("MAKE", "NUMBER");
    private static final String TEST_CAR_REGISTRATION_NUMBER = "5014";
    private static final String TEST_CAR_MAKE = "BENZ";
    private static final Car TEST_CAR = new Car(TEST_CAR_MAKE, TEST_CAR_REGISTRATION_NUMBER);
    private static final int PARKING_SIZE = 12;
    private static final String INVALID_PARK_SPOT = "Invalid";
    private static final String DEFAULT_CAR_SPOT = "A1";
    private static final String TEST_SPOT = "A2";
    private static final String EXPECTED_CAR_INFO = String.format("Car:%s parked successfully!", TEST_CAR_REGISTRATION_NUMBER);
    private SoftPark softPark;
    private Map<String, Car> parking;

    @Before
    public void setUp() {
        this.softPark = new SoftPark();
        this.parking = this.softPark.getParking();
    }

    @Test
    public void shouldCreateAllFreeSpotsUponInitialization() {
        int actualSpots = this.softPark.getParking().size();
        Assert.assertEquals(PARKING_SIZE, actualSpots);
    }

    @Test
    public void shouldReturnCorrectCarInfoWhenParking() {
        String actual = this.softPark.parkCar(TEST_SPOT, TEST_CAR);
        Assert.assertEquals(EXPECTED_CAR_INFO, actual);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowWhenTryingToModifyParkingCollection() {
        this.parking.put("A4", TEST_CAR);
        this.parking.remove("lol");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenParkingAtNonExistingSpot() {
        this.softPark.parkCar(INVALID_PARK_SPOT, TEST_CAR);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenTryingToParkAtTakenSpot() {
        this.softPark.parkCar(DEFAULT_CAR_SPOT, TEST_CAR);
        this.softPark.parkCar(DEFAULT_CAR_SPOT, DEFAULT_CAR);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenCarIsAlreadyParked() {
        this.softPark.parkCar(TEST_SPOT, DEFAULT_CAR);
        this.softPark.parkCar(TEST_SPOT, TEST_CAR);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenRemovingCarFromNonExistingSpot() {
        this.softPark.removeCar(INVALID_PARK_SPOT, TEST_CAR);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenCarAtSpotDoesntExist() {
        this.softPark.removeCar(TEST_SPOT, TEST_CAR);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenRemovingDifferentCarAtSpot() {
        this.softPark.parkCar(TEST_SPOT, TEST_CAR);
        this.softPark.removeCar(TEST_SPOT, DEFAULT_CAR);
    }

    @Test
    public void shouldSetFreedCarSpotAsNull() {
        this.softPark.parkCar(TEST_SPOT, TEST_CAR);
        this.softPark.removeCar(TEST_SPOT, TEST_CAR);
        Assert.assertNull(this.softPark.getParking().get(TEST_SPOT));
    }

    @Test
  public void shouldReturnCorrectMessageWhenRemovingCar() {
      this.softPark.parkCar(TEST_SPOT, TEST_CAR);
      String actual = this.softPark.removeCar(TEST_SPOT, TEST_CAR);
      Assert.assertEquals(String.format("Remove car:%s successfully!", TEST_CAR_REGISTRATION_NUMBER), actual);
    }
}