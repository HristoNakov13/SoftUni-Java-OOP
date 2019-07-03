package animals;

public class Frog extends Animal {
    public Frog(String name, int age, String gender) throws InvalidInput {
        super(name, age, gender);
    }

    @Override
    public String produceSound() {
        return "Ribbit";
    }
}
