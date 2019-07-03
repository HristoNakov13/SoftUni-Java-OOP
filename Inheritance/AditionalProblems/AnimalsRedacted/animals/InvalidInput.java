package animals;

public class InvalidInput extends Exception {

    @Override
    public String getMessage() {
        return "Invalid input!";
    }
}