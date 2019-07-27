package systemSplit.components.software;

public class LightSoftware extends Software {
    public LightSoftware(String name, int capacityConsumption, int memoryConsumption) {
        super(name, capacityConsumption, memoryConsumption);
        this.adjustValues();
    }

    private void adjustValues() {
        super.setCapacityConsumption(this.adjustCapacityConsumption());
        super.setMemoryConsumption(this.adjustMemoryConsumption());
    }

    private int adjustCapacityConsumption() {
        int capacityConsumption = super.getCapacityConsumption();
        return capacityConsumption + ((capacityConsumption * 2) / 4);
    }

    private int adjustMemoryConsumption() {
        int memoryConsumption = super.getMemoryConsumption();
        return memoryConsumption - ((memoryConsumption * 2) / 4);
    }
}
