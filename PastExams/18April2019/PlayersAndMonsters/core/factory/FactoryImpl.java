package core.factory;

import models.players.Advanced;
import models.players.Beginner;
import models.cards.MagicCard;
import models.cards.TrapCard;
import models.cards.interfaces.Card;
import models.players.interfaces.Player;
import repositories.CardRepositoryImpl;

public class FactoryImpl implements Factory {
    @Override
    public Player createPlayer(String type, String username) {
        Player player = null;
        if (type.equals("Beginner")) {
            player = new Beginner(new CardRepositoryImpl(), username);
        } else if (type.equals("Advanced")) {
            player = new Advanced(new CardRepositoryImpl(), username);
        }
        return player;
    }

    @Override
    public Card createCard(String type, String name) {

        if (type.equals("Magic")) {
           return new MagicCard(name);
        } else {
            return new TrapCard(name);
        }

    }
}
