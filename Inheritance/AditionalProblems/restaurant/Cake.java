package restaurant;

import java.math.BigDecimal;

public class Cake extends Dessert {
    static final double CAKE_GRAMS = 250;
    static final double CAKE_CALORIES = 1000;
    static final BigDecimal CAKE_PRICE = BigDecimal.valueOf(5);

    public Cake(String name, BigDecimal price, double grams, double calories) {
        super(name, price, grams, calories);
        super.setGrams(CAKE_GRAMS);
        super.setPrice(CAKE_PRICE);
        super.setCalories(CAKE_CALORIES);
    }
}
