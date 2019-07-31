package models;

import models.cards.interfaces.Card;

//•	name – String (If the card name is null or empty throw an IllegalArgumentException with message "Card's name cannot be null or an empty string.")
//•	damagePoints – int (If the damage points are below zero, throw an IllegalArgumentException with message "Card's damage points cannot be less than zero.")
//•	healthPoints - int (If the health points are below zero, throw an IllegalArgumentException with message "Card's HP cannot be less than zero.")

public abstract class BaseCard implements Card {
    private String name;
    private int damagePoints;
    private int healthPoints;

    protected BaseCard(String name, int damagePoints, int healthPoints) {
        this.name = name;
        this.damagePoints = damagePoints;
        this.healthPoints = healthPoints;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getDamagePoints() {
        return 0;
    }

    @Override
    public void setDamagePoints(int damagePoints) {

    }

    @Override
    public int getHealthPoints() {
        return 0;
    }
}
