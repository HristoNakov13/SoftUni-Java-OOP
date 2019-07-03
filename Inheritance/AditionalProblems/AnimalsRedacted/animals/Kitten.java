package animals;

public class Kitten extends Cat {
    public Kitten(String name, int age, String gender) {
        super(name, age);
       super.setGender("Female");
    }

    public Kitten(String name, int age) {
        super(name, age);
        super.setGender("Female");
    }

    @Override
    protected void setGender(String gender) {
        super.setGender(gender);
    }

    @Override
    public String produceSound() {
        return "Meow";
    }
}
