package animals;

public class Cat extends Animal {
    public Cat(String name, int age, String gender) throws InvalidInput {
        super(name, age, gender);
    }

    protected Cat(String name, int age) throws InvalidInput {
        super(name, age);
    }

    @Override
    public String produceSound() {
        return "Meow meow";
    }
}
