package motocrossWorldChampionship.entities;

import motocrossWorldChampionship.common.ExceptionMessages;

public class SpeedMotorcycle extends MotorcycleImpl {
    private static final double DEFAULT_CUBIC_CENTIMETERS = 125.0;
    private static final int MINIMUM_HORSEPOWER = 50;
    private static final int MAXIMUM_HORSEPOWER = 69;

    public SpeedMotorcycle(String model, int horsePower) {
        super(model, horsePower, DEFAULT_CUBIC_CENTIMETERS);
    }

    @Override
    protected void setHorsePower(int horsePower) {
        if (isValidHorsePower(horsePower)) {
            super.setHorsePower(horsePower);
        } else {
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_HORSE_POWER, horsePower));
        }
    }

    private boolean isValidHorsePower(int horsePower) {
        return horsePower >= MINIMUM_HORSEPOWER && horsePower <= MAXIMUM_HORSEPOWER;
    }
}
