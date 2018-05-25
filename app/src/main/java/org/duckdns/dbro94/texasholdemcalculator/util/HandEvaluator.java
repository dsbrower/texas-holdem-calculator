package org.duckdns.dbro94.texasholdemcalculator.util;

import org.duckdns.dbro94.texasholdemcalculator.domain.Card;
import org.duckdns.dbro94.texasholdemcalculator.domain.CardSuit;
import org.duckdns.dbro94.texasholdemcalculator.domain.CardValue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HandEvaluator {

    public static Rank evaluate(List<Card> hand) {

        if (isStraightFlush(hand)) {
            return Rank.STRAIGHT_FLUSH;
        }
        else if (isFourOfAKind(hand)) {
            return Rank.FOUR_OF_A_KIND;
        }
        else if (isFullHouse(hand)) {
            return Rank.FULL_HOUSE;
        }
        else if (isFlush(hand)) {
            return Rank.FLUSH;
        }
        else if (isStraight(hand)) {
            return Rank.STRAIGHT;
        }
        else if (isThreeOfAKind(hand)) {
            return Rank.THREE_OF_A_KIND;
        }
        else if (isTwoPair(hand)) {
            return Rank.TWO_PAIR;
        }
        else if (isPair(hand)) {
            return Rank.PAIR;
        }
        else {
            return Rank.HIGH_CARD;
        }

    }

    private static boolean isStraightFlush(List<Card> hand) {
        CardSuit suit = null;

        Map<CardSuit, Integer> suitCount = createSuitCountMap();
        for (Card card : hand) {
            CardSuit key = card.getSuit();
            int value = suitCount.get(key);
            suitCount.put(key, ++value);
        }
        for (Map.Entry<CardSuit, Integer> entry : suitCount.entrySet()) {
            if (entry.getValue() >= 5) {
                suit = entry.getKey();
                break;
            }
        }

        if (null != suit) {
            List<Card> handClone = new LinkedList<>(hand);
            List<Card> cardsToRemove = new LinkedList<>();
            for (Card card : handClone) {
                if (!card.getSuit().equals(suit)) {
                    cardsToRemove.add(card);
                }
            }
            handClone.removeAll(cardsToRemove);

            Collections.sort(handClone, (c1, c2) -> c1.getValue().getCardValue() > c2.getValue().getCardValue() ? -1 : (c1.getValue().getCardValue() < c2.getValue().getCardValue() ? 1 : 0));

            Integer[] values = new Integer[handClone.size()];
            int i = 0;
            for (Card card : handClone) {
                values[i++] = card.getValue().getCardValue();
            }

            if (values.length == 7) {
                if (values[6] == values[5] - 1 && values[5] == values[4] - 1 && values[4] == values[3] - 1 && values[3] == values[2] - 1) {
                    return true;
                }
                else if (values[5] == values[4] - 1 && values[4] == values[3] - 1 && values[3] == values[2] - 1 && values[2] == values[1] - 1) {
                    return true;
                }
                else if (values[4] == values[3] - 1 && values[3] == values[2] - 1 && values[2] == values[1] - 1 && values[1] == values[0] - 1) {
                    return true;
                }
                else {
                    List<Integer> valueList = Arrays.asList(values);
                    if (valueList.containsAll(Arrays.asList(14, 2, 3, 4, 5))) {
                        return true;
                    }
                }
            }
            else if (values.length == 6) {
                if (values[5] == values[4] - 1 && values[4] == values[3] - 1 && values[3] == values[2] - 1 && values[2] == values[1] - 1) {
                    return true;
                }
                else if (values[4] == values[3] - 1 && values[3] == values[2] - 1 && values[2] == values[1] - 1 && values[1] == values[0] - 1) {
                    return true;
                }
                else {
                    List<Integer> valueList = Arrays.asList(values);
                    if (valueList.containsAll(Arrays.asList(14, 2, 3, 4, 5))) {
                        return true;
                    }
                }
            }
            else if (values.length == 5) {
                if (values[4] == values[3] - 1 && values[3] == values[2] - 1 && values[2] == values[1] - 1 && values[1] == values[0] - 1) {
                    return true;
                }
                else {
                    List<Integer> valueList = Arrays.asList(values);
                    if (valueList.containsAll(Arrays.asList(14, 2, 3, 4, 5))) {
                        return true;
                    }
                }

                return false;
            }
        }

        return false;
    }

    private static boolean isFourOfAKind(List<Card> hand) {
        Map<CardValue, Integer> valueCount = createValueCountMap();
        for (Card card : hand) {
            CardValue key = card.getValue();
            int value = valueCount.get(key);
            valueCount.put(key, ++value);
        }
        for (Map.Entry<CardValue, Integer> entry : valueCount.entrySet()) {
            if (entry.getValue() >= 4) {
                return true;
            }
        }
        return false;
    }

    private static boolean isFullHouse(List<Card> hand) {
        Map<CardValue, Integer> valueCount = createValueCountMap();
        for (Card card : hand) {
            CardValue key = card.getValue();
            int value = valueCount.get(key);
            valueCount.put(key, ++value);
        }
        boolean hasThree = false;
        boolean hasPair = false;
        for (Map.Entry<CardValue, Integer> entry : valueCount.entrySet()) {
            if (entry.getValue() >= 3) {
                valueCount.remove(entry.getKey());
                hasThree = true;
                break;
            }
        }
        for (Map.Entry<CardValue, Integer> entry : valueCount.entrySet()) {
            if (entry.getValue() >= 2) {
                hasPair = true;
                break;
            }
        }
        if (hasThree && hasPair) {
            return true;
        }
        return false;
    }

    private static boolean isFlush(List<Card> hand) {
        Map<CardSuit, Integer> suitCount = createSuitCountMap();
        for (Card card : hand) {
            CardSuit key = card.getSuit();
            int value = suitCount.get(key);
            suitCount.put(key, ++value);
        }
        for (Map.Entry<CardSuit, Integer> entry : suitCount.entrySet()) {
            if (entry.getValue() >= 5) {
                return true;
            }
        }
        return false;
    }

    private static boolean isStraight(List<Card> hand) {
        Collections.sort(hand, (c1, c2) -> c1.getValue().getCardValue() > c2.getValue().getCardValue() ? -1 : (c1.getValue().getCardValue() < c2.getValue().getCardValue() ? 1 : 0));

        Integer[] values = new Integer[7];
        int i = 0;
        for (Card card : hand) {
            values[i++] = card.getValue().getCardValue();
        }
        if (values[6] == values[5] - 1 && values[5] == values[4] - 1 && values[4] == values[3] - 1 && values[3] == values[2] - 1) {
            return true;
        }
        else if (values[5] == values[4] - 1 && values[4] == values[3] - 1 && values[3] == values[2] - 1 && values[2] == values[1] - 1) {
            return true;
        }
        else if (values[4] == values[3] - 1 && values[3] == values[2] - 1 && values[2] == values[1] - 1 && values[1] == values[0] - 1) {
            return true;
        }
        else {
            List<Integer> valueList = Arrays.asList(values);
            if (valueList.containsAll(Arrays.asList(14, 2, 3, 4, 5))) {
                return true;
            }
        }

        return false;
    }

    private static boolean isThreeOfAKind(List<Card> hand) {
        Map<CardValue, Integer> valueCount = createValueCountMap();
        for (Card card : hand) {
            CardValue key = card.getValue();
            int value = valueCount.get(key);
            valueCount.put(key, ++value);
        }
        for (Map.Entry<CardValue, Integer> entry : valueCount.entrySet()) {
            if (entry.getValue() >= 3) {
                return true;
            }
        }
        return false;
    }

    private static boolean isTwoPair(List<Card> hand) {
        Map<CardValue, Integer> valueCount = createValueCountMap();
        for (Card card : hand) {
            CardValue key = card.getValue();
            int value = valueCount.get(key);
            valueCount.put(key, ++value);
        }
        int numPairs = 0;
        for (Map.Entry<CardValue, Integer> entry : valueCount.entrySet()) {
            if (entry.getValue() >= 2) {
                numPairs++;
            }
            if (numPairs >= 2) {
                return true;
            }
        }
        return false;
    }

    private static boolean isPair(List<Card> hand) {
        Map<CardValue, Integer> valueCount = createValueCountMap();
        for (Card card : hand) {
            CardValue key = card.getValue();
            int value = valueCount.get(key);
            valueCount.put(key, ++value);
        }
        for (Map.Entry<CardValue, Integer> entry : valueCount.entrySet()) {
            if (entry.getValue() >= 2) {
                return true;
            }
        }
        return false;
    }

    private static Map<CardValue, Integer> createValueCountMap() {
        Map<CardValue, Integer> valueCount = new HashMap<>();

        for (int i = 0; i < 13; i++) {
            CardValue key = CardValue.values()[i];
            valueCount.put(key, 0);
        }

        return valueCount;
    }

    private static Map<CardSuit, Integer> createSuitCountMap() {
        Map<CardSuit, Integer> suitCount = new HashMap<>();

        for (int i = 0; i < 4; i++) {
            CardSuit key = CardSuit.values()[i];
            suitCount.put(key, 0);
        }

        return suitCount;
    }



}