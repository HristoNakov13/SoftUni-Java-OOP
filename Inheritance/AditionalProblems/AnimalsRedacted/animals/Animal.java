package animals;

public abstract class Animal implements SoundProducable {
    private String name;
    private int age;
    private String gender;

    public Animal(String name, int age, String gender) {
        this.setName(name);
        this.setAge(age);
        this.setGender(gender);
    }

    protected Animal(String name, int age) {
        this.setName(name);
        this.setAge(age);
    }

    public void setName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Invalid input!");
        }
        this.name = name;
    }

    protected void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Invalid input!");

        }
        this.age = age;
    }

    protected void setGender(String gender) {

        if (gender.isEmpty()) {
            throw new IllegalArgumentException("Invalid input!");

        }
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return String.format("%s%n%s %d %s"
                , this.getClass().getSimpleName(), this.name, this.age, this.gender);
    }
}
