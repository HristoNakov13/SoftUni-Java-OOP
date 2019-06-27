package cardsWithPower;

public class Card {
    private Rank rank;
    private Suit suit;

    public Card(String rank, String suit) {
        this.rank = Rank.valueOf(rank);
        this.suit = Suit.valueOf(suit);
    }

    public int power() {
        return this.rank.getValue() + this.suit.getValue();
    }
}
