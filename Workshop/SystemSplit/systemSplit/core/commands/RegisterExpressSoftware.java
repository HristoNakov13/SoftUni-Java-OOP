package systemSplit.core.commands;

import systemSplit.core.Systemimpl;
import systemSplit.components.software.ExpressSoftware;
import systemSplit.components.software.Software;

public class RegisterExpressSoftware implements Command{
    @Override
    public void execute(String[] args) {
        String hardwareComponent = args[0];
        String name = args[1];
        int capacityConsumption = Integer.parseInt(args[2]);
        int memoryConsumption = Integer.parseInt(args[3]);

        Software expressSoftware = new ExpressSoftware(name, capacityConsumption, memoryConsumption);
        Systemimpl.registerSoftware(hardwareComponent, expressSoftware);
    }
}
