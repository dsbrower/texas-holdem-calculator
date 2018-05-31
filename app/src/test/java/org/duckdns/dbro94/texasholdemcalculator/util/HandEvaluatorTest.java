package org.duckdns.dbro94.texasholdemcalculator.util;

import static org.junit.Assert.*;

import org.apache.commons.lang3.tuple.Pair;
import org.duckdns.dbro94.texasholdemcalculator.domain.Card;
import org.duckdns.dbro94.texasholdemcalculator.domain.Rank;
import org.junit.Test;
import org.powermock.reflect.internal.WhiteboxImpl;

import java.util.LinkedList;
import java.util.List;

public class HandEvaluatorTest {

    @Test
    public void testEvaluateStraighFlush() throws Exception {
        List<Card> hand = new LinkedList<>();
        hand.add(CardMapper.getCard("AH"));
        hand.add(CardMapper.getCard("7C"));
        hand.add(CardMapper.getCard("AD"));
        hand.add(CardMapper.getCard("KH"));
        hand.add(CardMapper.getCard("JH"));
        hand.add(CardMapper.getCard("10H"));
        hand.add(CardMapper.getCard("QH"));

        Pair<Rank, Integer[]> outcome = WhiteboxImpl.invokeMethod(HandEvaluator.class, "evaluateHand", hand);
        assertEquals("Unexpected outcome", Rank.STRAIGHT_FLUSH, outcome.getKey());
        assertEquals("Unexpected outcome", 1, outcome.getValue().length);
        assertEquals("Unexpected outcome", Integer.valueOf(14), outcome.getValue()[0]);
    }

    @Test
    public void testEvaluateStraighFlushAceLow() throws Exception {
        List<Card> hand = new LinkedList<>();
        hand.add(CardMapper.getCard("AH"));
        hand.add(CardMapper.getCard("7C"));
        hand.add(CardMapper.getCard("AD"));
        hand.add(CardMapper.getCard("5H"));
        hand.add(CardMapper.getCard("3H"));
        hand.add(CardMapper.getCard("2H"));
        hand.add(CardMapper.getCard("4H"));

        Pair<Rank, Integer[]> outcome = WhiteboxImpl.invokeMethod(HandEvaluator.class, "evaluateHand", hand);
        assertEquals("Unexpected outcome", Rank.STRAIGHT_FLUSH, outcome.getKey());
        assertEquals("Unexpected outcome", 1, outcome.getValue().length);
        assertEquals("Unexpected outcome", Integer.valueOf(5), outcome.getValue()[0]);
    }

    @Test
    public void testEvaluateFourOfAKind() throws Exception {
        List<Card> hand = new LinkedList<>();
        hand.add(CardMapper.getCard("AH"));
        hand.add(CardMapper.getCard("7C"));
        hand.add(CardMapper.getCard("AD"));
        hand.add(CardMapper.getCard("5H"));
        hand.add(CardMapper.getCard("AC"));
        hand.add(CardMapper.getCard("AS"));
        hand.add(CardMapper.getCard("4H"));

        Pair<Rank, Integer[]> outcome = WhiteboxImpl.invokeMethod(HandEvaluator.class, "evaluateHand", hand);
        assertEquals("Unexpected outcome", Rank.FOUR_OF_A_KIND, outcome.getKey());
        assertEquals("Unexpected outcome", 2, outcome.getValue().length);
        assertEquals("Unexpected outcome", Integer.valueOf(14), outcome.getValue()[0]);
        assertEquals("Unexpected outcome", Integer.valueOf(7), outcome.getValue()[1]);
    }

    @Test
    public void testEvaluateFullHouse() throws Exception {
        List<Card> hand = new LinkedList<>();
        hand.add(CardMapper.getCard("AH"));
        hand.add(CardMapper.getCard("7C"));
        hand.add(CardMapper.getCard("AD"));
        hand.add(CardMapper.getCard("8H"));
        hand.add(CardMapper.getCard("8C"));
        hand.add(CardMapper.getCard("8S"));
        hand.add(CardMapper.getCard("4H"));

        Pair<Rank, Integer[]> outcome = WhiteboxImpl.invokeMethod(HandEvaluator.class, "evaluateHand", hand);
        assertEquals("Unexpected outcome", Rank.FULL_HOUSE, outcome.getKey());
        assertEquals("Unexpected outcome", 2, outcome.getValue().length);
        assertEquals("Unexpected outcome", Integer.valueOf(8), outcome.getValue()[0]);
        assertEquals("Unexpected outcome", Integer.valueOf(14), outcome.getValue()[1]);
    }

