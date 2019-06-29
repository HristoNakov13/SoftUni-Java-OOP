package shoppingSpree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

        String[] peopleData = bfr.readLine().split(";");
        Map<String, Person> people = new LinkedHashMap<>();

        for (String data : peopleData) {
            String[] person = data.split("=");
            String name = person[0];
            double money = Double.parseDouble(person[1]);

            try {
                Person dude = new Person(name, money);
                people.put(name, dude);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        String[] productData = bfr.readLine().split(";");
        Map<String, Product> groceries = new HashMap<>();

        for (String data : productData) {
            String[] products = data.split("=");
            String name = products[0];
            double cost = Double.parseDouble(products[1]);

            try {
                Product product = new Product(name, cost);
                groceries.put(name, product);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        String input;

        while (!"END".equals(input = bfr.readLine())) {
            String[] line = input.split(" ");
            String name = line[0];
            String product = line[1];

            Person buyer = null;
            if (people.containsKey(name)) {
                buyer = people.get(name);
            }
            Product purchase = null;
            if (groceries.containsKey(product)) {
                purchase = groceries.get(product);
            }

            if (buyer != null && purchase != null) {
                buyer.buyProduct(purchase);
                System.out.println(String.format("%s bought %s", name, product));
            }
        }
        for (Person person : people.values()) {
            System.out.println(person.bagContents());
        }
    }
}
