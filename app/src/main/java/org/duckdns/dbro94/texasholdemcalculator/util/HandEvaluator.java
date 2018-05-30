package org.duckdns.dbro94.texasholdemcalculator.util;

import org.apache.commons.lang3.tuple.Pair;
import org.duckdns.dbro94.texasholdemcalculator.domain.Card;
import org.duckdns.dbro94.texasholdemcalculator.domain.CardSuit;
import org.duckdns.dbro94.texasholdemcalculator.domain.CardValue;
import org.duckdns.dbro94.texasholdemcalculator.domain.Rank;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.duckdns.dbro94.texasholdemcalculator.util.Constants.LOSS;
import static org.duckdns.dbro94.texasholdemcalculator.util.Constants.TIE;
import static org.duckdns.dbro94.texasholdemcalculator.util.Constants.WIN;

public class HandEvaluator {

    public static String evaluate(List<List<Card>> hands) {
        List<Card> playerHand = hands.get(0);


        // Compare player hand with cpu hands





        return null;
    }

    private static Pair<Rank, Integer[]> evaluateHand(List<Card> hand) {
        Pair<Boolean, Integer[]> straightFlush = isStraightFlush(hand);
        if (straightFlush.getKey()) {
            return Pair.of(Rank.STRAIGHT_FLUSH, straightFlush.getValue());
        }
        else {
            Pair<Boolean, Integer[]> fourOfAKind = isFourOfAKind(hand);
            if (fourOfAKind.getKey()) {
                return Pair.of(Rank.FOUR_OF_A_KIND, fourOfAKind.getValue());
            }
            else {
                Pair<Boolean, Integer[]> fullHouse = isFullHouse(hand);
                if (fullHouse.getKey()) {
                    return Pair.of(Rank.FULL_HOUSE, fullHouse.getValue());
                }
                else {
                    Pair<Boolean, Integer[]> flush = isFlush(hand);
                    if (fullHouse.getKey()) {
                        return Pair.of(Rank.FLUSH, flush.getValue());
                    }
                    else {
                        Pair<Boolean, Integer[]> straight = isStraight(hand);
                        if (fullHouse.getKey()) {
                            return Pair.of(Rank.STRAIGHT, straight.getValue());
                        }
                        else {
                            Pair<Boolean, Integer[]> threeOfAKind = isThreeOfAKind(hand);
                            if (fullHouse.getKey()) {
                                return Pair.of(Rank.THREE_OF_A_KIND, threeOfAKind.getValue());
                            }
                            else {
                                Pair<Boolean, Integer[]> twoPair = isTwoPair(hand);
                                if (fullHouse.getKey()) {
                                    return Pair.of(Rank.TWO_PAIR, twoPair.getValue());
                                }
                                else {
                                    Pair<Boolean, Integer[]> pair = isPair(hand);
                                    if (fullHouse.getKey()) {
                                        return Pair.of(Rank.PAIR, pair.getValue());
                                    }
                                    else {
                                        Collections.sort(hand, (c1, c2) -> c1.getValue().getCardValue() > c2.getValue().getCardValue() ? -1 : (c1.getValue().getCardValue() < c2.getValue().getCardValue() ? 1 : 0));
                                        Integer[] power = new Integer[5];
                                        power[0] = hand.get(0).getValue().getCardValue();
                                        power[1] = hand.get(1).getValue().getCardValue();
                                        power[2] = hand.get(2).getValue().getCardValue();
                                        power[3] = hand.get(3).getValue().getCardValue();
                                        power[4] = hand.get(4).getValue().getCardValue();
                                        return Pair.of(Rank.HIGH_CARD, power);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static Pair<Boolean, Integer[]> isStraightFlush(List<Card> hand) {
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
                    return Pair.of(true, new Integer[]{values[2]});
                }
                else if (values[5] == values[4] - 1 && values[4] == values[3] - 1 && values[3] == values[2] - 1 && values[2] == values[1] - 1) {
                    return Pair.of(true, new Integer[]{values[1]});
                }
                else if (values[4] == values[3] - 1 && values[3] == values[2] - 1 && values[2] == values[1] - 1 && values[1] == values[0] - 1) {
                    return Pair.of(true, new Integer[]{values[0]});
                }
                else {
                    List<Integer> valueList = Arrays.asList(values);
                    if (valueList.containsAll(Arrays.asList(14, 2, 3, 4, 5))) {
                        return Pair.of(true, new Integer[]{5});
                    }
                }
            }
            else if (values.length == 6) {
                if (values[5] == values[4] - 1 && values[4] == values[3] - 1 && values[3] == values[2] - 1 && values[2] == values[1] - 1) {
                    return Pair.of(true, new Integer[]{values[1]});
                }
                else if (values[4] == values[3] - 1 && values[3] == values[2] - 1 && values[2] == values[1] - 1 && values[1] == values[0] - 1) {
                    return Pair.of(true, new Integer[]{values[0]});
                }
                else {
                    List<Integer> valueList = Arrays.asList(values);
                    if (valueList.containsAll(Arrays.asList(14, 2, 3, 4, 5))) {
                        return Pair.of(true, new Integer[]{5});
                    }
                }
            }
            else if (values.length == 5) {
                if (values[4] == values[3] - 1 && values[3] == values[2] - 1 && values[2] == values[1] - 1 && values[1] == values[0] - 1) {
                    return Pair.of(true, new Integer[]{values[0]});
                }
                else {
                    List<Integer> valueList = Arrays.asList(values);
                    if (valueList.containsAll(Arrays.asList(14, 2, 3, 4, 5))) {
                        return Pair.of(true, new Integer[]{5});
                    }
                }
            }
        }

        return Pair.of(false, null);
    }

    private static Pair<Boolean, Integer[]> isFourOfAKind(List<Card> hand) {
        Map<CardValue, Integer> valueCount = createValueCountMap();
        for (Card card : hand) {
            CardValue key = card.getValue();
            int value = valueCount.get(key);
            valueCount.put(key, ++value);
        }
        for (Map.Entry<CardValue, Integer> entry : valueCount.entrySet()) {
            if (entry.getValue() >= 4) {
                Integer[] power = new Integer[2];
                power[0] = entry.getKey().getCardValue();
                int highest = 0;
                int second = 0;
                for (Card card : hand) {
                    if (card.getValue().getCardValue() > highest) {
                        highest = card.getValue().getCardValue();
                        second = highest;
                    }
                    else if (card.getValue().getCardValue() > second) {
                        second = card.getValue().getCardValue();
                    }
                }
                if (power[0] != highest) {
                    power[1] = highest;
                }
                else {
                    power[1] = second;
                }
                return Pair.of(true, power);
            }
        }
        return Pair.of(false, null);
    }

    private static Pair<Boolean, Integer[]> isFullHouse(List<Card> hand) {
        Map<CardValue, Integer> valueCount = createValueCountMap();
        for (Card card : hand) {
            CardValue key = card.getValue();
            int value = valueCount.get(key);
            valueCount.put(key, ++value);
        }
        boolean hasThree = false;
        boolean hasPair = false;
        Integer[] power = {0, 0};
        for (Map.Entry<CardValue, Integer> entry : valueCount.entrySet()) {
            if (entry.getValue() >= 3) {
                valueCount.remove(entry.getKey());
                hasThree = true;
                power[0] = entry.getKey().getCardValue();
                break;
            }
        }
        for (Map.Entry<CardValue, Integer> entry : valueCount.entrySet()) {
            if (entry.getValue() >= 2) {
                hasPair = true;
                if (power[1] < entry.getKey().getCardValue()) {
                    entry.getKey().getCardValue();
                }
            }
        }
        if (hasThree && hasPair) {
            return Pair.of(true, power);
        }
        return Pair.of(false, null);
    }

    private static Pair<Boolean, Integer[]> isFlush(List<Card> hand) {
        Map<CardSuit, Integer> suitCount = createSuitCountMap();
        for (Card card : hand) {
            CardSuit key = card.getSuit();
            int value = suitCount.get(key);
            suitCount.put(key, ++value);
        }
        for (Map.Entry<CardSuit, Integer> entry : suitCount.entrySet()) {
            if (entry.getValue() >= 5) {
                Collections.sort(hand, (c1, c2) -> c1.getValue().getCardValue() > c2.getValue().getCardValue() ? -1 : (c1.getValue().getCardValue() < c2.getValue().getCardValue() ? 1 : 0));
                Integer[] power = new Integer[5];
                List<Card> handFlush = hand.stream().filter(card -> card.getSuit().equals(entry.getKey())).collect(Collectors.toList());
                power[0] = handFlush.get(0).getValue().getCardValue();
                power[1] = handFlush.get(1).getValue().getCardValue();
                power[2] = handFlush.get(2).getValue().getCardValue();
                power[3] = handFlush.get(3).getValue().getCardValue();
                power[4] = handFlush.get(4).getValue().getCardValue();
                return Pair.of(true, power);
            }
        }
        return Pair.of(false, null);
    }

    private static Pair<Boolean, Integer[]> isStraight(List<Card> hand) {
        Collections.sort(hand, (c1, c2) -> c1.getValue().getCardValue() > c2.getValue().getCardValue() ? -1 : (c1.getValue().getCardValue() < c2.getValue().getCardValue() ? 1 : 0));

        Integer[] values = new Integer[7];
        int i = 0;
        for (Card card : hand) {
            values[i++] = card.getValue().getCardValue();
        }
        if (values[6] == values[5] - 1 && values[5] == values[4] - 1 && values[4] == values[3] - 1 && values[3] == values[2] - 1) {
            return Pair.of(true, new Integer[]{values[2]});
        }
        else if (values[5] == values[4] - 1 && values[4] == values[3] - 1 && values[3] == values[2] - 1 && values[2] == values[1] - 1) {
            return Pair.of(true, new Integer[]{values[1]});
        }
        else if (values[4] == values[3] - 1 && values[3] == values[2] - 1 && values[2] == values[1] - 1 && values[1] == values[0] - 1) {
            return Pair.of(true, new Integer[]{values[0]});
        }
        else {
            List<Integer> valueList = Arrays.asList(values);
            if (valueList.containsAll(Arrays.asList(14, 2, 3, 4, 5))) {
                return Pair.of(true, new Integer[]{5});
            }
        }

        return Pair.of(false, null);
    }

    private static Pair<Boolean, Integer[]> isThreeOfAKind(List<Card> hand) {
        Map<CardValue, Integer> valueCount = createValueCountMap();
        for (Card card : hand) {
            CardValue key = card.getValue();
            int value = valueCount.get(key);
            valueCount.put(key, ++value);
        }
        for (Map.Entry<CardValue, Integer> entry : valueCount.entrySet()) {
            if (entry.getValue() >= 3) {
                Integer[] power = new Integer[3];
                power[0] = entry.getKey().getCardValue();
                int highest = 0;
                int second = 0;
                int third = 0;
                for (Card card : hand) {
                    if (card.getValue().getCardValue() > highest) {
                        third = second;
                        second = highest;
                        highest = card.getValue().getCardValue();
                    }
                    else if (card.getValue().getCardValue() > second) {
                        third = second;
                        second = card.getValue().getCardValue();
                    }
                    else if (card.getValue().getCardValue() > third) {
                        third = card.getValue().getCardValue();
                    }
                }
                if (power[0] != highest) {
                    power[1] = highest;
                    if (power[0] != second) {
                        power[2] = second;
                    }
                    else {
                        power[2] = third;
                    }
                }
                else {
                    power[1] = second;
                    power[2] = third;
                }
                return Pair.of(true, power);
            }
        }
        return Pair.of(false, null);
    }

    private static Pair<Boolean, Integer[]> isTwoPair(List<Card> hand) {
        Map<CardValue, Integer> valueCount = createValueCountMap();
        for (Card card : hand) {
            CardValue key = card.getValue();
            int value = valueCount.get(key);
            valueCount.put(key, ++value);
        }
        int numPairs = 0;
        List<Integer> pairs = new LinkedList<>();
        for (Map.Entry<CardValue, Integer> entry : valueCount.entrySet()) {
            if (entry.getValue() >= 2) {
                pairs.add(entry.getKey().getCardValue());
                numPairs++;
            }
        }
        if (numPairs >= 2) {
            Collections.sort(pairs, (c1, c2) -> c1 > c2 ? -1 : (c1 < c2 ? 1 : 0));
            Integer[] power = new Integer[3];
            power[0] = pairs.get(0);
            power[1] = pairs.get(1);
            int highest = 0;
            for (Card card : hand) {
                if (card.getValue().getCardValue() > highest && card.getValue().getCardValue() != power[0] && card.getValue().getCardValue() != power[1]) {
                    highest = card.getValue().getCardValue();
                }
            }
            power[2] = highest;
            return Pair.of(true, power);
        }
        return Pair.of(false, null);
    }

    private static Pair<Boolean, Integer[]> isPair(List<Card> hand) {
        Map<CardValue, Integer> valueCount = createValueCountMap();
        for (Card card : hand) {
            CardValue key = card.getValue();
            int value = valueCount.get(key);
            valueCount.put(key, ++value);
        }
        for (Map.Entry<CardValue, Integer> entry : valueCount.entrySet()) {
            List<Integer> pairs = new LinkedList<>();
            if (entry.getValue() >= 2) {
                pairs.add(entry.getKey().getCardValue());
            }
            if (pairs.size() > 0) {
                Collections.sort(pairs, (c1, c2) -> c1 > c2 ? -1 : (c1 < c2 ? 1 : 0));
                Integer[] power = new Integer[4];
                power[0] = pairs.get(0);
                int highest = 0;
                int second = 0;
                int third = 0;
                for (Card card : hand) {
                    if (card.getValue().getCardValue() > highest && card.getValue().getCardValue() != power[0]) {
                        third = second;
                        second = highest;
                        highest = card.getValue().getCardValue();
                    }
                    else if (card.getValue().getCardValue() > second && card.getValue().getCardValue() != power[0]) {
                        third = second;
                        second = card.getValue().getCardValue();
                    }
                    else if (card.getValue().getCardValue() > third && card.getValue().getCardValue() != power[0]) {
                        third = card.getValue().getCardValue();
                    }
                }
                power[1] = highest;
                power[2] = second;
                power[3] = third;
                return Pair.of(true, power);
            }
        }
        return Pair.of(false, null);
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