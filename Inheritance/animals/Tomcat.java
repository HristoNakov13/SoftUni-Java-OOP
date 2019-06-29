package animals;

public class Tomcat extends Cat implements SoundProducible{
    public Tomcat(String name, int age, String gender) {
        super(name, age, gender);
    }

    @Override
    protected void setGender(String gender) {
        super.setGender(gender);
    }

    @Override
    public String produceSound() {
        return "Give me one million b***h";
    }
}
