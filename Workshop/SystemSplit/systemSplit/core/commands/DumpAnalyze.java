package systemSplit.core.commands;

import systemSplit.core.Systemimpl;

public class DumpAnalyze implements Command{
    @Override
    public void execute(String[] args) {
        String analysis = Systemimpl.dumpAnalysis();
        System.out.println(analysis);
    }
}
