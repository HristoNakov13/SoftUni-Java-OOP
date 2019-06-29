package pizzaCalories;

public enum BakingTechnique {
    CRISPY(0.9),
    CHEWY(1.1),
    HOMEMADE(1.0);

    double calorieModifier;

    BakingTechnique(double calorieModifier) {
        this.calorieModifier = calorieModifier;
    }

    public double getCalorieModifier() {
        return this.calorieModifier;
    }

    public String getTechniqueType() {
        return this.name();
    }
}
