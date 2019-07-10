package boatSimulator.repository;

import boatSimulator.models.boats.Boat;

import java.util.LinkedHashMap;
import java.util.Map;

class BoatRepository {
    private Map<String, Boat> boats;

    BoatRepository() {
        this.boats = new LinkedHashMap<>();
    }

    void addBoat(Boat boat) throws DuplicateModelException {
        String boatModel = boat.getModel();
        if (modelIsRegistered(boatModel)) {
            throw new DuplicateModelException(String.format("Boat model: %s already registered", boatModel));
        }
        this.boats.put(boatModel, boat);
    }

    Boat getBoat(String model) throws NonExistantModelException {
        if (!modelIsRegistered(model)) {
            throw new NonExistantModelException("%s model is not registered");
        }
        return this.boats.get(model);
    }

    private boolean modelIsRegistered(String model) {
        return this.boats.containsKey(model);
    }
}