    @Test
    public void testEvaluateFlush() throws Exception {
        List<Card> hand = new LinkedList<>();
        hand.add(CardMapper.getCard("6H"));
        hand.add(CardMapper.getCard("AH"));
        hand.add(CardMapper.getCard("AD"));
        hand.add(CardMapper.getCard("5H"));
        hand.add(CardMapper.getCard("2H"));
        hand.add(CardMapper.getCard("4S"));
        hand.add(CardMapper.getCard("4H"));

        Pair<Rank, Integer[]> outcome = WhiteboxImpl.invokeMethod(HandEvaluator.class, "evaluateHand", hand);
        assertEquals("Unexpected outcome", Rank.FLUSH, outcome.getKey());
        assertEquals("Unexpected outcome", 5, outcome.getValue().length);
        assertEquals("Unexpected outcome", Integer.valueOf(14), outcome.getValue()[0]);
        assertEquals("Unexpected outcome", Integer.valueOf(6), outcome.getValue()[1]);
        assertEquals("Unexpected outcome", Integer.valueOf(5), outcome.getValue()[2]);
        assertEquals("Unexpected outcome", Integer.valueOf(4), outcome.getValue()[3]);
        assertEquals("Unexpected outcome", Integer.valueOf(2), outcome.getValue()[4]);
    }

    @Test
    public void testEvaluateStraight() throws Exception {
        List<Card> hand = new LinkedList<>();
        hand.add(CardMapper.getCard("AS"));
        hand.add(CardMapper.getCard("KC"));
        hand.add(CardMapper.getCard("2D"));
        hand.add(CardMapper.getCard("10H"));
        hand.add(CardMapper.getCard("7C"));
        hand.add(CardMapper.getCard("QD"));
        hand.add(CardMapper.getCard("JS"));

        Pair<Rank, Integer[]> outcome = WhiteboxImpl.invokeMethod(HandEvaluator.class, "evaluateHand", hand);
        assertEquals("Unexpected outcome", Rank.STRAIGHT, outcome.getKey());
        assertEquals("Unexpected outcome", 1, outcome.getValue().length);
        assertEquals("Unexpected outcome", Integer.valueOf(14), outcome.getValue()[0]);
    }

    @Test
    public void testEvaluateStraightAceLow() throws Exception {
        List<Card> hand = new LinkedList<>();
        hand.add(CardMapper.getCard("AS"));
        hand.add(CardMapper.getCard("2C"));
        hand.add(CardMapper.getCard("2D"));
        hand.add(CardMapper.getCard("5H"));
        hand.add(CardMapper.getCard("7C"));
        hand.add(CardMapper.getCard("3D"));
        hand.add(CardMapper.getCard("4S"));

        Pair<Rank, Integer[]> outcome = WhiteboxImpl.invokeMethod(HandEvaluator.class, "evaluateHand", hand);
        assertEquals("Unexpected outcome", Rank.STRAIGHT, outcome.getKey());
        assertEquals("Unexpected outcome", 1, outcome.getValue().length);
        assertEquals("Unexpected outcome", Integer.valueOf(5), outcome.getValue()[0]);
    }

    @Test
    public void testEvaluateThreeOfAKind() throws Exception {
        List<Card> hand = new LinkedList<>();
        hand.add(CardMapper.getCard("AS"));
        hand.add(CardMapper.getCard("2C"));
        hand.add(CardMapper.getCard("2D"));
        hand.add(CardMapper.getCard("5H"));
        hand.add(CardMapper.getCard("7C"));
        hand.add(CardMapper.getCard("2C"));
        hand.add(CardMapper.getCard("4S"));

        Pair<Rank, Integer[]> outcome = WhiteboxImpl.invokeMethod(HandEvaluator.class, "evaluateHand", hand);
        assertEquals("Unexpected outcome", Rank.THREE_OF_A_KIND, outcome.getKey());
        assertEquals("Unexpected outcome", 3, outcome.getValue().length);
        assertEquals("Unexpected outcome", Integer.valueOf(2), outcome.getValue()[0]);
        assertEquals("Unexpected outcome", Integer.valueOf(14), outcome.getValue()[1]);
        assertEquals("Unexpected outcome", Integer.valueOf(7), outcome.getValue()[2]);
    }

