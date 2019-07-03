package animals;

public class Kitten extends Cat {
    public Kitten(String name, int age, String gender) throws InvalidInput {
        this(name, age);
    }

    public Kitten(String name, int age) throws InvalidInput {
        super(name, age);
        super.setGender("Female");
    }

//    @Override
//    protected void setGender(String gender) throws InvalidInput {
//        super.setGender(gender);
//    }

    @Override
    public String produceSound() {
        return "Meow";
    }
}
