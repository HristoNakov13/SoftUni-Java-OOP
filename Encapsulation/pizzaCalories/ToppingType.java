package pizzaCalories;

public enum ToppingType {
    MEAT(1.2),
    VEGGIES(0.8),
    CHEESE(1.1),
    SAUCE(0.9);

    double calorieModifier;

    ToppingType(double calorieModifier) {
        this.calorieModifier = calorieModifier;
    }

    public double getCalorieModifier() {
        return this.calorieModifier;
    }

    public String getToppingType() {
        return this.name();
    }
}
