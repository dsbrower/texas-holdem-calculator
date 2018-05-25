package org.duckdns.dbro94.texasholdemcalculator.util;

import org.duckdns.dbro94.texasholdemcalculator.domain.Card;
import org.duckdns.dbro94.texasholdemcalculator.domain.CardSuit;
import org.duckdns.dbro94.texasholdemcalculator.domain.CardValue;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    private Deck() {}

    public static List<Card> getDeck() {
        List<Card> deck = new ArrayList<>();
        for (int v = 0; v < 13; v++) {

            CardValue value = CardValue.values()[v];

            for (int s = 0; s < 4; s++) {

                CardSuit suit = CardSuit.values()[s];

                Card card = new Card(value, suit);
                deck.add(card);
            }
        }
        return deck;
    }

}