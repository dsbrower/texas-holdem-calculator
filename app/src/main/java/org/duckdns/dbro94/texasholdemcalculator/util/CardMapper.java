package org.duckdns.dbro94.texasholdemcalculator.util;

import org.duckdns.dbro94.texasholdemcalculator.domain.Card;
import org.duckdns.dbro94.texasholdemcalculator.domain.CardSuit;
import org.duckdns.dbro94.texasholdemcalculator.domain.CardValue;

public class CardMapper {

    public static Card getCard(String c) {
        Card card = null;
        switch (c) {
            case "AS":
                card = new Card(CardValue.ACE, CardSuit.SPADE);
                break;
            case "2S":
                card = new Card(CardValue.TWO, CardSuit.SPADE);
                break;
            case "3S":
                card = new Card(CardValue.THREE, CardSuit.SPADE);
                break;
            case "4S":
                card = new Card(CardValue.FOUR, CardSuit.SPADE);
                break;
            case "5S":
                card = new Card(CardValue.FIVE, CardSuit.SPADE);
                break;
            case "6S":
                card = new Card(CardValue.SIX, CardSuit.SPADE);
                break;
            case "7S":
                card = new Card(CardValue.SEVEN, CardSuit.SPADE);
                break;
            case "8S":
                card = new Card(CardValue.EIGHT, CardSuit.SPADE);
                break;
            case "9S":
                card = new Card(CardValue.NINE, CardSuit.SPADE);
                break;
            case "10S":
                card = new Card(CardValue.TEN, CardSuit.SPADE);
                break;
            case "JS":
                card = new Card(CardValue.JACK, CardSuit.SPADE);
                break;
            case "QS":
                card = new Card(CardValue.QUEEN, CardSuit.SPADE);
                break;
            case "KS":
                card = new Card(CardValue.KING, CardSuit.SPADE);
                break;
            case "AC":
                card = new Card(CardValue.ACE, CardSuit.CLUB);
                break;
            case "2C":
                card = new Card(CardValue.TWO, CardSuit.CLUB);
                break;
            case "3C":
                card = new Card(CardValue.THREE, CardSuit.CLUB);
                break;
            case "4C":
                card = new Card(CardValue.FOUR, CardSuit.CLUB);
                break;
            case "5C":
                card = new Card(CardValue.FIVE, CardSuit.CLUB);
                break;
            case "6C":
                card = new Card(CardValue.SIX, CardSuit.CLUB);
                break;
            case "7C":
                card = new Card(CardValue.SEVEN, CardSuit.CLUB);
                break;
            case "8C":
                card = new Card(CardValue.EIGHT, CardSuit.CLUB);
                break;
            case "9C":
                card = new Card(CardValue.NINE, CardSuit.CLUB);
                break;
            case "10C":
                card = new Card(CardValue.TEN, CardSuit.CLUB);
                break;
            case "JC":
                card = new Card(CardValue.JACK, CardSuit.CLUB);
                break;
            case "QC":
                card = new Card(CardValue.QUEEN, CardSuit.CLUB);
                break;
            case "KC":
                card = new Card(CardValue.KING, CardSuit.CLUB);
                break;
            case "AH":
                card = new Card(CardValue.ACE, CardSuit.HEART);
                break;
            case "2H":
                card = new Card(CardValue.TWO, CardSuit.HEART);
                break;
            case "3H":
                card = new Card(CardValue.THREE, CardSuit.HEART);
                break;
            case "4H":
                card = new Card(CardValue.FOUR, CardSuit.HEART);
                break;
            case "5H":
                card = new Card(CardValue.FIVE, CardSuit.HEART);
                break;
            case "6H":
                card = new Card(CardValue.SIX, CardSuit.HEART);
                break;
            case "7H":
                card = new Card(CardValue.SEVEN, CardSuit.HEART);
                break;
            case "8H":
                card = new Card(CardValue.EIGHT, CardSuit.HEART);
                break;
            case "9H":
                card = new Card(CardValue.NINE, CardSuit.HEART);
                break;
            case "10H":
                card = new Card(CardValue.TEN, CardSuit.HEART);
                break;
            case "JH":
                card = new Card(CardValue.JACK, CardSuit.HEART);
                break;
            case "QH":
                card = new Card(CardValue.QUEEN, CardSuit.HEART);
                break;
            case "KH":
                card = new Card(CardValue.KING, CardSuit.HEART);
                break;
            case "AD":
                card = new Card(CardValue.ACE, CardSuit.DIAMOND);
                break;
            case "2D":
                card = new Card(CardValue.TWO, CardSuit.DIAMOND);
                break;
            case "3D":
                card = new Card(CardValue.THREE, CardSuit.DIAMOND);
                break;
            case "4D":
                card = new Card(CardValue.FOUR, CardSuit.DIAMOND);
                break;
            case "5D":
                card = new Card(CardValue.FIVE, CardSuit.DIAMOND);
                break;
            case "6D":
                card = new Card(CardValue.SIX, CardSuit.DIAMOND);
                break;
            case "7D":
                card = new Card(CardValue.SEVEN, CardSuit.DIAMOND);
                break;
            case "8D":
                card = new Card(CardValue.EIGHT, CardSuit.DIAMOND);
                break;
            case "9D":
                card = new Card(CardValue.NINE, CardSuit.DIAMOND);
                break;
            case "10D":
                card = new Card(CardValue.TEN, CardSuit.DIAMOND);
                break;
            case "JD":
                card = new Card(CardValue.JACK, CardSuit.DIAMOND);
                break;
            case "QD":
                card = new Card(CardValue.QUEEN, CardSuit.DIAMOND);
                break;
            case "KD":
                card = new Card(CardValue.KING, CardSuit.DIAMOND);
                break;
            default:
                break;
        }
        return card;
    }

}