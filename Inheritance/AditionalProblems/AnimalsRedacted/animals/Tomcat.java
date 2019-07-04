package animals;

public class Tomcat extends Cat {
    private static final String DEFAULT_TOMCAT_GENDER = "Male";

    public Tomcat(String name, int age) {
        super(name, age, DEFAULT_TOMCAT_GENDER);
    }

    public Tomcat(String name, int age, String gendder) {
        super(name, age, gendder);
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
