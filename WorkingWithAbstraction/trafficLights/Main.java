package trafficLights;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

        String[] input = bfr.readLine().split(" ");
        int printCount = Integer.parseInt(bfr.readLine());

        for (int i = 0; i < printCount; i++) {
            for (int j = 0; j < input.length; j++) {
                input[j] = Light.valueOf(input[j]).getLight();
                System.out.print(String.format("%s ", input[j]));
            }
            System.out.println();
        }
    }
}
