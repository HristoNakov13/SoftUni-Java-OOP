package wildFarm.animals;

import wildFarm.models.Feline;
import wildFarm.models.Food;

public class Cat extends Feline {
    private String breed;

    public Cat(String animalName, String animalType, Double animalWeight, String livingRegion, String breed) {
        super(animalName, animalType, animalWeight, livingRegion);
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }

    @Override
    public String toString() {
        return String.format("Cat[%s, %s, %s, %s, %d]"
                , super.getAnimalName()
                , this.getBreed()
                , super.getAnimalWeightFormatted()
                , super.getLivingRegion()
                , super.getFoodEaten());
    }

    @Override
    public void makeSound() {
        System.out.println("Meowwww");
    }

    @Override
    public void eat(Food food) {
        super.setFoodEaten(super.getFoodEaten() + food.getQuantity());
    }
}
