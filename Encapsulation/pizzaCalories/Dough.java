package pizzaCalories;

public class Dough {
    private String flourType;
    private String bakingTechnique;
    private double weight;

    public Dough(String doughType, String bakingTechnique, double weight) {
        this.setFlourType(doughType);
        this.setBakingTechnique(bakingTechnique);
        this.setWeight(weight);
    }

    private void setFlourType(String doughType) {
        try {
            DoughType.valueOf(doughType.toUpperCase());
            this.flourType = doughType;
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid type of dough.");
        }

    }

    private void setBakingTechnique(String bakingTechnique) {
        try {
            BakingTechnique.valueOf(bakingTechnique.toUpperCase());
            this.bakingTechnique = bakingTechnique;
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid type of dough.");
        }
    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 200) {
            throw new IllegalArgumentException("Dough weight should be in the range [1..200].");
        }
        this.weight = weight;
    }

    private String getFlourType() {
        return flourType.toUpperCase();
    }

    private String getBakingTechnique() {
        return bakingTechnique.toUpperCase();
    }

    private double getWeight() {
        return weight;
    }

    public double calculateCalories() {
        double doughModifier = DoughType.valueOf(this.getFlourType()).getCalorieModifier();
        double bakingModifier = BakingTechnique.valueOf(this.getBakingTechnique()).getCalorieModifier();
        return bakingModifier * doughModifier * this.getWeight() * 2;
    }
}
