package repositories;

import models.cards.interfaces.Card;
import repositories.interfaces.CardRepository;

import java.util.List;

public class CardRepositoryImpl implements CardRepository {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public List<Card> getCards() {
        return null;
    }

    @Override
    public void add(Card card) {

    }

    @Override
    public boolean remove(Card card) {
        return false;
    }

    @Override
    public Card find(String name) {
        return null;
    }
}
