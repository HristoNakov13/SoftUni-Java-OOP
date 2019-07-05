package wildFarm.models;


import wildFarm.Interfaces.AnimalI;

import java.text.DecimalFormat;

public abstract class Animal implements AnimalI {
    private static final String DECIMAL_FORMAT_STR = "0.##";
    private String animalName;
    private String animalType;
    private Double animalWeight;
    private Integer foodEaten;

    public Animal(String animalName, String animalType, Double animalWeight) {
        this.animalName = animalName;
        this.animalType = animalType;
        this.animalWeight = animalWeight;
        this.foodEaten = 0;
    }

    public String getAnimalName() {
        return animalName;
    }

    public String getAnimalType() {
        return animalType;
    }

    public String getAnimalWeightFormatted() {
        DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_FORMAT_STR);
        String weight = decimalFormat.format(this.getAnimalWeight());
        return weight;
    }

    public Double getAnimalWeight() {
        return animalWeight;
    }

    public Integer getFoodEaten() {
        return foodEaten;
    }


    public void setFoodEaten(Integer foodEaten) {
        this.foodEaten = foodEaten;
    }
}
