package classBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

        double length = Double.parseDouble(bfr.readLine());
        double width = Double.parseDouble(bfr.readLine());
        double height = Double.parseDouble(bfr.readLine());


        Box box = new Box(length, width, height);
        StringBuilder print = new StringBuilder();

        print.append(String.format("Surface Area - %.2f", box.calculateSurfaceArea()))
                .append(System.lineSeparator())
                .append(String.format("Lateral Surface Area - %.2f", box.calculateLateralSurfaceArea()))
                .append(System.lineSeparator())
                .append(String.format("Volume – %.2f", box.calculateVolume()));

        System.out.println(print.toString());

    }
}
