package models.players;

import repositories.interfaces.CardRepository;

public class Advanced extends BasePlayer {
    private static final int DEFAULT_HEALTH_POINTS = 250;

    public Advanced(CardRepository repository, String username) {
        super(repository, username, DEFAULT_HEALTH_POINTS);
    }
}
