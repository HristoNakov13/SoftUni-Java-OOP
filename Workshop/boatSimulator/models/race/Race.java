package boatSimulator.models.race;

import boatSimulator.models.boats.Boat;

public interface Race {
    boolean SignUpBoat(Boat boat) throws UnallowedBoatType;
    String StartRace();
}
