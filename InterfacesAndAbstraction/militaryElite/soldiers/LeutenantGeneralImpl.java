package militaryElite.soldiers;

import militaryElite.baseClasses.Soldier;
import militaryElite.comparators.PrivatesComparator;
import militaryElite.repositories.SoldierRepository;

import java.util.TreeSet;

public class LeutenantGeneralImpl extends PrivateImpl {
    private TreeSet<Soldier> privates;
    private static final PrivatesComparator comparator;

    static {
        comparator = new PrivatesComparator();
    }

    public LeutenantGeneralImpl(int id, String firstName, String lastName, double salary, String... privatesIDs) {
        super(id, firstName, lastName, salary);
        this.privates = new TreeSet<>(comparator);
        this.addPrivates(privatesIDs);
    }

    private void addPrivates(String[] privatesIDs) {
        for (String id : privatesIDs) {
            int idNum = Integer.parseInt(id);

            Soldier soldier = SoldierRepository.getPrivateById(idNum);
            if (soldier != null) {
                this.privates.add(soldier);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder lieutenantInfoAsString = new StringBuilder();
        lieutenantInfoAsString
                .append(super.toString())
                .append("Privates:")
                .append(System.lineSeparator());

        for (Soldier PVT : this.privates) {
            lieutenantInfoAsString
                    .append("\t")
                    .append(PVT.toString());
        }
        return lieutenantInfoAsString.toString();
    }
}
