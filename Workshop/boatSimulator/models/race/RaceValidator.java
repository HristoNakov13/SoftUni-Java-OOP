package boatSimulator.models.race;

import boatSimulator.models.boats.Boat;
import boatSimulator.models.engines.Engine;

import java.lang.reflect.Field;

class RaceValidator {

    static boolean isSailBoat(Boat boat) {
        boolean isSailBoat = false;
        Field[] fields = boat.getClass().getFields();
        for (Field field : fields) {
            if (field.getName().equals("windSpeed")) {
                isSailBoat = true;
                break;
            }
        }
        return isSailBoat;
    }

    static boolean isMotorBoat(Boat boat) {
        Field[] fields = boat.getClass().getFields();
        String engineClassName = Engine.class.getName();
        boolean isMotorBoat = false;

        for (Field field : fields) {
            if (field.getType().getName().equals(engineClassName)) {
                isMotorBoat = true;
                break;
            }
        }
        return isMotorBoat;
    }

    static boolean isValidDistance (int distance) {
        return distance > 0;
    }
}
