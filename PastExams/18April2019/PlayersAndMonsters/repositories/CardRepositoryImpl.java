package repositories;

import models.cards.interfaces.Card;
import repositories.interfaces.CardRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CardRepositoryImpl implements CardRepository {
    private Map<String, Card> cards;

    public CardRepositoryImpl() {
        this.cards = new LinkedHashMap<>();
    }

    @Override
    public int getCount() {
        return this.cards.size();
    }

    @Override
    public List<Card> getCards() {
        return new ArrayList<>(this.cards.values());
    }

    @Override
    public void add(Card card) {
        if (card == null) {
            throw new IllegalArgumentException("Card cannot be null!");
        }
        if (this.cards.containsKey(card.getName())) {
            throw new IllegalArgumentException(String.format("Card %s already exists!", card.getName()));
        }
        this.cards.put(card.getName(), card);
    }

    @Override
    public boolean remove(Card card) {
        if (this.cards.containsKey(card.getName())) {
            this.cards.remove(card.getName());
            return true;
        }
        return false;
    }

    @Override
    public Card find(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Card's name cannot be null or an empty string.");
        }
        return this.cards.get(name);
    }
}
