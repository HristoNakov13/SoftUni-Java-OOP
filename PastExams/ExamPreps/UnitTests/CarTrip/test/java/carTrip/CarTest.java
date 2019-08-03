package carTrip;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CarTest {
    private static final String INVALID_NULL_MODEL = null;
    private static final String INVALID_EMPTY_MODEL = "";
    private static final String TEST_MODEL = "Audi";
    private static final double TEST_TANK_CAPACITY = 250.0;
    private static final double TEST_FUEL_AMOUNT = 100.0;
    private static final double INVALID_FUEL_AMOUNT = TEST_TANK_CAPACITY + 50.0;
    private static final double TEST_FUEL_CONSUMPTION = 5.0;
    private static final double INVALID_FUEL_CONSUMPTION = 0;
    private static final double TEST_TRAVEL_DISTANCE = 10.0;
    private static final double INVALID_TRAVEL_DISTANCE = TEST_TANK_CAPACITY;
    private static final double TEST_REFUEL_AMOUNT = 50.0;
    private static final double INVALID_REFUEL_AMOUNT = TEST_TANK_CAPACITY;
    private static final double DOUBLE_TEST_DELTA = 0.000000005;
    private static final String SUCCESSFUL_TRAVEL_MESSAGE = "Have a nice trip";
    private Car car;

    @Before
    public void setUp() {
        this.car = new Car(TEST_MODEL, TEST_TANK_CAPACITY, TEST_FUEL_AMOUNT, TEST_FUEL_CONSUMPTION);
    }

    @Test
    public void getModelShouldReturnCorrectValue() {
        String actualModel = this.car.getModel();

        Assert.assertEquals(TEST_MODEL, actualModel);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenSetNameWithNullValue() {
        this.car.setModel(INVALID_NULL_MODEL);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenSetNameWithEmptyValue() {
        this.car.setModel(INVALID_EMPTY_MODEL);
    }

    @Test
    public void getTankCapacityShouldReturnCorrectValue() {
        double actualTankCapacity = this.car.getTankCapacity();

        Assert.assertEquals(TEST_TANK_CAPACITY, actualTankCapacity, DOUBLE_TEST_DELTA);
    }

    @Test
    public void setTestTankCapacityShouldChangeCorrectly() {
        double actualNewCapacity = TEST_TANK_CAPACITY + TEST_TANK_CAPACITY;
        this.car.setTankCapacity(actualNewCapacity);
        double expected = this.car.getTankCapacity();

        Assert.assertEquals(expected, actualNewCapacity, DOUBLE_TEST_DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setFuelAmountShouldThrowWhenNegativeValueProvided() {
        this.car.setFuelAmount(INVALID_FUEL_AMOUNT);
    }

    @Test
    public void getFuelShouldReturnCorrectValue() {
        double actual = this.car.getFuelAmount();

        Assert.assertEquals(TEST_FUEL_AMOUNT, actual, DOUBLE_TEST_DELTA);
    }

    @Test
    public void setFuelShouldCorrectlyChangeFuelValue() {
        double newFuel = TEST_FUEL_AMOUNT + TEST_FUEL_AMOUNT;
        this.car.setFuelAmount(newFuel);
        double expectedFuelValue = this.car.getFuelAmount();

        Assert.assertEquals(expectedFuelValue, newFuel, DOUBLE_TEST_DELTA);
    }

    @Test
    public void getFuelConsumptionShouldReturnCorrectValue() {
        double actual = this.car.getFuelConsumptionPerKm();

        Assert.assertEquals(TEST_FUEL_CONSUMPTION, actual, DOUBLE_TEST_DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setFuelConsumptionShouldThrowWhenInvalidValueProvided() {
        this.car.setFuelConsumptionPerKm(INVALID_FUEL_CONSUMPTION);
    }

    @Test
    public void setFuelConsumptionShouldChangeValueCorrectly() {
        double expected = TEST_FUEL_CONSUMPTION + TEST_FUEL_CONSUMPTION;
        this.car.setFuelConsumptionPerKm(expected);
        double actual = this.car.getFuelConsumptionPerKm();

        Assert.assertEquals(expected, actual, DOUBLE_TEST_DELTA);
    }

    @Test(expected = IllegalStateException.class)
    public void driveShouldThrowWhenCarDoesNotHaveNeededFuel() {
        this.car.drive(INVALID_TRAVEL_DISTANCE);
    }

    @Test
    public void driveShouldRemoveExpendedFuelFromFuelAmount() {
        double fuelExpenditure = TEST_TRAVEL_DISTANCE * TEST_FUEL_CONSUMPTION;
        double expectedFuel = TEST_FUEL_AMOUNT - fuelExpenditure;
        this.car.drive(TEST_TRAVEL_DISTANCE);
        double actual = this.car.getFuelAmount();

        Assert.assertEquals(expectedFuel, actual, DOUBLE_TEST_DELTA);
    }

    @Test
    public void driveShouldReturnCorrectMessage() {
       String actual = this.car.drive(TEST_TRAVEL_DISTANCE);

       Assert.assertEquals(SUCCESSFUL_TRAVEL_MESSAGE, actual);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowWhenAddingGreaterFuelThanTankCapacity() {
        this.car.refuel(INVALID_FUEL_AMOUNT);
    }

    @Test
    public void refuelShouldSetFuelAmountCorrectly() {
        double expectedFuel = TEST_FUEL_AMOUNT + TEST_REFUEL_AMOUNT;
        this.car.refuel(TEST_REFUEL_AMOUNT);
        double actual = this.car.getFuelAmount();

        Assert.assertEquals(expectedFuel, actual, DOUBLE_TEST_DELTA);
    }
}