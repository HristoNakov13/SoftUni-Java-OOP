package wildFarm;

import wildFarm.animals.Cat;
import wildFarm.animals.Mouse;
import wildFarm.animals.Tiger;
import wildFarm.animals.Zebra;
import wildFarm.food.Meat;
import wildFarm.food.Vegetable;
import wildFarm.Interfaces.AnimalI;
import wildFarm.models.Food;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        String input;
        List<AnimalI> animals = new ArrayList<>();

        while (!"End".equals(input = bfr.readLine())) {
            String[] animalData = input.split(" ");

            String type = animalData[0];
            String name = animalData[1];
            Double weight = Double.parseDouble(animalData[2]);
            String region = animalData[3];
            AnimalI animal = null;

            switch (type) {
                case "Cat":
                    String breed = animalData[4];
                    animal = new Cat(name, type, weight, region, breed);
                    break;
                case "Zebra":
                    animal = new Zebra(name, type, weight, region);
                    break;
                case "Tiger":
                    animal = new Tiger(name, type, weight, region);
                    break;
                case "Mouse":
                    animal = new Mouse(name, type, weight, region);
                    break;
            }
            String[] foodData = bfr.readLine().split(" ");
            String foodType = foodData[0];
            Integer quantity = Integer.parseInt(foodData[1]);
            Food food = null;
            switch (foodType) {
                case "Vegetable":
                    food = new Vegetable(quantity);
                    break;
                case "Meat":
                    food = new Meat(quantity);
                    break;
            }
            if (animal == null || food == null) {
                continue;
            }
            animal.makeSound();
            try {
                animal.eat(food);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            animals.add(animal);
        }
        StringBuilder allAnimalsAsString = new StringBuilder();
        for (AnimalI animalI : animals) {
            allAnimalsAsString
                    .append(animalI.toString())
                    .append(System.lineSeparator());
        }
        System.out.println(allAnimalsAsString.toString().trim());
    }
}
