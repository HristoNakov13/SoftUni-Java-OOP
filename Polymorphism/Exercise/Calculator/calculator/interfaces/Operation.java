package calculator.interfaces;

public interface Operation {
    void addOperand(int operand);
    int getResult();
    boolean isCompleted();
}
