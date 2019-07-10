package boatSimulator.models.race;

import boatSimulator.models.ArgumentException;
import boatSimulator.models.boats.Boat;

public interface Race {
    boolean SignUpBoat(Boat boat) throws UnallowedBoatType;
    String StartRace();
    void setDistance(int distance) throws ArgumentException;
    void setWindSpeed(double windSpeed);
    void setOceanCurrent(double oceanCurrent);
    void setMotorBoatsAllowed(boolean motorBoatsAllowed);
    boolean hasEnoughParticipants();
    boolean motorBoatsAllowed();
}
