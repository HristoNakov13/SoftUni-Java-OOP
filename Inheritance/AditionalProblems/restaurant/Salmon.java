package restaurant;

import java.math.BigDecimal;

public class Salmon extends MainDish {
    static final double SALMON_GRAMS = 22.0;

    public Salmon(String name, BigDecimal price, double grams) {
        super(name, price, grams);
//        super.setGrams(SALMON_GRAMS);
    }

//    @Override
//    public double getGrams() {
//        return SALMON_GRAMS;
//    }
}
