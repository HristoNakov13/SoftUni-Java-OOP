package trafficLights;

public enum Light {
    RED("GREEN"),
    GREEN("YELLOW"),
    YELLOW("RED");

    String value;
    Light(String value) {
        this.value = value;
    }

    public String getLight() {
        return value;
    }
}
