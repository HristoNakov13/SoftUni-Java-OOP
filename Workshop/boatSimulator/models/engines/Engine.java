package boatSimulator.models.engines;

import boatSimulator.models.ArgumentException;

public class Engine {
    private String model;
    private String type;
    private int horsepower;
    private int displacement;
    private double output;

    public Engine(String model, int horsepower, int displacement, String type) throws ArgumentException {
        this.setModel(model);
        this.setType(type);
        this.setHorsepower(horsepower);
        this.setDisplacement(displacement);
        this.calculateEngineOutput();
    }

    public void setModel(String model) throws ArgumentException {
        if (!EngineValidator.isValidModel(model)) {
            throw new ArgumentException("Model's name must be at least 3 symbols long.");
        }
        this.model = model;
    }

    private void setType(String type) throws ArgumentException {
        if (!EngineValidator.isValidEngineType(type)) {
            throw new ArgumentException("Unavailable model type");
        }
        this.type = type;
    }

    private void setHorsepower(int horsepower) throws ArgumentException {
        if (!EngineValidator.isValidHorsePower(horsepower)) {
            throw new ArgumentException("Horsepower must be a positive integer.");
        }
        this.horsepower = horsepower;
    }

    private void setDisplacement(int displacement) throws ArgumentException {
        if (!EngineValidator.isValidDisplacement(displacement)) {
            throw new ArgumentException("Horsepower must be a positive integer.");
        }
        this.displacement = displacement;
    }

    public String getModel() {
        return model;
    }

    private String getType() {
        return type;
    }

    private int getHorsepower() {
        return horsepower;
    }

    private int getDisplacement() {
        return displacement;
    }

    public double getOutput() {
        return output;
    }

    private void calculateEngineOutput() {
        double modifier = this.getHorsepower() * EngineTypes.valueOf(this.getType()).getEngineModifier();
        this.output = modifier + this.getDisplacement();
    }
}
