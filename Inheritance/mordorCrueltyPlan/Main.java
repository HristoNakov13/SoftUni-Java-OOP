package mordorCrueltyPlan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

        String[] input = bfr.readLine().split(" ");
        Gandalf gandalf = new Gandalf();

        for (String foodItem : input) {
            gandalf.eatFood(foodItem.toUpperCase());
        }
        System.out.println(gandalf.getFoodPoints());
        System.out.println(gandalf.getMood());
    }
}
