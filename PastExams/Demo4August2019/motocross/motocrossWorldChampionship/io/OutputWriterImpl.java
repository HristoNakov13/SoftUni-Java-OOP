package motocrossWorldChampionship.io;

import motocrossWorldChampionship.io.interfaces.OutputWriter;

public class OutputWriterImpl implements OutputWriter {
    @Override
    public void writeLine(String text) {
        System.out.println(text);
    }
}
