package animals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

        String input;

        while (!"Beast!".equals(input = bfr.readLine())) {
            String type = input;
            String[] animalData = bfr.readLine().split(" ");
            String name = null, gender = null;
            int age = 0;

            try {
                name = animalData[0];
                gender = animalData[2];
                age = Integer.parseInt(animalData[1]);
            }catch (Exception e) {
            }

            switch (type) {
                case "Cat":
                    try {
                        Cat cat = new Cat(name, age, gender);
                        System.out.println(cat.produceSound());
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "Dog":
                    try {
                        Dog dog = new Dog(name, age, gender);
                        System.out.println(dog.produceSound());
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "Frog":
                    try {
                        Frog frog = new Frog(name, age, gender);
                        System.out.println(frog.produceSound());
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        }
    }
}
