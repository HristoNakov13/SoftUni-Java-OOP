package core.factory;

import models.cards.interfaces.Card;
import models.players.interfaces.Player;

public interface Factory {
    Player createPlayer(String type, String username);

    Card createCard(String type, String username);
}
