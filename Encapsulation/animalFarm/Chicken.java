package animalFarm;

public class Chicken {
    private String name;
    private int age;

    public Chicken(String name, int age) {
        this.setName(name);
        this.setAge(age);
    }

    private void setName(String name) {
        if (name == null || name.isEmpty() || name.equals(" ")) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;
    }

    private void setAge(int age) {
        if (age < 0 || age > 15) {
            throw new IllegalArgumentException("Age should be between 0 and 15.");
        }
        this.age = age;
    }

    private int getAge() {
        return this.age;
    }

    private String getName() {
        return this.name;
    }

    public double productPerDay() {
        return this.calculateProductPerDay();
    }

    private double calculateProductPerDay() {
        double dailyProduction = 0;

        if (this.getAge() < 6) {
            dailyProduction = 2;
        }else if (this.getAge() < 11) {
            dailyProduction = 1;
        }else {
            dailyProduction = 0.75;
        }
        return dailyProduction;
    }
    @Override
    public String toString(){
        return String.format("Chicken %s (age %d) can produce %.0f eggs per day."
        , this.getName(), this.getAge(), this.productPerDay());
    }

}
