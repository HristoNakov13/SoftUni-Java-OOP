package animals;

abstract class Animal implements SoundProducable {
    private String name;
    private int age;
    private String gender;

    protected Animal(String name, int age, String gender) throws InvalidInput {
        this.setName(name);
        this.setAge(age);
        this.setGender(gender);
    }

    protected Animal(String name, int age) throws InvalidInput {
        this.setName(name);
        this.setAge(age);
    }

    public void setName(String name) throws InvalidInput {
        if (name.isEmpty()) {
            throw new InvalidInput("Invalid input!");
        }
        this.name = name;
    }

    public void setAge(int age) throws InvalidInput {
        if (age < 0) {
            throw new InvalidInput("Invalid input!");
        }
        this.age = age;
    }

    public void setGender(String gender) throws InvalidInput {

        if (gender.isEmpty()) {
            throw new InvalidInput("Invalid input!");
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

        return String.format("%s%n%s %d %s", this.getClass().getName()
        , this.getName(), this.getAge(), this.getGender());
    }
}
