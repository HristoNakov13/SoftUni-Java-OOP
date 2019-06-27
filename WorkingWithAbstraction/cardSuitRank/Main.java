package cardSuitRank;

import cardSuitRank.CardRank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));


//        for (CardSuits card : CardSuits.values()) {
//            System.out.println(String.format("Ordinal value: %d; Name value: %s"
//            , card.ordinal(), CardSuits.valueOf(card.name())));
//        }

        String input = bfr.readLine();
        System.out.println(input + ":");

        for (CardRank card : CardRank.values()) {
            System.out.println(String.format("Ordinal value: %d; Name value: %s"
            , card.ordinal(), CardRank.valueOf(card.toString())));
        }
    }
}