    @Test
    public void testEvaluateTwoPair() throws Exception {
        List<Card> hand = new LinkedList<>();
        hand.add(CardMapper.getCard("AS"));
        hand.add(CardMapper.getCard("2C"));
        hand.add(CardMapper.getCard("2D"));
        hand.add(CardMapper.getCard("5H"));
        hand.add(CardMapper.getCard("7C"));
        hand.add(CardMapper.getCard("AC"));
        hand.add(CardMapper.getCard("4S"));

        Pair<Rank, Integer[]> outcome = WhiteboxImpl.invokeMethod(HandEvaluator.class, "evaluateHand", hand);
        assertEquals("Unexpected outcome", Rank.TWO_PAIR, outcome.getKey());
        assertEquals("Unexpected outcome", 3, outcome.getValue().length);
        assertEquals("Unexpected outcome", Integer.valueOf(14), outcome.getValue()[0]);
        assertEquals("Unexpected outcome", Integer.valueOf(2), outcome.getValue()[1]);
        assertEquals("Unexpected outcome", Integer.valueOf(7), outcome.getValue()[2]);
    }

    @Test
    public void testEvaluatePair() throws Exception {
        List<Card> hand = new LinkedList<>();
        hand.add(CardMapper.getCard("AS"));
        hand.add(CardMapper.getCard("2C"));
        hand.add(CardMapper.getCard("2D"));
        hand.add(CardMapper.getCard("5H"));
        hand.add(CardMapper.getCard("7C"));
        hand.add(CardMapper.getCard("8C"));
        hand.add(CardMapper.getCard("4S"));

        Pair<Rank, Integer[]> outcome = WhiteboxImpl.invokeMethod(HandEvaluator.class, "evaluateHand", hand);
        assertEquals("Unexpected outcome", Rank.PAIR, outcome.getKey());
        assertEquals("Unexpected outcome", 4, outcome.getValue().length);
        assertEquals("Unexpected outcome", Integer.valueOf(2), outcome.getValue()[0]);
        assertEquals("Unexpected outcome", Integer.valueOf(14), outcome.getValue()[1]);
        assertEquals("Unexpected outcome", Integer.valueOf(8), outcome.getValue()[2]);
        assertEquals("Unexpected outcome", Integer.valueOf(7), outcome.getValue()[3]);
    }

    @Test
    public void testEvaluateHighCard() throws Exception {
        List<Card> hand = new LinkedList<>();
        hand.add(CardMapper.getCard("AS"));
        hand.add(CardMapper.getCard("2C"));
        hand.add(CardMapper.getCard("9D"));
        hand.add(CardMapper.getCard("5H"));
        hand.add(CardMapper.getCard("7C"));
        hand.add(CardMapper.getCard("8C"));
        hand.add(CardMapper.getCard("4S"));

        Pair<Rank, Integer[]> outcome = WhiteboxImpl.invokeMethod(HandEvaluator.class, "evaluateHand", hand);
        assertEquals("Unexpected outcome", Rank.HIGH_CARD, outcome.getKey());
        assertEquals("Unexpected outcome", 5, outcome.getValue().length);
        assertEquals("Unexpected outcome", Integer.valueOf(14), outcome.getValue()[0]);
        assertEquals("Unexpected outcome", Integer.valueOf(9), outcome.getValue()[1]);
        assertEquals("Unexpected outcome", Integer.valueOf(8), outcome.getValue()[2]);
        assertEquals("Unexpected outcome", Integer.valueOf(7), outcome.getValue()[3]);
        assertEquals("Unexpected outcome", Integer.valueOf(5), outcome.getValue()[4]);
    }

    @Test
    public void testIsStraighFlush() throws Exception {
        List<Card> hand = new LinkedList<>();
        hand.add(CardMapper.getCard("AH"));
        hand.add(CardMapper.getCard("7C"));
        hand.add(CardMapper.getCard("AD"));
        hand.add(CardMapper.getCard("KH"));
        hand.add(CardMapper.getCard("JH"));
        hand.add(CardMapper.getCard("10H"));
        hand.add(CardMapper.getCard("QH"));

        Pair<Boolean, Integer[]> outcome = WhiteboxImpl.invokeMethod(HandEvaluator.class, "isStraightFlush", hand);
        assertEquals("Unexpected outcome", true, outcome.getKey());
        assertEquals("Unexpected outcome", 1, outcome.getValue().length);
        assertEquals("Unexpected outcome", Integer.valueOf(14), outcome.getValue()[0]);
    }

