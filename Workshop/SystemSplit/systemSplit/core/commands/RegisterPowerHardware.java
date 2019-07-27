package systemSplit.core.commands;

import systemSplit.core.Systemimpl;
import systemSplit.components.hardware.Hardware;
import systemSplit.components.hardware.PowerHardware;

public class RegisterPowerHardware implements Command {
    @Override
    public void execute(String[] args) {
        String name = args[0];
        int capacity = Integer.parseInt(args[1]);
        int memory = Integer.parseInt(args[2]);
        Hardware hardware = new PowerHardware(name, capacity, memory);
        Systemimpl.registerHardware(hardware);
    }
}
