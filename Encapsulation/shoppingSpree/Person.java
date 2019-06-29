package shoppingSpree;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private double money;
    private List<Product> products;

    public Person(String name, double money) {
        this.setName(name);
        this.setMoney(money);
        this.products = new ArrayList<>();
    }

    public void setName(String name) {
        name = name.replaceAll("\\s", "");
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    public void setMoney(double money) {
        if (money < 0) {
            throw new IllegalArgumentException("Money cannot be negative");
        }
        this.money = money;
    }

    public boolean canAffordProduct(Product product) {
        return this.money - product.getCost() >= 0;
    }

    public void buyProduct(Product product) {
        if (canAffordProduct(product)) {
            this.products.add(product);
            this.money -= product.getCost();
        } else {
            throw new IllegalArgumentException(String.format("%s can't afford %s", this.name, product.getName()));
        }

    }

    public String bagContents() {
        StringBuilder contents = new StringBuilder();
        contents.append(this.name).append(" - ");
        for (Product product : this.products) {
            contents.append(product.getName()).append(", ");
        }
        if (this.products.isEmpty()) {
            contents.append("Nothing bought");
        } else {
            contents.delete(contents.length() - 2, contents.length());
        }
        return contents.toString();
    }
}
