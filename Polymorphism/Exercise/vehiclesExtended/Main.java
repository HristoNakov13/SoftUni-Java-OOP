package vehiclesExtended;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

        String[] carData = bfr.readLine().split(" ");
        Car car = new Car(Double.parseDouble(carData[1]), Double.parseDouble(carData[2]), Double.parseDouble(carData[3]));

        String[] truckData = bfr.readLine().split(" ");
        Truck truck = new Truck(Double.parseDouble(truckData[1]), Double.parseDouble(truckData[2]), Double.parseDouble(truckData[3]));

        String[] busData = bfr.readLine().split(" ");
        Bus bus = new Bus(Double.parseDouble(busData[1]), Double.parseDouble(busData[2]), Double.parseDouble(busData[3]));

        int inputCount = Integer.parseInt(bfr.readLine());

        for (int i = 0; i < inputCount; i++) {
            String[] line = bfr.readLine().split(" ");

            String command = line[0];
            String vehicleType = line[1];
            double value = Double.parseDouble(line[2]);
            Vehicle vehicle = null;

            switch (vehicleType) {
                case "Bus":
                    vehicle = bus;
                    break;
                case "Car":
                    vehicle = car;
                    break;
                case "Truck":
                    vehicle = truck;
                    break;
            }
            if (vehicle == null) {
                continue;
            }
            switch (command) {
                case "Drive":
                    System.out.println(vehicle.drive(value));
                    break;
                case "Refuel":
                    try {
                        vehicle.refuel(value);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "DriveEmpty":
                    try {
                        bus = (Bus) vehicle;
                        System.out.println(bus.driveEmpty(value));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        }
        System.out.println(car.vehicleInfo());
        System.out.println(truck.vehicleInfo());
        System.out.println(bus.vehicleInfo());
    }
}
