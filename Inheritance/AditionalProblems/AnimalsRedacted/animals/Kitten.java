package animals;

public class Kitten extends Cat {
    public Kitten(String name, int age, String gender) {
        super(name, age);
       this.setGender("Female");
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
