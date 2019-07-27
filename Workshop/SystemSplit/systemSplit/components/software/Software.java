package systemSplit.components.software;

public class Software {
    private String name;
    private int capacityConsumption;
    private int memoryConsumption;

    public Software(String name, int capacityConsumption, int memoryConsumption) {
        this.name = name;
        this.capacityConsumption = capacityConsumption;
        this.memoryConsumption = memoryConsumption;
    }

    public String getName() {
        return name;
    }

    public int getCapacityConsumption() {
        return capacityConsumption;
    }

    public int getMemoryConsumption() {
        return memoryConsumption;
    }

    protected void setCapacityConsumption(int capacityConsumption) {
        this.capacityConsumption = capacityConsumption;
    }

    protected void setMemoryConsumption(int memoryConsumption) {
        this.memoryConsumption = memoryConsumption;
    }
}