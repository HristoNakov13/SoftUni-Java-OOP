package viceCity.core;

import viceCity.common.ConstantMessages;
import viceCity.core.interfaces.Controller;
import viceCity.models.guns.Gun;
import viceCity.models.guns.Pistol;
import viceCity.models.guns.Rifle;
import viceCity.models.neighbourhood.GangNeighbourhood;
import viceCity.models.neighbourhood.Neighbourhood;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;

import java.util.*;

public class ControllerImpl implements Controller {
    private Player mainPlayer;
    private List<Player> players;
    private Deque<Gun> gunQueue;
    private Neighbourhood gangNeighbourhood;

    public ControllerImpl() {
        this.mainPlayer = new MainPlayer();
        this.players = new ArrayList<>();
        this.gunQueue = new ArrayDeque<>();
        this.gangNeighbourhood = new GangNeighbourhood();
    }
    @Override
    public String addPlayer(String name) {
        Player player = new CivilPlayer(name);
        this.players.add(player);
        return String.format(ConstantMessages.PLAYER_ADDED, name);
    }


    @Override
    public String addGun(String type, String name) {
        Gun gun = null;
        String output = ConstantMessages.GUN_TYPE_INVALID;
        if (type.equals("Pistol")) {
            gun = new Pistol(name);
            output = String.format(ConstantMessages.GUN_ADDED, name, type);
        } else if (type.equals("Rifle")) {
            gun = new Rifle(name);
            output = String.format(ConstantMessages.GUN_ADDED, name, type);
        }
        if (gun != null) {
            this.gunQueue.offer(gun);
        }
        return output;
    }

    @Override
    public String addGunToPlayer(String name) {
        if (this.gunQueue.isEmpty()) {
            return ConstantMessages.GUN_QUEUE_IS_EMPTY;
        }
        String output;
        Gun gun;
        if (name.equals("Vercetti")) {
            gun = this.gunQueue.poll();
            this.mainPlayer.getGunRepository().add(gun);
            output = String.format(ConstantMessages.GUN_ADDED_TO_MAIN_PLAYER, gun.getName(), "Tommy Vercetti");
        } else {
            Player player = null;
            for (Player player1 : players) {
                if (player1.getName().equals(name)) {
                    player = player1;
                    break;
                }
            }
            if (player != null) {
                gun = this.gunQueue.poll();
                player.getGunRepository().add(gun);
                output = String.format(ConstantMessages.GUN_ADDED_TO_CIVIL_PLAYER, gun.getName(), name);
            } else {
                output = ConstantMessages.CIVIL_PLAYER_DOES_NOT_EXIST;
            }
        }
        return output;
    }

    @Override
    public String fight() {
        int initialHp = this.mainPlayer.getLifePoints();
        int initialCivieHP = 0;
        for (Player player : this.players) {
            initialCivieHP += player.getLifePoints();
        }


        this.gangNeighbourhood.action(mainPlayer, players);

        int afterFightHp = this.mainPlayer.getLifePoints();
        int afterFightHpCivies = 0;

        for (Player player : this.players) {
            afterFightHpCivies += player.getLifePoints();
        }
        String output = "";

        if (initialHp == afterFightHp && initialCivieHP == afterFightHpCivies) {
            output = ConstantMessages.FIGHT_HOT_HAPPENED;
        } else {
            StringBuilder report = new StringBuilder();
            report
                    .append(ConstantMessages.FIGHT_HAPPENED)
                    .append(System.lineSeparator());
            ArrayDeque<String> markForRemoval = new ArrayDeque<>();
            int killedCivies = 0;
            for (Player player : this.players) {
                if (!player.isAlive()) {
                    killedCivies ++;
                    markForRemoval.offer(player.getName());
                }
            }
            while (!markForRemoval.isEmpty()) {
                String player = markForRemoval.poll();
                this.players.removeIf(current -> current.getName().equals(player));
            }
            report.append(String.format(ConstantMessages.MAIN_PLAYER_LIVE_POINTS_MESSAGE, mainPlayer.getLifePoints()))
                    .append(System.lineSeparator())
                    .append(String.format(ConstantMessages.MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE, killedCivies))
                    .append(System.lineSeparator())
                    .append(String.format(ConstantMessages.CIVIL_PLAYERS_LEFT_MESSAGE, this.players.size()));
            output = report.toString();
        }
        return output;
    }
}
