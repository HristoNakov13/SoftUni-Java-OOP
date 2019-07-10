package boatSimulator.models.engines;

public class EngineValidator {

    static boolean isValidHorsePower(int horsePower) {
        return horsePower > 0;
    }

    static boolean isValidDisplacement(int displacement) {
        return displacement > 0;
    }

    static boolean isValidModel(String model) {
        return model != null && model.length() >= 3;
    }

    static boolean isValidEngineType(String type) {
        try {
            EngineTypes.valueOf(type);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
