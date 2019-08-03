package cresla.core.commands;

import cresla.interfaces.Database;
import cresla.interfaces.Reactor;
import cresla.models.reactors.CryoReactor;
import cresla.models.reactors.HeatReactor;

import java.util.List;

public class ExitCommand extends CommandImpl {
    public ExitCommand(Database database) {
        super(database);
    }

    @Override
    public String execute(List<String> args) {
        StringBuilder report = new StringBuilder();
        int cryoReactorsCount = 0;
        int heatReactorsCount = 0;
        long totalEnergyOutput = 0;
        long totalHeatAbsorption = 0;

        for (Reactor reactor : super.getDatabase().getAllReactorsAsIterableCollection()) {
            if (reactor instanceof CryoReactor) {
                cryoReactorsCount++;
            } else if (reactor instanceof HeatReactor) {
                heatReactorsCount++;
            }

            totalEnergyOutput += reactor.getTotalEnergyOutput();
            totalHeatAbsorption += reactor.getTotalHeatAbsorbing();
        }

        report
                .append(String.format("Cryo Reactors: %d%n", cryoReactorsCount))
                .append(String.format("Heat Reactors: %d%n", heatReactorsCount))
                .append(String.format("Energy Modules: %d%n", super.getDatabase().getTotalEnergyModules()))
                .append(String.format("Absorbing Modules: %d%n", super.getDatabase().getTotalAbsorbingModules()))
                .append(String.format("Total Energy Output: %d%n", totalEnergyOutput))
                .append(String.format("Total Heat Absorbing: %d", totalHeatAbsorption));

        return report.toString();
    }
}
