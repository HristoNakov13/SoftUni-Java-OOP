package animals;

public class Dog extends Animal {
    public Dog(String name, int age, String gender) throws InvalidInput {
        super(name, age, gender);
    }

    @Override
    public String produceSound() {
        return "Woof!";
    }
}
