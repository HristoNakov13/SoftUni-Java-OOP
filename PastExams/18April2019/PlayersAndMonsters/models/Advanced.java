package models;

import repositories.interfaces.CardRepository;

public class Advanced extends BasePlayer {
    public Advanced(CardRepository repository, String username, int health) {
        super(repository, username, health);
    }
}
