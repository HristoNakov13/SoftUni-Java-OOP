package militaryElite.soldiers;

import militaryElite.entities.Repair;
import militaryElite.baseClasses.SpecialisedSoldier;
import militaryElite.enumartions.Corps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Engineer extends SpecialisedSoldier {
    private List<Repair> repairs;


    public Engineer(int id, String firstName, String lastName, double salary, String corps, String... repairs) {
        super(id, firstName, lastName, salary, corps);
        this.repairs = new ArrayList<>();
        this.addRepairs(repairs);
    }

    private void addRepair(Repair repair) {
        this.repairs.add(repair);
    }

    private void addRepairs(String[] repairs) {
        for (int i = 0; i < repairs.length - 1; i++) {
            String repairName = repairs[i];
            int hoursWorked = Integer.parseInt(repairs[++i]);

            Repair repair = new Repair(repairName, hoursWorked);
            this.addRepair(repair);
        }
    }
    private void addRepairs(Collection<Repair> repairs) {
        for (Repair repair : repairs) {
            this.addRepair(repair);
        }
    }

    @Override
    public String toString() {
        StringBuilder engineerInfoAsString = new StringBuilder();
        engineerInfoAsString
                .append(super.toString())
                .append(System.lineSeparator())
                .append("Repairs:")
                .append(System.lineSeparator());

        for (Repair repair : this.repairs) {
            engineerInfoAsString
                    .append("\t")
                    .append(repair.toString())
                    .append(System.lineSeparator());
        }
        return engineerInfoAsString.toString();
    }
}
