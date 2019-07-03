package vehicles;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

        String[] carData = bfr.readLine().split(" ");
        Car car = new Car(Double.parseDouble(carData[1]), Double.parseDouble(carData[2]));

        String[] truckData = bfr.readLine().split(" ");
        Truck truck = new Truck(Double.parseDouble(truckData[1]), Double.parseDouble(truckData[2]));

        int inputCount = Integer.parseInt(bfr.readLine());
        for (int i = 0; i < inputCount; i++) {
            String[] line = bfr.readLine().split(" ");
            String command = line[0];
            String vehicle = line[1];
            double value = Double.parseDouble(line[2]);

            if (command.equals("Drive")) {
                if (vehicle.equals("Car")) {
                    System.out.println(car.drive(value));
                }else {
                    System.out.println(truck.drive(value));
                }
            }else {
                if (vehicle.equals("Car")) {
                    car.refuel(value);
                }else {
                    truck.refuel(value);
                }
            }
        }
        System.out.println(car.vehicleInfo());
        System.out.println(truck.vehicleInfo());
    }
}
