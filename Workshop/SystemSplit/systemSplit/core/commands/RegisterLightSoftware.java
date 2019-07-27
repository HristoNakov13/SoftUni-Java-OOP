package systemSplit.core.commands;

import systemSplit.core.Systemimpl;
import systemSplit.components.software.LightSoftware;
import systemSplit.components.software.Software;

public class RegisterLightSoftware implements Command{
    @Override
    public void execute(String[] args) {
        String hardwareComponent = args[0];
        String name = args[1];
        int capacityConsumption = Integer.parseInt(args[2]);
        int memoryConsumption = Integer.parseInt(args[3]);

        Software lightSoftware = new LightSoftware(name, capacityConsumption, memoryConsumption);
        Systemimpl.registerSoftware(hardwareComponent, lightSoftware);
    }
}
