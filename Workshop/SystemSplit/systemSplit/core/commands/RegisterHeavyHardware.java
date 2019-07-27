package systemSplit.core.commands;

import systemSplit.core.Systemimpl;
import systemSplit.components.hardware.Hardware;
import systemSplit.components.hardware.HeavyHardware;

public class RegisterHeavyHardware implements Command {
    @Override
    public void execute(String[] args) {
        String name = args[0];
        int capacity = Integer.parseInt(args[1]);
        int memory = Integer.parseInt(args[2]);
        Hardware hardware = new HeavyHardware(name, capacity, memory);
        Systemimpl.registerHardware(hardware);
    }
}
