package boatSimulator.models.boats;

import boatSimulator.models.ArgumentException;

abstract class BoatImpl implements Boat{
    private String model;
    private int weight;

    protected BoatImpl(String model, int weight) throws ArgumentException {
        this.setModel(model);
        this.setWeight(weight);
    }

    private void setModel(String model) throws ArgumentException {
        if (model == null || model.length() < 5) {
            throw new ArgumentException("Model's name must be at least 5 symbols long.");
        }
        this.model = model;
    }

    private void setWeight(int weight) throws ArgumentException {
        if (weight <= 0) {
            throw new ArgumentException("Weight must be a positive integer.");
        }
        this.weight = weight;
    }

    public String boatInformation() {
        String clazzName = this.getClass().getSimpleName();
        return String.format("%s Model: %s", clazzName, this.getModel());
    }

    public String getModel() {
        return model;
    }

    public int getWeight() {
        return weight;
    }
}
