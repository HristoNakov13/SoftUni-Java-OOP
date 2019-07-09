package barracksReturnOfTheDependancies.data;

import barracksReturnOfTheDependancies.contracts.Repository;
import barracksReturnOfTheDependancies.contracts.Unit;

import java.util.Map;
import java.util.TreeMap;

public class UnitRepository implements Repository {

    private Map<String, Integer> amountOfUnits;

    public UnitRepository() {
        this.amountOfUnits = new TreeMap<>();
    }

    public void addUnit(Unit unit) {
        String unitType = unit.getClass().getSimpleName();
        if (!this.amountOfUnits.containsKey(unitType)) {
            this.amountOfUnits.put(unitType, 0);
        }

        int newAmount = this.amountOfUnits.get(unitType) + 1;
        this.amountOfUnits.put(unitType, newAmount);
    }

    public String getStatistics() {
        StringBuilder statBuilder = new StringBuilder();
        for (Map.Entry<String, Integer> entry : amountOfUnits.entrySet()) {
            String formatedEntry =
                    String.format("%s -> %d%n", entry.getKey(), entry.getValue());
            statBuilder.append(formatedEntry);
        }
        statBuilder.setLength(
                statBuilder.length() - System.lineSeparator().length());

        return statBuilder.toString();
    }

    private boolean containsUnit(String unit) {
        return this.amountOfUnits.containsKey(unit);
    }

    private boolean canRemoveUnit(String unit) {
        return this.amountOfUnits.get(unit) > 0;
    }

    public void removeUnit(String unitType) {
        if (this.containsUnit(unitType) && this.canRemoveUnit(unitType)) {
            this.amountOfUnits.put(unitType, this.amountOfUnits.get(unitType) - 1);
            System.out.print(String.format("%s retired!", unitType));
            return;
        }
        System.out.print("No such units in repository.");
    }
}
