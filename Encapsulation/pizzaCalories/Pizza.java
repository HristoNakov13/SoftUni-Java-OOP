package pizzaCalories;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private String name;
    private Dough dough;
    private List<Topping> toppings;
    private int toppingsCount;

    public Pizza(String name, int toppingsCount) {
        this.setName(name);
        this.setToppings(toppingsCount);
    }

    private void setName(String name) {
        name = name.replaceAll("\\s", "");
        if (name.isEmpty() || name.length() > 15) {
            throw new IllegalArgumentException("Pizza name should be between 1 and 15 symbols.");
        }
        this.name = name;
    }

    private void setToppings(int toppingsCount) {
        if (toppingsCount < 0 || toppingsCount > 10) {
            throw new IllegalArgumentException("Number of toppings should be in range [0..10].");
        }
        this.toppings = new ArrayList<>();
        this.toppingsCount = toppingsCount;
    }

    public void setDough(Dough dough) {
        this.dough = dough;
    }

    public void addTopping(Topping topping) {
        this.toppings.add(topping);

    }

    public double getOverallCalories() {
        double doughCalories = this.dough.calculateCalories();
        double toppingCalories = 0;

        for (Topping topping : this.toppings) {
            toppingCalories += topping.calculateCalories();
        }
        return doughCalories + toppingCalories;
    }

    public String pizzaInformation() {
        return String.format("%s - %.2f", this.name, this.getOverallCalories());
    }
}
