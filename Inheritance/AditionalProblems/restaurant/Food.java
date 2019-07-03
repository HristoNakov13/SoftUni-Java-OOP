package restaurant;

import java.math.BigDecimal;

public class Food extends Product {
    private double grams;

    public Food(String name, BigDecimal price, double grams) {
        super(name, price);
        this.grams = grams;
    }

    public double getGrams() {
        return grams;
    }

    public void setGrams(double grams) {
        if (grams < 0) {
            throw new IllegalArgumentException();
        }
        this.grams = grams;
    }
}
