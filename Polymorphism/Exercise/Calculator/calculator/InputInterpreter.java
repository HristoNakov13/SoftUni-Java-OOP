package calculator;

import calculator.interfaces.Operation;
import calculator.operations.AddOperation;
import calculator.operations.DivisionOperation;
import calculator.operations.ModularDivisionOperation;
import calculator.operations.MultiplicationOperation;
import calculator.operationsRepo.Memory;

public class InputInterpreter {
    private CalculationEngine engine;
    private Memory memory;

    public InputInterpreter(CalculationEngine engine) {
        this.engine = engine;
        this.memory = new Memory();
    }

    public boolean interpret(String input) {
        try {
            engine.pushNumber(Integer.parseInt(input));
        } catch (Exception ex) {
            engine.pushOperation(this.getOperation(input));
        }
        return true;
    }

    public Operation getOperation(String operation) {
        Operation returnOperation = null;
        switch (operation) {
            case "*":
                this.memory.setLastOperation(operation);
                returnOperation = new MultiplicationOperation();
                break;
            case "/":
                this.memory.setLastOperation(operation);
                returnOperation = new DivisionOperation();
                break;
            case "%":
                this.memory.setLastOperation("%");
                returnOperation = new ModularDivisionOperation();
                break;
            case "+":
                this.memory.setLastOperation(operation);
                returnOperation = new AddOperation();
                break;
            case "ms":
                this.memory.addToMemory(engine.getCurrentResult());
                break;
            case "mr":
                engine.pushNumber(this.memory.memoryRecall());
                operation = this.memory.getLastOperation();
                this.getOperation(operation);
                break;
        }
        return returnOperation;
    }
}
