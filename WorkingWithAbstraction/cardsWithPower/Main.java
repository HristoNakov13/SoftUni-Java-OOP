package cardsWithPower;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

        String rank = bfr.readLine();
        String suit = bfr.readLine();
        Card card = new Card(rank, suit);
        int cardPower = card.power();
        System.out.println(String.format("Card name: %s of %s; Card power: %d"
                                            , rank, suit, cardPower));
    }
}
