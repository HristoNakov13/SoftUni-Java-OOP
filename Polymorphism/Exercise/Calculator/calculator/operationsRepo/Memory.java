package calculator.operationsRepo;

import java.util.ArrayDeque;
import java.util.Deque;

public class Memory {
    private Deque<Integer> memory;
    private String lastOperation;

    public Memory() {
        this.memory = new ArrayDeque<>();
    }

    public void setLastOperation(String operation) {
        this.lastOperation = operation;
    }

    public String getLastOperation() {
        return lastOperation;
    }

    public void addToMemory(int element) {
        this.memory.push(element);
    }

    public int memoryRecall() {
        int value = -1;
        if (!this.memory.isEmpty()) {
            value = this.memory.pop();
        }
        return value;
    }
}
