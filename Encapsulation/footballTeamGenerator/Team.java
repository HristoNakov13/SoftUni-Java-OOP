package footballTeamGenerator;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private List<Player> players;

    public Team(String name) {
        this.setName(name);
        this.players = new ArrayList<>();
    }

    private void setName(String name) {
        if (name.isEmpty() || name.equals("\\s")) {
            throw new IllegalArgumentException("A name should not be empty.");
        }
        this.name = name;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    private String getName() {
        return name;
    }

    public void remove(String name) {
        if (!this.players.removeIf(player -> player.getName().equals(name))) {
            System.out.println(String.format("Player %s is not in %s team."
                    , name, this.getName()));
        }
    }

    public double getRating() {
        double rating = 0;
        for(Player player : this.players) {
            rating += player.overallSkillLevel();
        }
        return rating;
    }
}
