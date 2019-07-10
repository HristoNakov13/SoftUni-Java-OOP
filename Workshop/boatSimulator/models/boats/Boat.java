package boatSimulator.models.boats;

public interface Boat {
    String getModel();
    int getWeight();
    double calculateSpeed(double oceanCurrent);
    String boatInformation();
}
