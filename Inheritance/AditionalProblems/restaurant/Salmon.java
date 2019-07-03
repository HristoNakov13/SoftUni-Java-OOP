package restaurant;

import java.math.BigDecimal;

public class Salmon extends MainDish {
    static final double SALMON_GRAMS = 22.0;

    public Salmon(String name, BigDecimal price) {
        super(name, price, SALMON_GRAMS);
//        super.setGrams(SALMON_GRAMS);
    }

//    @Override
//    public double getGrams() {
//        return SALMON_GRAMS;
//    }
}
