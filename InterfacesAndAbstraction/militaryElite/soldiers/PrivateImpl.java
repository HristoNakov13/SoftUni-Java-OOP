package militaryElite.soldiers;

import militaryElite.baseClasses.SoldierImpl;

public class PrivateImpl extends SoldierImpl {
    private double salary;
    public PrivateImpl(int id, String firstName, String lastName, double salary) {
        super(id, firstName, lastName);
        this.salary = salary;
    }

    public double getSalary() {
        return this.salary;
    }

    @Override
    public String toString() {
        return String.format("%s Salary: %.2f%n", super.toString(), this.getSalary());
    }
}
