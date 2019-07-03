package restaurant;

import java.math.BigDecimal;

public class Product {
    private String name;
    private BigDecimal price;

    public Product(String name, BigDecimal price) {
        this.setName(name);
        this.setPrice(price);
    }

    public void setName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.name = name;
    }

    public void setPrice(BigDecimal price) {

        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException();
        }
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }


}
