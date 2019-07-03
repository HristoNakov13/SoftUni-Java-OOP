package telephony;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

        List<String> numbers = Arrays.asList(bfr.readLine().split(" "));
        List<String> URLs = Arrays.asList(bfr.readLine().split(" "));

        Smartphone smartphone = new Smartphone(numbers, URLs);
        System.out.println(smartphone.call());
        System.out.println(smartphone.browse());

    }
}
