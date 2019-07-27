package systemSplit.core.commands;

import systemSplit.core.Systemimpl;

public class Dump implements Command {
    @Override
    public void execute(String[] args) {
        String component = args[0];
        Systemimpl.dump(component);
    }
}
