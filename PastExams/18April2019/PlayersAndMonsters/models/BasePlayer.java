package models;

import models.players.interfaces.Player;
import repositories.interfaces.CardRepository;

//•	username – String (If the username is null or empty, throw an IllegalArgumentException with message "Player's username cannot be null or an empty string. ")
//•	health – int -  the health of а player (if the health is below 0, throw an IllegalArgumentException with message "Player's health bonus cannot be less than zero. ")
//•	cardRepository – CardRepository repository of all user's cards.
//•	isDead – boolean – shows if player is alive(false) or dead(true).

public abstract class BasePlayer implements Player {
    private String username;
    private int health;
    private CardRepository repository;
    private boolean isDead;

    protected BasePlayer(CardRepository repository, String username, int health) {
        this.repository = repository;
        this.username = username;
        this.health = health;
        this.isDead = false;
    }

    @Override
    public CardRepository getCardRepository() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public int getHealth() {
        return 0;
    }

    @Override
    public void setHealth(int healthPoints) {

    }

    @Override
    public boolean isDead() {
        return false;
    }

    @Override
    public void takeDamage(int damagePoints) {

    }
}
