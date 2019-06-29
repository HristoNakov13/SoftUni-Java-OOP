package pizzaCalories;

public class Topping {
    private String toppingType;
    private double weight;

    public Topping(String toppingType, double weight) {
        this.setToppingType(toppingType);
        this.setWeight(weight);
    }

    private void setToppingType(String toppingType) {
        try {
            ToppingType.valueOf(toppingType.toUpperCase());
            this.toppingType = toppingType;
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("Cannot place %s on top of your pizza.", toppingType));
        }
    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 50) {
            throw new IllegalArgumentException(String.format("%s weight should be in the range [1..50].", this.toppingType));
        }
        this.weight = weight;
    }

    private String getToppingType() {
        return toppingType.toUpperCase();
    }

    private double getWeight() {
        return weight;
    }

    public double calculateCalories() {
        double calorieModifier = ToppingType.valueOf(this.getToppingType()).getCalorieModifier();
        return this.getWeight() * calorieModifier * 2;

    }
}
