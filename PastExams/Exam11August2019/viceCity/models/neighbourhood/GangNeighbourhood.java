package viceCity.models.neighbourhood;

import viceCity.models.guns.Gun;
import viceCity.models.players.Player;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;

public class GangNeighbourhood implements Neighbourhood {

    @Override
    public void action(Player mainPlayer, Collection<Player> civilPlayers) {
        this.mainPlayerAttacksNPCs(mainPlayer, civilPlayers);

        this.NPCsAttackMainPlayer(mainPlayer, civilPlayers);
    }

    private void mainPlayerAttacksNPCs(Player mainPlayer, Collection<Player> civilPlayers) {
        for (Player civilPlayer : civilPlayers) {
            this.shootEnemy(mainPlayer, civilPlayer);
        }
    }

    private void NPCsAttackMainPlayer(Player mainPlayer, Collection<Player> civilPlayers) {
        for (Player civilPlayer : civilPlayers) {
            if (!mainPlayer.isAlive()) {
                break;
            }
            if (civilPlayer.isAlive()) {
                this.shootEnemy(civilPlayer, mainPlayer);
            }
        }
    }

    private void shootEnemy(Player attacker, Player victim) {
        Iterator<Gun> armory = attacker.getGunRepository().getModels().iterator();

        while (armory.hasNext() && victim.isAlive()) {
            Gun currentGun = armory.next();

            while (currentGun.canFire() && victim.isAlive()) {
                int dmg = currentGun.fire();
                victim.takeLifePoints(dmg);
            }
        }
    }
}