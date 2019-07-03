package animals;

public class Cat extends Animal{
    protected Cat(String name, String favoriteFood) {
        super(name, favoriteFood);
    }

    @Override
    String explainSelf() {
        return String.format("I am %s and my fovourite food is %s%nMEEOW",
                super.getName(), super.getFavoriteFood());
    }
}
