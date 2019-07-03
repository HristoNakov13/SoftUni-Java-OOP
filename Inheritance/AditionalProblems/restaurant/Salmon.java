package restaurant;

import java.math.BigDecimal;

public class Salmon extends MainDish {
    static final double SALMON_GRAMS = 22;

    public Salmon(String name, BigDecimal price, double grams) {
        super(name, price, grams);
        this.setGrams(SALMON_GRAMS);
    }

    private void setSalmonGrams(double grams) {
        if (grams < 0) {
            throw new IllegalArgumentException();
        }
        super.setGrams(grams);
    }
}
