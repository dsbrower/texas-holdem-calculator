package org.duckdns.dbro94.texasholdemcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.duckdns.dbro94.texasholdemcalculator.domain.Card;
import org.duckdns.dbro94.texasholdemcalculator.util.Deck;
import org.duckdns.dbro94.texasholdemcalculator.util.HandEvaluator;
import org.duckdns.dbro94.texasholdemcalculator.util.Rank;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private static void calc(List<Card> handOrig) {
        Map<Rank, Integer> stats = new LinkedHashMap<>();

        for (int i = 0; i < 9; i++) {
            Rank key = Rank.values()[i];
            stats.put(key, 0);
        }

        for (int run = 0; run < 100000; run++) {
            List<Card> deck = Deck.getDeck();
            Collections.shuffle(deck);
            List<Card> hand = new LinkedList<>(handOrig);
            for (Card card : hand) {
                for (Card c : deck) {
                    if (c.getSuit().equals(card.getSuit()) && c.getValue().getCardValue() == card.getValue().getCardValue()) {
                        deck.remove(c);
                        break;
                    }
                }
            }

            for (int j = hand.size(); j < 7; j++) {
                hand.add(deck.remove(0));
            }

            Rank rank = HandEvaluator.evaluate(hand);
            int value = stats.get(rank);
            stats.put(rank, ++value);
        }

        for (Map.Entry<Rank, Integer> entry : stats.entrySet()) {
            String out = entry.getKey() + "                    ";
            out = out.substring(0, 20) + round((entry.getValue() / 100000.0 * 100), 2) + "%";
            System.out.println(out);
        }


        print("\r\n\r\n");
        print("\r\n\r\n");
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private static void print(Object o) {
        System.out.println(o);
    }
}
