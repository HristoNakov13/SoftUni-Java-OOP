package core;

import common.ConstantMessages;
import core.factory.Factory;
import core.factory.FactoryImpl;
import core.interfaces.ManagerController;
import models.battleFields.BattleFieldImpl;
import models.battleFields.interfaces.Battlefield;
import models.cards.interfaces.Card;
import models.players.interfaces.Player;
import repositories.CardRepositoryImpl;
import repositories.PlayerRepositoryImpl;
import repositories.interfaces.CardRepository;
import repositories.interfaces.PlayerRepository;

import java.util.List;


public class ManagerControllerImpl implements ManagerController {
    private PlayerRepository playerRepository;
    private CardRepository cardRepository;
    private Factory factory;
    private Battlefield battlefield;

    public ManagerControllerImpl() {
        this.playerRepository = new PlayerRepositoryImpl();
        this.cardRepository = new CardRepositoryImpl();
        this.factory = new FactoryImpl();
        this.battlefield = new BattleFieldImpl();
    }

    @Override
    public String addPlayer(String type, String username) {
        this.playerRepository.add(this.factory.createPlayer(type, username));
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_PLAYER, type, username);
    }

    @Override
    public String addCard(String type, String name) {
        this.cardRepository.add(this.factory.createCard(type, name));
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_CARD, type, name);
    }

    @Override
    public String addPlayerCard(String username, String cardName) {
        Card card = this.cardRepository.find(cardName);
        Player player = this.playerRepository.find(username);

        player.getCardRepository().add(card);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_PLAYER_WITH_CARDS, cardName, username);
    }

    @Override
    public String fight(String attackUser, String enemyUser) {
        Player attacker = this.playerRepository.find(attackUser);
        Player enemy = this.playerRepository.find(enemyUser);
        this.battlefield.fight(attacker, enemy);

        return String.format(ConstantMessages.FIGHT_INFO, attacker.getHealth(), enemy.getHealth());
    }

    @Override
    public String report() {
        StringBuilder report = new StringBuilder();
        List<Player> players = this.playerRepository.getPlayers();

        for (Player player : players) {
            List<Card> cards = player.getCardRepository().getCards();
            report.append(String.format(
                            ConstantMessages.PLAYER_REPORT_INFO
                            , player.getUsername()
                            , player.getHealth()
                            , cards.size()))
                    .append(System.lineSeparator());

            for (Card card : cards) {
                report.append(String.format(
                                ConstantMessages.CARD_REPORT_INFO
                                , card.getName()
                                , card.getDamagePoints()))
                        .append(System.lineSeparator());
            }
            report
                    .append(ConstantMessages.DEFAULT_REPORT_SEPARATOR)
                    .append(System.lineSeparator());
        }
        return report.toString().trim();
    }
}
