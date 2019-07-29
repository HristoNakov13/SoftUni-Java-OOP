package hell.core.inputOutput;

import hell.interfaces.OutputWriter;

public class OutputWriterImpl implements OutputWriter {
    public OutputWriterImpl() {
    }

    @Override
    public void writeLine(String output) {
        System.out.println(output);
    }

    @Override
    public void writeLine(String format, Object... params) {
    }
}