    @Test
    public void testIsStraighFlushAceLow() throws Exception {
        List<Card> hand = new LinkedList<>();
        hand.add(CardMapper.getCard("AH"));
        hand.add(CardMapper.getCard("7C"));
        hand.add(CardMapper.getCard("AD"));
        hand.add(CardMapper.getCard("5H"));
        hand.add(CardMapper.getCard("3H"));
        hand.add(CardMapper.getCard("2H"));
        hand.add(CardMapper.getCard("4H"));

        Pair<Boolean, Integer[]> outcome = WhiteboxImpl.invokeMethod(HandEvaluator.class, "isStraightFlush", hand);
        assertEquals("Unexpected outcome", true, outcome.getKey());
        assertEquals("Unexpected outcome", 1, outcome.getValue().length);
        assertEquals("Unexpected outcome", Integer.valueOf(5), outcome.getValue()[0]);
    }

    @Test
    public void testIsFourOfAKind() throws Exception {
        List<Card> hand = new LinkedList<>();
        hand.add(CardMapper.getCard("AH"));
        hand.add(CardMapper.getCard("7C"));
        hand.add(CardMapper.getCard("AD"));
        hand.add(CardMapper.getCard("5H"));
        hand.add(CardMapper.getCard("AC"));
        hand.add(CardMapper.getCard("AS"));
        hand.add(CardMapper.getCard("4H"));

        Pair<Boolean, Integer[]> outcome = WhiteboxImpl.invokeMethod(HandEvaluator.class, "isFourOfAKind", hand);
        assertEquals("Unexpected outcome", true, outcome.getKey());
        assertEquals("Unexpected outcome", 2, outcome.getValue().length);
        assertEquals("Unexpected outcome", Integer.valueOf(14), outcome.getValue()[0]);
        assertEquals("Unexpected outcome", Integer.valueOf(7), outcome.getValue()[1]);
    }

    @Test
    public void testIsFullHouse() throws Exception {
        List<Card> hand = new LinkedList<>();
        hand.add(CardMapper.getCard("AH"));
        hand.add(CardMapper.getCard("7C"));
        hand.add(CardMapper.getCard("AD"));
        hand.add(CardMapper.getCard("8H"));
        hand.add(CardMapper.getCard("8C"));
        hand.add(CardMapper.getCard("8S"));
        hand.add(CardMapper.getCard("4H"));

        Pair<Boolean, Integer[]> outcome = WhiteboxImpl.invokeMethod(HandEvaluator.class, "isFullHouse", hand);
        assertEquals("Unexpected outcome", true, outcome.getKey());
        assertEquals("Unexpected outcome", 2, outcome.getValue().length);
        assertEquals("Unexpected outcome", Integer.valueOf(8), outcome.getValue()[0]);
        assertEquals("Unexpected outcome", Integer.valueOf(14), outcome.getValue()[1]);
    }

    @Test
    public void testIsFlush() throws Exception {
        List<Card> hand = new LinkedList<>();
        hand.add(CardMapper.getCard("6H"));
        hand.add(CardMapper.getCard("AH"));
        hand.add(CardMapper.getCard("AD"));
        hand.add(CardMapper.getCard("5H"));
        hand.add(CardMapper.getCard("2H"));
        hand.add(CardMapper.getCard("4S"));
        hand.add(CardMapper.getCard("4H"));

        Pair<Boolean, Integer[]> outcome = WhiteboxImpl.invokeMethod(HandEvaluator.class, "isFlush", hand);
        assertEquals("Unexpected outcome", true, outcome.getKey());
        assertEquals("Unexpected outcome", 5, outcome.getValue().length);
        assertEquals("Unexpected outcome", Integer.valueOf(14), outcome.getValue()[0]);
        assertEquals("Unexpected outcome", Integer.valueOf(6), outcome.getValue()[1]);
        assertEquals("Unexpected outcome", Integer.valueOf(5), outcome.getValue()[2]);
        assertEquals("Unexpected outcome", Integer.valueOf(4), outcome.getValue()[3]);
        assertEquals("Unexpected outcome", Integer.valueOf(2), outcome.getValue()[4]);
    }

    @Test
    public void testIsStraight() throws Exception {
        List<Card> hand = new LinkedList<>();
        hand.add(CardMapper.getCard("AS"));
        hand.add(CardMapper.getCard("KC"));
        hand.add(CardMapper.getCard("2D"));
        hand.add(CardMapper.getCard("10H"));
        hand.add(CardMapper.getCard("7C"));
        hand.add(CardMapper.getCard("QD"));
        hand.add(CardMapper.getCard("JS"));

        Pair<Boolean, Integer[]> outcome = WhiteboxImpl.invokeMethod(HandEvaluator.class, "isStraight", hand);
        assertEquals("Unexpected outcome", true, outcome.getKey());
        assertEquals("Unexpected outcome", 1, outcome.getValue().length);
        assertEquals("Unexpected outcome", Integer.valueOf(14), outcome.getValue()[0]);
    }

