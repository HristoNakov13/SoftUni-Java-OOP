package animals;

public class Tomcat extends Cat {
    public Tomcat(String name, int age, String gender) {
        super(name, age);
        this.setGender("Male");
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
