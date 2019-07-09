package barracksReturnOfTheDependancies.core.commands;

import barracksReturnOfTheDependancies.contracts.Executable;

public abstract class Command implements Executable {
    private String[] data;


    public Command(String[] data) {
        this.data = data;

    }

    public String[] getData() {
        return data;
    }
}
