package animals;

public class Dog extends Animal {
    protected Dog(String name, String favoriteFood) {
        super(name, favoriteFood);
    }

    @Override
    String explainSelf() {
        return String.format("I am %s and my fovourite food is %s%nDJAAF"
        , super.getName(), super.getFavoriteFood());
    }
}