    @Test
    public void testIsStraightAceLow() throws Exception {
        List<Card> hand = new LinkedList<>();
        hand.add(CardMapper.getCard("AS"));
        hand.add(CardMapper.getCard("2C"));
        hand.add(CardMapper.getCard("2D"));
        hand.add(CardMapper.getCard("5H"));
        hand.add(CardMapper.getCard("7C"));
        hand.add(CardMapper.getCard("3D"));
        hand.add(CardMapper.getCard("4S"));

        Pair<Boolean, Integer[]> outcome = WhiteboxImpl.invokeMethod(HandEvaluator.class, "isStraight", hand);
        assertEquals("Unexpected outcome", true, outcome.getKey());
        assertEquals("Unexpected outcome", 1, outcome.getValue().length);
        assertEquals("Unexpected outcome", Integer.valueOf(5), outcome.getValue()[0]);
    }

    @Test
    public void testIsThreeOfAKind() throws Exception {
        List<Card> hand = new LinkedList<>();
        hand.add(CardMapper.getCard("AS"));
        hand.add(CardMapper.getCard("2C"));
        hand.add(CardMapper.getCard("2D"));
        hand.add(CardMapper.getCard("5H"));
        hand.add(CardMapper.getCard("7C"));
        hand.add(CardMapper.getCard("2C"));
        hand.add(CardMapper.getCard("4S"));

        Pair<Boolean, Integer[]> outcome = WhiteboxImpl.invokeMethod(HandEvaluator.class, "isThreeOfAKind", hand);
        assertEquals("Unexpected outcome", true, outcome.getKey());
        assertEquals("Unexpected outcome", 3, outcome.getValue().length);
        assertEquals("Unexpected outcome", Integer.valueOf(2), outcome.getValue()[0]);
        assertEquals("Unexpected outcome", Integer.valueOf(14), outcome.getValue()[1]);
        assertEquals("Unexpected outcome", Integer.valueOf(7), outcome.getValue()[2]);
    }

    @Test
    public void testIsTwoPair() throws Exception {
        List<Card> hand = new LinkedList<>();
        hand.add(CardMapper.getCard("AS"));
        hand.add(CardMapper.getCard("2C"));
        hand.add(CardMapper.getCard("2D"));
        hand.add(CardMapper.getCard("5H"));
        hand.add(CardMapper.getCard("7C"));
        hand.add(CardMapper.getCard("AC"));
        hand.add(CardMapper.getCard("4S"));

        Pair<Boolean, Integer[]> outcome = WhiteboxImpl.invokeMethod(HandEvaluator.class, "isTwoPair", hand);
        assertEquals("Unexpected outcome", true, outcome.getKey());
        assertEquals("Unexpected outcome", 3, outcome.getValue().length);
        assertEquals("Unexpected outcome", Integer.valueOf(14), outcome.getValue()[0]);
        assertEquals("Unexpected outcome", Integer.valueOf(2), outcome.getValue()[1]);
        assertEquals("Unexpected outcome", Integer.valueOf(7), outcome.getValue()[2]);
    }

    @Test
    public void testIsPair() throws Exception {
        List<Card> hand = new LinkedList<>();
        hand.add(CardMapper.getCard("AS"));
        hand.add(CardMapper.getCard("2C"));
        hand.add(CardMapper.getCard("2D"));
        hand.add(CardMapper.getCard("5H"));
        hand.add(CardMapper.getCard("7C"));
        hand.add(CardMapper.getCard("8C"));
        hand.add(CardMapper.getCard("4S"));

        Pair<Boolean, Integer[]> outcome = WhiteboxImpl.invokeMethod(HandEvaluator.class, "isPair", hand);
        assertEquals("Unexpected outcome", true, outcome.getKey());
        assertEquals("Unexpected outcome", 4, outcome.getValue().length);
        assertEquals("Unexpected outcome", Integer.valueOf(2), outcome.getValue()[0]);
        assertEquals("Unexpected outcome", Integer.valueOf(14), outcome.getValue()[1]);
        assertEquals("Unexpected outcome", Integer.valueOf(8), outcome.getValue()[2]);
        assertEquals("Unexpected outcome", Integer.valueOf(7), outcome.getValue()[3]);
    }

}