package militaryElite.baseClasses;

import militaryElite.enumeration.Corps;

public class SpecialisedSoldier extends SoldierImpl {
    private final double salary;
    private String corps;

    public SpecialisedSoldier(int id, String firstName, String lastName, double salary, String corps) {
        super(id, firstName, lastName);
        this.setCorps(corps);
        this.salary = salary;

    }

    public double getSalary() {
        return salary;
    }

    public String getCorps() {
        return corps;
    }

    public void setCorps(String corps) {
        if (isValidCorps(corps)) {
            this.corps = corps;
        } else {
            throw new IllegalArgumentException("Invalid corps");
        }
    }

    private boolean isValidCorps(String corps) {
        try {
            Corps.valueOf(corps);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("%s Salary: %.2f%nCorps: %s"
                , super.toString(), this.getSalary(), this.getCorps());
    }
}
