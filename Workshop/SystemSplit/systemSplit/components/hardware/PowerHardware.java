package systemSplit.components.hardware;

public class PowerHardware extends Hardware {
    public PowerHardware(String name, Integer capacity, Integer memory) {
        super(name, capacity, memory);
        this.adjustValues();
    }

    private void adjustValues() {
        super.setCapacity(this.adjustCapacity());
        super.setMemory(this.adjustMemory());
    }

    private int adjustCapacity() {
        int capacity = super.getCapacity();
        return capacity - ((capacity * 3) / 4);
    }

    private int adjustMemory() {
        int memory = super.getMemory();
        return memory + ((memory * 3) / 4);
    }
}
