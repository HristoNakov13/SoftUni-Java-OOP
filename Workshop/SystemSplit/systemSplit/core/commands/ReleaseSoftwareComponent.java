package systemSplit.core.commands;

import systemSplit.core.Systemimpl;

public class ReleaseSoftwareComponent implements Command{
    @Override
    public void execute(String[] args) {
        String hardName = args[0];
        String softName = args[1];
        Systemimpl.releaseSoftware(hardName, softName);
    }
}
