package cresla.core.commands;

import cresla.interfaces.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModuleCommand extends FactoryCommandImpl {
    private static final String ENERGY_MODULE = "Energy";
    private static final String ABSORBING_MODULE = "Absorbing";
    private static final String COOLDOWN_SYSTEM = "CooldownSystem";
    private static final String HEATPROCESSOR = "HeatProcessor";
    private static final String CRYOROD = "CryogenRod";
    private Map<String, String> moduleTypes;

    public ModuleCommand(Database database, Factory factory) {
        super(database, factory);
        this.init();
    }

    private void init() {
        this.moduleTypes = new HashMap<>();
        this.moduleTypes.put(COOLDOWN_SYSTEM, ABSORBING_MODULE);
        this.moduleTypes.put(HEATPROCESSOR, ABSORBING_MODULE);
        this.moduleTypes.put(CRYOROD, ENERGY_MODULE);
    }

    @Override
    public String execute(List<String> args) {
        int reactorId = Integer.parseInt(args.get(0));
        String type = args.get(1);
        int outputOrAbsorbtion = Integer.parseInt(args.get(2));

        int moduleId = -1;
        switch (this.getModuleType(type)) {
            case ABSORBING_MODULE:
                AbsorbingModule absorbingModule = null;
                switch (type) {
                    case COOLDOWN_SYSTEM:
                        absorbingModule = super.getFactory().createCoolDownSystem(outputOrAbsorbtion);
                        break;
                    case HEATPROCESSOR:
                        absorbingModule = super.getFactory().createHeatProcessor(outputOrAbsorbtion);
                        break;
                }
                this.addAbsorbingModuleToReactor(reactorId, absorbingModule);
                moduleId = absorbingModule.getId();
                break;
            case ENERGY_MODULE:
                EnergyModule energyModule = super.getFactory().createCryogenRod(outputOrAbsorbtion);
                this.addEnergyModuleToReactor(reactorId, energyModule);
                moduleId = energyModule.getId();
                break;
        }
        return String.format("Added %s - %d to Reactor - %d", type, moduleId, reactorId);
    }

    private void addAbsorbingModuleToReactor(int reactorID, AbsorbingModule absorbingModule) {
        super.getDatabase()
                .addAbsorbingModuleToReactor(reactorID, absorbingModule);

    }

    private void addEnergyModuleToReactor(int reactorID, EnergyModule energyModule) {
        super.getDatabase()
                .addEnergyModuleToReactor(reactorID, energyModule);

    }

    private String getModuleType(String type) {
        return this.moduleTypes.get(type);
    }
}
