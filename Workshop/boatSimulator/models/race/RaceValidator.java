package boatSimulator.models.race;

import boatSimulator.models.boats.Boat;
import boatSimulator.models.engines.Engine;

import java.lang.reflect.Field;

class RaceValidator {

    static boolean isSailBoat(Boat boat) {
        return boat.getClass().getSimpleName().equals("SailBoat");
    }

    static boolean isMotorBoat(Boat boat) {
        Class clazz = boat.getClass();
        Field[] fields = clazz.getDeclaredFields();
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
