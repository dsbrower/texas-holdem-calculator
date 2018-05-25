package org.duckdns.dbro94.texasholdemcalculator.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.duckdns.dbro94.texasholdemcalculator.domain.Card;
import org.duckdns.dbro94.texasholdemcalculator.domain.CardSuit;
import org.duckdns.dbro94.texasholdemcalculator.domain.CardValue;
import org.junit.Test;

public class HandEvaluatorTest {

    @Test
    public void testRoyalFlush() {
        List<Card> hand = new ArrayList<>();
        hand.add(new Card(CardValue.ACE, CardSuit.CLUB));
        hand.add(new Card(CardValue.KING, CardSuit.CLUB));
        hand.add(new Card(CardValue.QUEEN, CardSuit.CLUB));
        hand.add(new Card(CardValue.FIVE, CardSuit.HEART));
        hand.add(new Card(CardValue.TWO, CardSuit.SPADE));
        hand.add(new Card(CardValue.JACK, CardSuit.CLUB));
        hand.add(new Card(CardValue.TEN, CardSuit.CLUB));

        assertEquals("Wrong rank", Rank.STRAIGHT_FLUSH, HandEvaluator.evaluate(hand));
    }

    @Test
    public void testStraightFlush() {
        List<Card> hand = new ArrayList<>();
        hand.add(new Card(CardValue.NINE, CardSuit.CLUB));
        hand.add(new Card(CardValue.KING, CardSuit.CLUB));
        hand.add(new Card(CardValue.QUEEN, CardSuit.CLUB));
        hand.add(new Card(CardValue.FIVE, CardSuit.HEART));
        hand.add(new Card(CardValue.TWO, CardSuit.SPADE));
        hand.add(new Card(CardValue.JACK, CardSuit.CLUB));
        hand.add(new Card(CardValue.TEN, CardSuit.CLUB));

        assertEquals("Wrong rank", Rank.STRAIGHT_FLUSH, HandEvaluator.evaluate(hand));
    }

    @Test
    public void testStraightFlushAceLow() {
        List<Card> hand = new ArrayList<>();
        hand.add(new Card(CardValue.ACE, CardSuit.CLUB));
        hand.add(new Card(CardValue.THREE, CardSuit.CLUB));
        hand.add(new Card(CardValue.FOUR, CardSuit.CLUB));
        hand.add(new Card(CardValue.FIVE, CardSuit.CLUB));
        hand.add(new Card(CardValue.TWO, CardSuit.CLUB));
        hand.add(new Card(CardValue.JACK, CardSuit.HEART));
        hand.add(new Card(CardValue.TEN, CardSuit.SPADE));

        assertEquals("Wrong rank", Rank.STRAIGHT_FLUSH, HandEvaluator.evaluate(hand));
    }

    @Test
    public void testStraightFlush7Club() {
        List<Card> hand = new ArrayList<>();
        hand.add(new Card(CardValue.ACE, CardSuit.CLUB));
        hand.add(new Card(CardValue.THREE, CardSuit.CLUB));
        hand.add(new Card(CardValue.FOUR, CardSuit.CLUB));
        hand.add(new Card(CardValue.FIVE, CardSuit.CLUB));
        hand.add(new Card(CardValue.TWO, CardSuit.CLUB));
        hand.add(new Card(CardValue.JACK, CardSuit.CLUB));
        hand.add(new Card(CardValue.TEN, CardSuit.CLUB));

        assertEquals("Wrong rank", Rank.STRAIGHT_FLUSH, HandEvaluator.evaluate(hand));
    }

    @Test
    public void testStraightFlush6Club() {
        List<Card> hand = new ArrayList<>();
        hand.add(new Card(CardValue.ACE, CardSuit.CLUB));
        hand.add(new Card(CardValue.THREE, CardSuit.CLUB));
        hand.add(new Card(CardValue.FOUR, CardSuit.CLUB));
        hand.add(new Card(CardValue.FIVE, CardSuit.CLUB));
        hand.add(new Card(CardValue.TWO, CardSuit.CLUB));
        hand.add(new Card(CardValue.JACK, CardSuit.CLUB));
        hand.add(new Card(CardValue.TEN, CardSuit.SPADE));

        assertEquals("Wrong rank", Rank.STRAIGHT_FLUSH, HandEvaluator.evaluate(hand));
    }

    @Test
    public void testFourOfAKind() {
        List<Card> hand = new ArrayList<>();
        hand.add(new Card(CardValue.ACE, CardSuit.CLUB));
        hand.add(new Card(CardValue.ACE, CardSuit.DIAMOND));
        hand.add(new Card(CardValue.ACE, CardSuit.HEART));
        hand.add(new Card(CardValue.ACE, CardSuit.SPADE));
        hand.add(new Card(CardValue.TWO, CardSuit.SPADE));
        hand.add(new Card(CardValue.FIVE, CardSuit.CLUB));
        hand.add(new Card(CardValue.NINE, CardSuit.HEART));

        assertEquals("Wrong rank", Rank.FOUR_OF_A_KIND, HandEvaluator.evaluate(hand));
    }

