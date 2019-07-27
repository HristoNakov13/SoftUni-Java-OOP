package systemSplit.core.commands;

import systemSplit.core.Systemimpl;

public class Restore implements Command{
    @Override
    public void execute(String[] args) {
        String component = args[0];
        Systemimpl.restore(component);
    }
}
