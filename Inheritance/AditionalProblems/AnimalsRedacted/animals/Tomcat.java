package animals;

public class Tomcat extends Cat {
    public Tomcat(String name, int age, String gender) throws InvalidInput {
        this(name, age);
    }

    public Tomcat(String name, int age) throws InvalidInput {
        super(name, age);
        super.setGender("Male");
    }

//    @Override
//    protected void setGender(String gender) throws InvalidInput {
//        super.setGender(gender);
//    }

    @Override
    public String produceSound() {
        return "MEOW";
    }
}