    @Test
    public void testFullHOuse() {
        List<Card> hand = new ArrayList<>();
        hand.add(new Card(CardValue.ACE, CardSuit.CLUB));
        hand.add(new Card(CardValue.ACE, CardSuit.DIAMOND));
        hand.add(new Card(CardValue.ACE, CardSuit.HEART));
        hand.add(new Card(CardValue.FIVE, CardSuit.SPADE));
        hand.add(new Card(CardValue.TWO, CardSuit.SPADE));
        hand.add(new Card(CardValue.FIVE, CardSuit.CLUB));
        hand.add(new Card(CardValue.NINE, CardSuit.HEART));

        assertEquals("Wrong rank", Rank.FULL_HOUSE, HandEvaluator.evaluate(hand));
    }

    @Test
    public void testFlush() {
        List<Card> hand = new ArrayList<>();
        hand.add(new Card(CardValue.ACE, CardSuit.CLUB));
        hand.add(new Card(CardValue.SEVEN, CardSuit.CLUB));
        hand.add(new Card(CardValue.SIX, CardSuit.HEART));
        hand.add(new Card(CardValue.FIVE, CardSuit.CLUB));
        hand.add(new Card(CardValue.TWO, CardSuit.SPADE));
        hand.add(new Card(CardValue.JACK, CardSuit.CLUB));
        hand.add(new Card(CardValue.NINE, CardSuit.CLUB));

        assertEquals("Wrong rank", Rank.FLUSH, HandEvaluator.evaluate(hand));
    }

    @Test
    public void testStraightAceHigh() {
        List<Card> hand = new ArrayList<>();
        hand.add(new Card(CardValue.ACE, CardSuit.CLUB));
        hand.add(new Card(CardValue.KING, CardSuit.CLUB));
        hand.add(new Card(CardValue.QUEEN, CardSuit.HEART));
        hand.add(new Card(CardValue.FIVE, CardSuit.SPADE));
        hand.add(new Card(CardValue.TWO, CardSuit.SPADE));
        hand.add(new Card(CardValue.JACK, CardSuit.DIAMOND));
        hand.add(new Card(CardValue.TEN, CardSuit.CLUB));

        assertEquals("Wrong rank", Rank.STRAIGHT, HandEvaluator.evaluate(hand));
    }

    @Test
    public void testStraightAceLow() {
        List<Card> hand = new ArrayList<>();
        hand.add(new Card(CardValue.ACE, CardSuit.CLUB));
        hand.add(new Card(CardValue.KING, CardSuit.CLUB));
        hand.add(new Card(CardValue.FOUR, CardSuit.HEART));
        hand.add(new Card(CardValue.FIVE, CardSuit.SPADE));
        hand.add(new Card(CardValue.TWO, CardSuit.SPADE));
        hand.add(new Card(CardValue.JACK, CardSuit.DIAMOND));
        hand.add(new Card(CardValue.THREE, CardSuit.CLUB));

        assertEquals("Wrong rank", Rank.STRAIGHT, HandEvaluator.evaluate(hand));
    }

    @Test
    public void testThreeOfAKind() {
        List<Card> hand = new ArrayList<>();
        hand.add(new Card(CardValue.ACE, CardSuit.CLUB));
        hand.add(new Card(CardValue.ACE, CardSuit.DIAMOND));
        hand.add(new Card(CardValue.ACE, CardSuit.HEART));
        hand.add(new Card(CardValue.KING, CardSuit.SPADE));
        hand.add(new Card(CardValue.TWO, CardSuit.SPADE));
        hand.add(new Card(CardValue.FIVE, CardSuit.CLUB));
        hand.add(new Card(CardValue.NINE, CardSuit.HEART));

        assertEquals("Wrong rank", Rank.THREE_OF_A_KIND, HandEvaluator.evaluate(hand));
    }

    @Test
    public void testTwoPair() {
        List<Card> hand = new ArrayList<>();
        hand.add(new Card(CardValue.ACE, CardSuit.CLUB));
        hand.add(new Card(CardValue.ACE, CardSuit.DIAMOND));
        hand.add(new Card(CardValue.SEVEN, CardSuit.HEART));
        hand.add(new Card(CardValue.SEVEN, CardSuit.SPADE));
        hand.add(new Card(CardValue.TWO, CardSuit.SPADE));
        hand.add(new Card(CardValue.FIVE, CardSuit.CLUB));
        hand.add(new Card(CardValue.NINE, CardSuit.HEART));

        assertEquals("Wrong rank", Rank.TWO_PAIR, HandEvaluator.evaluate(hand));
    }

    @Test
    public void testPair() {
        List<Card> hand = new ArrayList<>();
        hand.add(new Card(CardValue.ACE, CardSuit.CLUB));
        hand.add(new Card(CardValue.ACE, CardSuit.DIAMOND));
        hand.add(new Card(CardValue.SEVEN, CardSuit.HEART));
        hand.add(new Card(CardValue.KING, CardSuit.SPADE));
        hand.add(new Card(CardValue.TWO, CardSuit.SPADE));
        hand.add(new Card(CardValue.FIVE, CardSuit.CLUB));
        hand.add(new Card(CardValue.NINE, CardSuit.HEART));

        assertEquals("Wrong rank", Rank.PAIR, HandEvaluator.evaluate(hand));
    }

}