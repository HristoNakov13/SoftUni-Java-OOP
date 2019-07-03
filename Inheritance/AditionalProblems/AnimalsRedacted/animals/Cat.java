package animals;

public class Cat extends Animal {
    public Cat(String name, int age, String gender) {
        super(name, age, gender);
    }

    protected Cat(String name, int age) {
        super(name, age);
    }

    @Override
    public String produceSound() {
        return "Meow meow";
    }
}
