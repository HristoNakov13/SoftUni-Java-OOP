package animals;

public class Kitten extends Cat {
    private static final String DEFAULT_KITTEN_GENDER = "Female";

    public Kitten(String name, int age) {
        super(name, age, DEFAULT_KITTEN_GENDER);

    }
    public Kitten(String name, int age, String gender) {
        super(name, age, gender);

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
