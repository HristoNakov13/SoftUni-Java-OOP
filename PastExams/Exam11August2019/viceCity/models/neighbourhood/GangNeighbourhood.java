package viceCity.models.neighbourhood;

import viceCity.models.guns.Gun;
import viceCity.models.players.Player;

import java.util.ArrayDeque;
import java.util.Collection;

public class GangNeighbourhood implements Neighbourhood {

    @Override
    public void action(Player mainPlayer, Collection<Player> civilPlayers) {
        boolean allCiviesAreDead = false;
        boolean hasGuns = true;

        ArrayDeque<Gun> guns = new ArrayDeque<>();
        mainPlayer.getGunRepository().getModels().forEach(guns::offer);

        Gun currentGun;
        if (!guns.isEmpty()) {
            currentGun = guns.poll();

            for (Player civilPlayer : civilPlayers) {
                if (guns.isEmpty() && !currentGun.canFire()) {
                    break;
                }
                if (!currentGun.canFire()) {
                    mainPlayer.getGunRepository().remove(currentGun);
                    currentGun = guns.poll();
                }

                while (civilPlayer.isAlive() && currentGun.canFire()) {
                    int dmg = currentGun.fire();
                    civilPlayer.takeLifePoints(dmg);
                }
            }

            for (Player civilPlayer : civilPlayers) {
                if (!mainPlayer.isAlive()) {
                    break;
                }

                if (civilPlayer.isAlive()) {
                    ArrayDeque<Gun> civieArsenal = new ArrayDeque<>();
                    civilPlayer.getGunRepository().getModels().forEach(civieArsenal::offer);

                    while (!civieArsenal.isEmpty()) {
                        Gun currentWeapon = civieArsenal.poll();
                        while (mainPlayer.isAlive() && currentWeapon.canFire()) {
                            int dmg = currentWeapon.fire();
                            mainPlayer.takeLifePoints(dmg);
                        }
                        if (!currentWeapon.canFire()) {
                            civilPlayer.getGunRepository().remove(currentWeapon);
                        }
                        if (!mainPlayer.isAlive()) {
                            break;
                        }
                    }
                }

            }
        }
    }
}

