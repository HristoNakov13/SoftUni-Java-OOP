package boatSimulator.repository;


import boatSimulator.models.engines.Engine;

import java.util.LinkedHashMap;
import java.util.Map;

class EngineRepository {
    private Map<String, Engine> engines;

    EngineRepository() {
        this.engines = new LinkedHashMap<>();
    }

    void addEngine(Engine engine) throws DuplicateModelException {
        String engineModel = engine.getModel();
        if (modelIsRegistered(engineModel)) {
            throw new DuplicateModelException(
                    String.format("Engine model: %s already registered",
                            engineModel));
        }
        this.engines.put(engineModel, engine);
    }

    Engine getEngine(String model) throws NonExistantModelException {
        if (!modelIsRegistered(model)) {
            throw new NonExistantModelException(String.format("%s model is not registered", model));
        }
        return this.engines.get(model);
    }

    private boolean modelIsRegistered(String model) {
        return this.engines.containsKey(model);
    }
}
