package mordorCrueltyPlan;

public enum Foods {
    CRAM(2),
    LEMBAS(3),
    APPLE(1),
    MELON(1),
    HONEYCAKE(5),
    MUSHROOMS(-10);

    int value;

    Foods(int value) {
        this.value = value;
    }

    public int getHappiness() {
        return this.value;
    }
}
