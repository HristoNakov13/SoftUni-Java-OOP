package mordorCrueltyPlan;

public class Gandalf {
    private int foodPoints;

    public Gandalf() {
    }

    public void eatFood(String food) {
        try {
            this.foodPoints += Foods.valueOf(food.toUpperCase()).getHappiness();
        } catch (Exception e) {
            this.foodPoints--;
        }
    }

    public int getFoodPoints() {
        return foodPoints;
    }

    public String getMood() {

        String mood = "JavaScript";

        if (this.getFoodPoints() < -5) {
            mood = "Angry";
        } else if (this.getFoodPoints() >= -5 && this.getFoodPoints() <= 0) {
            mood = "Sad";
        } else if (this.getFoodPoints() > 0 && this.getFoodPoints() <= 15) {
            mood = "Happy";
        }
        return mood;
    }
}
