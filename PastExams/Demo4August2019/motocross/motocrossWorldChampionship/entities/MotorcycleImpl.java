package motocrossWorldChampionship.entities;

import motocrossWorldChampionship.common.ExceptionMessages;
import motocrossWorldChampionship.entities.interfaces.Motorcycle;

public abstract class MotorcycleImpl implements Motorcycle {
    private static final int MINIMUM_VALID_NAME_LENGTH = 4;
    private String model;
    private int horsePower;
    private double cubicCentimeters;

    protected MotorcycleImpl(String model, int horsePower, double cubicCentimeters) {
        this.setModel(model);
        this.setHorsePower(horsePower);
        this.cubicCentimeters = cubicCentimeters;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public int getHorsePower() {
        return this.horsePower;
    }

    @Override
    public double getCubicCentimeters() {
        return this.cubicCentimeters;
    }

    @Override
    public double calculateRacePoints(int laps) {
        return this.getCubicCentimeters() / (this.getHorsePower() * laps);
    }

    protected void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    private void setModel(String model) {
        if (isValidModel(model)) {
            this.model = model;
        } else {
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_MODEL, model, MINIMUM_VALID_NAME_LENGTH));
        }
    }

    private boolean isValidModel(String model) {
        return model != null && !model.trim().isEmpty() && model.length() >= MINIMUM_VALID_NAME_LENGTH;
    }

    @Override
    public boolean equals(Object o) {

        if (!(o instanceof MotorcycleImpl)) {
            return false;
        }
        Motorcycle compare = (Motorcycle) o;
        return this.getModel().equals(compare.getModel());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
