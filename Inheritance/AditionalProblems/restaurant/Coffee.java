package restaurant;

import java.math.BigDecimal;

public class Coffee extends HotBeverage {
    static final double COFFEE_MILLILITERS = 50.0;
    static final BigDecimal COFFEE_PRICE = BigDecimal.valueOf(3.50);
    double caffeine;

    public Coffee(String name, BigDecimal price, double milliliters) {
        super(name, price, milliliters);
//        super.setPrice(COFFEE_PRICE);
//        super.setMilliliters(COFFEE_MILLILITERS);
    }

    public double getCaffeine() {
        return caffeine;
    }

//    @Override
//    public double getMilliliters() {
//        return COFFEE_MILLILITERS;
//    }
//
//    @Override
//    public BigDecimal getPrice() {
//        return COFFEE_PRICE;
//    }
}
