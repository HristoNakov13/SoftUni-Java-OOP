package animals;

public class Tomcat extends Cat {
    public Tomcat(String name, int age, String gender) {
        super(name, age);
        super.setGender("Male");
    }

    public Tomcat(String name, int age) {
        super(name, age);
        super.setGender("Male");
    }

    @Override
    protected void setGender(String gender) {
        super.setGender(gender);
    }

    @Override
    public String produceSound() {
        return "MEOW";
    }
}
