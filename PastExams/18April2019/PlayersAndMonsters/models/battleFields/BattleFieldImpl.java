package models.battleFields;

import models.battleFields.interfaces.Battlefield;
import models.cards.interfaces.Card;
import models.players.Beginner;
import models.players.interfaces.Player;



public class BattleFieldImpl implements Battlefield {
    private static final int BEGINNER_HEALTH_BONUS = 40;
    private static final int BEGINNER_CARDS_DAMAGE_BONUS = 30;

    @Override
    public void fight(Player attackPlayer, Player enemyPlayer) {
        if (attackPlayer.isDead() || enemyPlayer.isDead()) {
            throw new IllegalArgumentException("Player is dead!");
        }
        this.battlefieldPreparation(attackPlayer, enemyPlayer);

        int attackerDmgPoints = this.getPlayerAttackPoints(attackPlayer);
        int enemyDmgPoints = this.getPlayerAttackPoints(enemyPlayer);

        while (!attackPlayer.isDead()) {
            enemyPlayer.takeDamage(attackerDmgPoints);
            if (enemyPlayer.isDead()) {
                break;
            }
            attackPlayer.takeDamage(enemyDmgPoints);
        }
    }

    private void battlefieldPreparation(Player attacker, Player enemy) {
        attacker.setHealth(attacker.getHealth() + getCardsHealthModifier(attacker));
        enemy.setHealth(enemy.getHealth() + getCardsHealthModifier(enemy));

        if (this.isBeginner(attacker)) {
            this.setPlayerBonuses(attacker);
        }
        if (this.isBeginner(enemy)) {
            this.setPlayerBonuses(enemy);
        }
    }

    private int getCardsHealthModifier(Player player) {
//        int healthModifier = 0;
//        List<Card> cards = player.getCardRepository().getCards();
//        for (Card card : cards) {
//            healthModifier += card.getHealthPoints();
//        }
//        return healthModifier;
        return player.getCardRepository()
                .getCards()
                .stream()
                .mapToInt(Card::getHealthPoints)
                .sum();
    }

    private void setPlayerBonuses(Player player) {
        int healthBonus = player.getHealth() + BEGINNER_HEALTH_BONUS;
        player.setHealth(healthBonus);

        player
                .getCardRepository()
                .getCards()
                .forEach(card ->
                        card.setDamagePoints(
                                card.getDamagePoints() + BEGINNER_CARDS_DAMAGE_BONUS));
    }

    private boolean isBeginner(Player player) {
        return player instanceof Beginner;
    }

    private int getPlayerAttackPoints(Player player) {
        return player
                .getCardRepository()
                .getCards()
                .stream()
                .mapToInt(Card::getDamagePoints)
                .sum();
    }
}
