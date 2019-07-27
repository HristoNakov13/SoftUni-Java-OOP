package systemSplit.components.software;

public class ExpressSoftware extends Software{
    public ExpressSoftware(String name, int capacityConsumption, int memoryConsumption) {
        super(name, capacityConsumption, memoryConsumption);
        this.adjustValues();
    }

    private void adjustValues() {
        super.setMemoryConsumption(this.adjustMemoryConsumption());
    }

    private int adjustMemoryConsumption() {
        return super.getMemoryConsumption() * 2;
    }
}
