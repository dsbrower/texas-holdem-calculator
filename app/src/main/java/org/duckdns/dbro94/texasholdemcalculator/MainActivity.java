package org.duckdns.dbro94.texasholdemcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;
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

public class MainActivity extends AppCompatActivity {

    TextView winPercentage;
    TextView txtH1;
    TextView txtH2;
    TextView txtF1;
    TextView txtF2;
    TextView txtF3;
    TextView txtTurn;
    TextView txtRiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        winPercentage = (TextView) findViewById(R.id.winPercentage);
        txtH1 = (TextView) findViewById(R.id.h1);
        txtH2 = (TextView) findViewById(R.id.h2);
        txtF1 = (TextView) findViewById(R.id.f1);
        txtF2 = (TextView) findViewById(R.id.f2);
        txtF3 = (TextView) findViewById(R.id.f3);
        txtTurn = (TextView) findViewById(R.id.turn);
        txtRiver = (TextView) findViewById(R.id.river);
    }

    public void submit(View v) {
        Card h1;
        Card h2;
        Card f1;
        Card f2;
        Card f3;
        Card turn;
        Card river;

        List<Card> hand = new LinkedList<>();
        List<Card> comm = new LinkedList<>();

        if (!StringUtils.isBlank(txtH1.getText().toString())) {
            h1 = CardMapper.getCard(txtH1.getText().toString());
            hand.add(h1);
        }
        if (!StringUtils.isBlank(txtH2.getText().toString())) {
            h2 = CardMapper.getCard(txtH2.getText().toString());
            hand.add(h2);
        }
        if (!StringUtils.isBlank(txtF1.getText().toString())) {
            f1 = CardMapper.getCard(txtF1.getText().toString());
            comm.add(f1);
        }
        if (!StringUtils.isBlank(txtF2.getText().toString())) {
            f2 = CardMapper.getCard(txtF2.getText().toString());
            comm.add(f2);
        }
        if (!StringUtils.isBlank(txtF3.getText().toString())) {
            f3 = CardMapper.getCard(txtF3.getText().toString());
            comm.add(f3);
        }
        if (!StringUtils.isBlank(txtTurn.getText().toString())) {
            turn = CardMapper.getCard(txtTurn.getText().toString());
            comm.add(turn);
        }
        if (!StringUtils.isBlank(txtRiver.getText().toString())) {
            river = CardMapper.getCard(txtRiver.getText().toString());
            comm.add(river);
        }

        winPercentage.setText(calc(hand, comm));
    }

    private String calc(List<Card> handOrig, List<Card> commOrig) {

        int wins = 0;

        for (int run = 0; run < 10000; run++) {
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
            List<Card> comm = new LinkedList<>(commOrig);
            for (Card card : comm) {
                for (Card c : deck) {
                    if (c.getSuit().equals(card.getSuit()) && c.getValue().getCardValue() == card.getValue().getCardValue()) {
                        deck.remove(c);
                        break;
                    }
                }
            }

            for (int j = comm.size(); j < 5; j++) {
                comm.add(deck.remove(0));
            }

            List<Card> cpu1 = new LinkedList<>();
            List<Card> cpu2 = new LinkedList<>();
            List<Card> cpu3 = new LinkedList<>();
            List<Card> cpu4 = new LinkedList<>();

            cpu1.add(deck.remove(0));
            cpu1.add(deck.remove(0));
            cpu2.add(deck.remove(0));
            cpu2.add(deck.remove(0));
            cpu3.add(deck.remove(0));
            cpu3.add(deck.remove(0));
            cpu4.add(deck.remove(0));
            cpu4.add(deck.remove(0));

            cpu1.addAll(comm);
            cpu2.addAll(comm);
            cpu3.addAll(comm);
            cpu4.addAll(comm);

            List<Card> playerHand = new LinkedList<>(hand);
            playerHand.addAll(comm);

            Rank playerRank = HandEvaluator.evaluate(playerHand);
            Rank cpu1Rank = HandEvaluator.evaluate(cpu1);
            Rank cpu2Rank = HandEvaluator.evaluate(cpu2);
            Rank cpu3Rank = HandEvaluator.evaluate(cpu3);
            Rank cpu4Rank = HandEvaluator.evaluate(cpu4);

            if ((playerRank.getRankValue() > cpu1Rank.getRankValue())
                    && (playerRank.getRankValue() > cpu2Rank.getRankValue())
                    && (playerRank.getRankValue() > cpu3Rank.getRankValue())
                    && (playerRank.getRankValue() > cpu4Rank.getRankValue())) {
                wins++;
            }

        }

        double percent = round((wins / 10000.0 * 100), 2);

        return Double.toString(percent);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
