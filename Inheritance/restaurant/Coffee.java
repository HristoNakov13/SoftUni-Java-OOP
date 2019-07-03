package restaurant;

import java.math.BigDecimal;

public class Coffee extends HotBeverage {
    private double COFFEE_MILLILITERS = 50;
    private BigDecimal COFFEE_PRICE = BigDecimal.valueOf(3.5);
    private double caffeine;

    public Coffee(String name, BigDecimal price, double milliliters, double caffeine) {
        super(name, price, milliliters);
        this.caffeine = caffeine;
        super.setPrice(COFFEE_PRICE);
        super.setMilliliters(COFFEE_MILLILITERS);
    }

    public double getCaffeine() {
        return caffeine;
    }
}
