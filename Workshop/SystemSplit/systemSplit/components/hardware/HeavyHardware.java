package systemSplit.components.hardware;

public class HeavyHardware extends Hardware {
    public HeavyHardware(String name, Integer capacity, Integer memory) {
        super(name, capacity, memory);
        this.adjustValues();
    }

    private void adjustValues() {
        super.setCapacity(this.adjustCapacity());
        super.setMemory(this.adjustMemory());
    }

    private int adjustCapacity() {
        int capacity = super.getCapacity();
        return capacity * 2;
    }

    private int adjustMemory() {
        int memory = super.getMemory();
        return memory - (memory / 4);
    }
}
