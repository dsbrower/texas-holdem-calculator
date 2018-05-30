package org.duckdns.dbro94.texasholdemcalculator.main;

import org.duckdns.dbro94.texasholdemcalculator.domain.Card;
import org.duckdns.dbro94.texasholdemcalculator.util.CardMapper;
import org.duckdns.dbro94.texasholdemcalculator.util.Deck;
import org.duckdns.dbro94.texasholdemcalculator.util.HandEvaluator;
import org.duckdns.dbro94.texasholdemcalculator.domain.Rank;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try (Scanner reader = new Scanner(System.in)) {

            Card card1 = null;
            while (null == card1) {
                print("Enter Card 1:");
                String c = reader.nextLine();
                card1 = CardMapper.getCard(c);
            }

            Card card2 = null;
            while (null == card2) {
                print("Enter Card 2:");
                String c = reader.nextLine();
                card2 = CardMapper.getCard(c);
            }


            List<Card> hand = new LinkedList<>();
            hand.add(card1);
            hand.add(card2);

            calc(hand);

            Card card3 = null;
            while (null == card3) {
                print("Enter Card 3:");
                String c = reader.nextLine();
                card3 = CardMapper.getCard(c);
            }

            Card card4 = null;
            while (null == card4) {
                print("Enter Card 4:");
                String c = reader.nextLine();
                card4 = CardMapper.getCard(c);
            }

            Card card5 = null;
            while (null == card5) {
                print("Enter Card 5:");
                String c = reader.nextLine();
                card5 = CardMapper.getCard(c);
            }

            hand.add(card3);
            hand.add(card4);
            hand.add(card5);

            calc(hand);

            Card card6 = null;
            while (null == card6) {
                print("Enter Card 6:");
                String c = reader.nextLine();
                card6 = CardMapper.getCard(c);
            }

            hand.add(card6);

            calc(hand);

            Card card7 = null;
            while (null == card7) {
                print("Enter Card 7:");
                String c = reader.nextLine();
                card7 = CardMapper.getCard(c);
            }

            hand.add(card6);

            calc(hand);

        }
        catch (Exception e) {
            print(e.getMessage());
        }
    }

    private static void calc(List<Card> handOrig) {
        print("\r\n\r\n");
        print("\r\n\r\n");

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