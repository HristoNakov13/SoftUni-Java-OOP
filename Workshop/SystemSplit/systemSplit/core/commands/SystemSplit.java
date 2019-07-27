package systemSplit.core.commands;

import systemSplit.core.Systemimpl;

public class SystemSplit implements Command{
    @Override
    public void execute(String[] args) {
        System.out.println(Systemimpl.systemSplit());
    }
}
