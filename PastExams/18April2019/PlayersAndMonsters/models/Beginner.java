package models;

import repositories.interfaces.CardRepository;

public class Beginner extends BasePlayer {
    protected Beginner(CardRepository repository, String username, int health) {
        super(repository, username, health);
    }
}
