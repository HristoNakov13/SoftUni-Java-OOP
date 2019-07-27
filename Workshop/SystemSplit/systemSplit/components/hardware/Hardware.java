package systemSplit.components.hardware;

import systemSplit.components.software.ExpressSoftware;
import systemSplit.components.software.LightSoftware;
import systemSplit.components.software.Software;

import java.util.LinkedHashMap;
import java.util.Map;

public class Hardware {
    private String name;
    private int capacity;
    private int memory;
    private int takenCapacity;
    private int takenMemory;
    private Map<String, Software> softwareComponents;

    public Hardware(String name, int capacity, int memory) {
        this.name = name;
        this.capacity = capacity;
        this.memory = memory;
        this.softwareComponents = new LinkedHashMap<>();
        this.takenCapacity = 0;
        this.takenMemory = 0;
    }

    public void registerSoftware(Software software) {
        if (canRegisterSoftware(software)) {
            this.takenMemory += software.getMemoryConsumption();
            this.takenCapacity += software.getCapacityConsumption();
            this.softwareComponents.put(software.getName(), software);
        }
    }

    public void releaseSoftwareComponent(String componentName) {
        if (this.softwareComponents.containsKey(componentName)) {
            Software component = this.softwareComponents.get(componentName);
            this.takenCapacity -= component.getCapacityConsumption();
            this.takenMemory -= component.getMemoryConsumption();
            this.softwareComponents.remove(componentName);
        }
    }

    public int softwareComponentsCount() {
        return this.softwareComponents.size();
    }

    private boolean canRegisterSoftware(Software software) {
        int memoryNeeded = this.getTakenMemory() + software.getMemoryConsumption();
        int capacityNeeded = this.getTakenCapacity() + software.getCapacityConsumption();
        return this.getMemory() >= memoryNeeded && this.getCapacity() >= capacityNeeded;
    }

    public int getExpressSoftwareCount() {
        int count = 0;
        for (Software software : this.softwareComponents.values()) {
            if (software instanceof ExpressSoftware) {
                count++;
            }
        }
        return count;
    }

    public int getLightSoftwareCount() {
        int count = 0;
        for (Software software : this.softwareComponents.values()) {
            if (software instanceof LightSoftware) {
                count++;
            }
        }
        return count;
    }

    private String softwareComponents() {
        StringBuilder components = new StringBuilder();
        if (this.softwareComponents.isEmpty()) {
            components.append("Software Components: None");
        }else {
            components.append("Software Components: ");
            for (Software software : this.softwareComponents.values()) {
                components.append(software.getName()).append(", ");
            }
            components.deleteCharAt(components.length() - 1).deleteCharAt(components.length() - 1);
        }
        return components.toString();
    }

    @Override
    public String toString() {
        StringBuilder componentInfo = new StringBuilder();
        String type = this.getClass().getSimpleName().replace("Hardware", "");

        componentInfo
                .append(String.format("Hardware Component - %s", this.getName()))
                .append(System.lineSeparator())
                .append(String.format("Express Software Components - %d", this.getExpressSoftwareCount()))
                .append(System.lineSeparator())
                .append(String.format("Light Software Components - %d", this.getLightSoftwareCount()))
                .append(System.lineSeparator())
                .append(String.format("Memory Usage: %d / %d", this.getTakenMemory(), this.getMemory()))
                .append(System.lineSeparator())
                .append(String.format("Capacity Usage: %d / %d", this.getTakenCapacity(), this.getCapacity()))
                .append(System.lineSeparator())
                .append(String.format("Type: %s", type))
                .append(System.lineSeparator())
                .append(softwareComponents());

        return componentInfo.toString();
    }

    public int getTakenCapacity() {
        return takenCapacity;
    }

    public int getTakenMemory() {
        return takenMemory;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getMemory() {
        return memory;
    }

    protected void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    protected void setMemory(int memory) {
        this.memory = memory;
    }
}
