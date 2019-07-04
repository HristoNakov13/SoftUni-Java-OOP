package militaryElite.comparators;

import militaryElite.baseClasses.Soldier;
import militaryElite.soldiers.PrivateImpl;

import java.util.Comparator;

public class PrivatesComparator implements Comparator<Soldier> {

    @Override
    public int compare(Soldier o1, Soldier o2) {
        return Integer.compare(o2.getId(), o1.getId());
    }
}
