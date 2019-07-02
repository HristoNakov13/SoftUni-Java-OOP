package footballTeamGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Team> teams = new HashMap<>();
        String input;

        while (!"END".equals(input = bfr.readLine())) {

            String[] line = input.split(";");

            String command = line[0];
            String teamName = line[1];
            String playerName;
            Team team;
            Player player;

            switch (command) {
                case "Team":
                    try {
                        team = new Team(teamName);
                        teams.put(teamName, team);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "Add":
                    if (!teams.containsKey(teamName)) {
                        System.out.println(String.format("Team %s does not exist.", teamName));
                        continue;
                    }
                    try {
                        playerName = line[2];
                        int endurance = Integer.parseInt(line[3]);
                        int sprint = Integer.parseInt(line[4]);
                        int dribble = Integer.parseInt(line[5]);
                        int passing = Integer.parseInt(line[6]);
                        int shooting = Integer.parseInt(line[7]);

                        player = new Player(playerName, endurance, sprint, dribble, passing, shooting);
                        team = teams.get(teamName);
                        team.addPlayer(player);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "Remove":
                    if (!teams.containsKey(teamName)) {
                        System.out.println(String.format("Team %s does not exist.", teamName));
                        continue;
                    }
                    playerName = line[2];
                    team = teams.get(teamName);
                    team.remove(playerName);
                    break;
                case "Rating":
                    if (!teams.containsKey(teamName)) {
                        System.out.println(String.format("Team %s does not exist.", teamName));
                        continue;
                    }
                    team = teams.get(teamName);
                    double rating = team.getRating();
//                    int round = (int) Math.round(rating);
                    System.out.println(String.format("%s - %.0f", teamName, rating));
                    break;
            }
        }
    }
}
