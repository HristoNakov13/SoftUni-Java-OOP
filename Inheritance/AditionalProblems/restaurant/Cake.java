package restaurant;

import java.math.BigDecimal;

public class Cake extends Dessert {
    static final double CAKE_GRAMS = 250.0;
    static final double CAKE_CALORIES = 1000.0;
    static final BigDecimal CAKE_PRICE = BigDecimal.valueOf(5);
//    •	double CAKE_GRAMS = 250
//•	double CAKE_CALORIES = 1000
//•	BigDecimal CAKE_PRICE = 5

    public Cake(String name, BigDecimal price, double grams, double calories) {
        super(name, price, grams, calories);
//        super.setGrams(CAKE_GRAMS);
//        super.setPrice(CAKE_PRICE);
//        super.setCalories(CAKE_CALORIES);
    }

//    @Override
//    public double getGrams() {
//        return CAKE_GRAMS;
//    }
//
//    @Override
//    public BigDecimal getPrice() {
//        return CAKE_PRICE;
//    }
//
//    @Override
//    public double getCalories() {
//        return CAKE_CALORIES;
//    }
}
