package militaryElite.soldiers;

import militaryElite.baseClasses.SoldierImpl;

public class Spy extends SoldierImpl {
    private String codeNumber;

    public Spy(int id, String firstName, String lastName, String codeNumber) {
        super(id, firstName, lastName);
        this.codeNumber = codeNumber;
    }

    public String getCodeNumber() {
        return codeNumber;
    }

    @Override
    public String toString() {
        return String.format("%s%nCode Number: %s%n",super.toString(), this.getCodeNumber());
    }
}
