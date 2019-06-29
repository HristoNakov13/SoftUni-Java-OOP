package pizzaCalories;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

        String[] pizzaInfo = bfr.readLine().split(" ");
        String name = pizzaInfo[1];
        int toppingCount = Integer.parseInt(pizzaInfo[2]);

        String[] doughInfo = bfr.readLine().split(" ");
        String type = doughInfo[1];
        String technique = doughInfo[2];
        double weight = Double.parseDouble(doughInfo[3]);

        try {
            Pizza pizza = new Pizza(name, toppingCount);
            Dough dough = new Dough(type, technique, weight);
            pizza.setDough(dough);
            String input;

            while (!"END".equals(input = bfr.readLine())) {
                String[] toppingData = input.split(" ");
                type = toppingData[1];
                weight = Double.parseDouble(toppingData[2]);

                Topping topping = new Topping(type, weight);
                pizza.addTopping(topping);
            }
            System.out.println(pizza.pizzaInformation());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
