package militaryElite.soldiers;

import militaryElite.entities.Mission;
import militaryElite.baseClasses.SpecialisedSoldier;
import militaryElite.enumartions.MissionStates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Commando extends SpecialisedSoldier {
    private List<Mission> missions;

    public Commando(int id, String firstName, String lastName, double salary, String corps, String ... missions) {
        super(id, firstName, lastName, salary, corps);
        this.missions = new ArrayList<>();
        this.addMissions(missions);
    }

    private void addMissions(String[] missions) {
        for (int i = 0; i < missions.length - 1; i++) {
            String codeName = missions[i];
            String state = missions[++i];
            try {
                MissionStates.valueOf(state);
            } catch (Exception e) {
                continue;
            }
            Mission current = new Mission(codeName, state);
            this.addMission(current);
        }
    }

    private void addMissions(Collection<Mission> missions) {

        for (Mission mission : missions) {
            this.addMission(mission);
        }
    }

    public void completeMission(String mission) {
        this.missions.removeIf(mis -> mis.getCodeName().equals(mission));
    }

    private void addMission(Mission mission) {
        this.missions.add(mission);
    }

    @Override
    public String toString() {
        StringBuilder commandoInfoAsString = new StringBuilder();
        commandoInfoAsString
                .append(super.toString())
                .append(System.lineSeparator())
                .append("Missions:")
                .append(System.lineSeparator());

        for (Mission mission : this.missions) {
                commandoInfoAsString
                        .append("\t")
                        .append(mission.toString())
                        .append(System.lineSeparator());
        }
        return commandoInfoAsString.toString();
    }
}
