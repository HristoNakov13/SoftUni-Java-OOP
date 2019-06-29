package pizzaCalories;

public enum DoughType {
    WHITE(1.5),
    WHOLEGRAIN(1.0);

    double calorieModifier;

    DoughType(double calorieModifier) {
        this.calorieModifier = calorieModifier;
    }

    public double getCalorieModifier() {
        return this.calorieModifier;
    }

    public String getDoughType() {
        return this.name();
    }
}
