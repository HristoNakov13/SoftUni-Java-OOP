package boatSimulator.models.engines;

public enum EngineTypes {
    Jet(5),
    Sterndrive(7);

    private int modifier;

    EngineTypes(int modifier) {
        this.modifier = modifier;
    }

    public int getEngineModifier() {
        return this.modifier;
    }
}
