package org.duckdns.dbro94.texasholdemcalculator.util;

import org.duckdns.dbro94.texasholdemcalculator.domain.Card;
import org.duckdns.dbro94.texasholdemcalculator.domain.CardSuit;
import org.duckdns.dbro94.texasholdemcalculator.domain.CardValue;

public class CardMapper {

    public static Card getCard(String c) {
        Card card = null;
        switch (c) {
            case "A♠":
                card = new Card(CardValue.ACE, CardSuit.SPADE);
                break;
            case "2♠":
                card = new Card(CardValue.TWO, CardSuit.SPADE);
                break;
            case "3♠":
                card = new Card(CardValue.THREE, CardSuit.SPADE);
                break;
            case "4♠":
                card = new Card(CardValue.FOUR, CardSuit.SPADE);
                break;
            case "5♠":
                card = new Card(CardValue.FIVE, CardSuit.SPADE);
                break;
            case "6♠":
                card = new Card(CardValue.SIX, CardSuit.SPADE);
                break;
            case "7♠":
                card = new Card(CardValue.SEVEN, CardSuit.SPADE);
                break;
            case "8♠":
                card = new Card(CardValue.EIGHT, CardSuit.SPADE);
                break;
            case "9♠":
                card = new Card(CardValue.NINE, CardSuit.SPADE);
                break;
            case "10♠":
                card = new Card(CardValue.TEN, CardSuit.SPADE);
                break;
            case "J♠":
                card = new Card(CardValue.JACK, CardSuit.SPADE);
                break;
            case "Q♠":
                card = new Card(CardValue.QUEEN, CardSuit.SPADE);
                break;
            case "K♠":
                card = new Card(CardValue.KING, CardSuit.SPADE);
                break;
            case "A♣":
                card = new Card(CardValue.ACE, CardSuit.CLUB);
                break;
            case "2♣":
                card = new Card(CardValue.TWO, CardSuit.CLUB);
                break;
            case "3♣":
                card = new Card(CardValue.THREE, CardSuit.CLUB);
                break;
            case "4♣":
                card = new Card(CardValue.FOUR, CardSuit.CLUB);
                break;
            case "5♣":
                card = new Card(CardValue.FIVE, CardSuit.CLUB);
                break;
            case "6♣":
                card = new Card(CardValue.SIX, CardSuit.CLUB);
                break;
            case "7♣":
                card = new Card(CardValue.SEVEN, CardSuit.CLUB);
                break;
            case "8♣":
                card = new Card(CardValue.EIGHT, CardSuit.CLUB);
                break;
            case "9♣":
                card = new Card(CardValue.NINE, CardSuit.CLUB);
                break;
            case "10♣":
                card = new Card(CardValue.TEN, CardSuit.CLUB);
                break;
            case "J♣":
                card = new Card(CardValue.JACK, CardSuit.CLUB);
                break;
            case "Q♣":
                card = new Card(CardValue.QUEEN, CardSuit.CLUB);
                break;
            case "K♣":
                card = new Card(CardValue.KING, CardSuit.CLUB);
                break;
            case "A♥":
                card = new Card(CardValue.ACE, CardSuit.HEART);
                break;
            case "2♥":
                card = new Card(CardValue.TWO, CardSuit.HEART);
                break;
            case "3♥":
                card = new Card(CardValue.THREE, CardSuit.HEART);
                break;
            case "4♥":
                card = new Card(CardValue.FOUR, CardSuit.HEART);
                break;
            case "5♥":
                card = new Card(CardValue.FIVE, CardSuit.HEART);
                break;
            case "6♥":
                card = new Card(CardValue.SIX, CardSuit.HEART);
                break;
            case "7♥":
                card = new Card(CardValue.SEVEN, CardSuit.HEART);
                break;
            case "8♥":
                card = new Card(CardValue.EIGHT, CardSuit.HEART);
                break;
            case "9♥":
                card = new Card(CardValue.NINE, CardSuit.HEART);
                break;
            case "10♥":
                card = new Card(CardValue.TEN, CardSuit.HEART);
                break;
            case "J♥":
                card = new Card(CardValue.JACK, CardSuit.HEART);
                break;
            case "Q♥":
                card = new Card(CardValue.QUEEN, CardSuit.HEART);
                break;
            case "K♥":
                card = new Card(CardValue.KING, CardSuit.HEART);
                break;
            case "A♦":
                card = new Card(CardValue.ACE, CardSuit.DIAMOND);
                break;
            case "2♦":
                card = new Card(CardValue.TWO, CardSuit.DIAMOND);
                break;
            case "3♦":
                card = new Card(CardValue.THREE, CardSuit.DIAMOND);
                break;
            case "4♦":
                card = new Card(CardValue.FOUR, CardSuit.DIAMOND);
                break;
            case "5♦":
                card = new Card(CardValue.FIVE, CardSuit.DIAMOND);
                break;
            case "6♦":
                card = new Card(CardValue.SIX, CardSuit.DIAMOND);
                break;
            case "7♦":
                card = new Card(CardValue.SEVEN, CardSuit.DIAMOND);
                break;
            case "8♦":
                card = new Card(CardValue.EIGHT, CardSuit.DIAMOND);
                break;
            case "9♦":
                card = new Card(CardValue.NINE, CardSuit.DIAMOND);
                break;
            case "10♦":
                card = new Card(CardValue.TEN, CardSuit.DIAMOND);
                break;
            case "J♦":
                card = new Card(CardValue.JACK, CardSuit.DIAMOND);
                break;
            case "Q♦":
                card = new Card(CardValue.QUEEN, CardSuit.DIAMOND);
                break;
            case "K♦":
                card = new Card(CardValue.KING, CardSuit.DIAMOND);
                break;
            default:
                break;
        }
        return card;
    }

}