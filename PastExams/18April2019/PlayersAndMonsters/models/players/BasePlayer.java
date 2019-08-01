package models.players;

import models.players.interfaces.Player;
import repositories.interfaces.CardRepository;

public abstract class BasePlayer implements Player{
    private String username;
    private int health;
    private CardRepository repository;

    BasePlayer(CardRepository repository, String username, int health) {
        this.repository = repository;
        this.setUsername(username);
        this.setHealth(health);
    }

    @Override
    public CardRepository getCardRepository() {
        return this.repository;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    private void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Player's username cannot be null or an empty string.");
        }
        this.username = username;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void setHealth(int healthPoints) {
        if (healthPoints < 0) {
            throw new IllegalArgumentException("Player's health bonus cannot be less than zero.");
        }
        this.health = healthPoints;
    }

    @Override
    public boolean isDead() {
        return this.health == 0;
    }

    @Override
    public void takeDamage(int damagePoints) {
        if (damagePoints < 0) {
            throw new IllegalArgumentException("Damage points cannot be less than zero.");
        }
        int healthPool = this.getHealth() - damagePoints;
        if(healthPool < 0) {
            healthPool = 0;
        }
        this.setHealth(healthPool);
    }

//    compareToAllowed?

//    @Override
//    public int compareTo(Player o) {
//        return 0;
//    }
}
