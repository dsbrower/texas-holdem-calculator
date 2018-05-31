package org.duckdns.dbro94.texasholdemcalculator.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;
import org.duckdns.dbro94.texasholdemcalculator.R;
import org.duckdns.dbro94.texasholdemcalculator.domain.Card;
import org.duckdns.dbro94.texasholdemcalculator.util.CardMapper;
import org.duckdns.dbro94.texasholdemcalculator.util.Deck;
import org.duckdns.dbro94.texasholdemcalculator.util.HandEvaluator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.duckdns.dbro94.texasholdemcalculator.util.Constants.LOSS;
import static org.duckdns.dbro94.texasholdemcalculator.util.Constants.TIE;
import static org.duckdns.dbro94.texasholdemcalculator.util.Constants.WIN;

public class Simulation extends AppCompatActivity {

    TextView winPercentage;
    TextView tiePercentage;
    TextView lossPercentage;
    TextView txtNumPlayers;
    TextView txtH1;
    TextView txtH2;
    TextView txtF1;
    TextView txtF2;
    TextView txtF3;
    TextView txtTurn;
    TextView txtRiver;
    int numPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulation);

        Intent sim = getIntent();
        numPlayers = sim.getIntExtra("numPlayers", 5);

        winPercentage = (TextView) findViewById(R.id.winPercentage);
        tiePercentage = (TextView) findViewById(R.id.tiePercentage);
        lossPercentage = (TextView) findViewById(R.id.lossPercentage);
        txtNumPlayers = (TextView) findViewById(R.id.numPlayersSim);
        txtH1 = (TextView) findViewById(R.id.h1);
        txtH2 = (TextView) findViewById(R.id.h2);
        txtF1 = (TextView) findViewById(R.id.f1);
        txtF2 = (TextView) findViewById(R.id.f2);
        txtF3 = (TextView) findViewById(R.id.f3);
        txtTurn = (TextView) findViewById(R.id.turn);
        txtRiver = (TextView) findViewById(R.id.river);

        txtNumPlayers.setText(Integer.toString(numPlayers));
    }

    public void clear(View v) {
        winPercentage.setText("");
        tiePercentage.setText("");
        lossPercentage.setText("");
        txtH1.setText("");
        txtH2.setText("");
        txtF1.setText("");
        txtF2.setText("");
        txtF3.setText("");
        txtTurn.setText("");
        txtRiver.setText("");
    }

    public void fold(View v) {
        if (numPlayers > 2) {
            numPlayers--;
            txtNumPlayers.setText(Integer.toString(numPlayers));
            submit();
        }
    }

    public void submit(View v) {
        submit();
    }

    private void submit() {
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

        Double[] outcomes = calc(hand, comm);

        winPercentage.setText(outcomes[0].toString());
        tiePercentage.setText(outcomes[1].toString());
        lossPercentage.setText(outcomes[2].toString());
    }

    private Double[] calc(List<Card> handOrig, List<Card> commOrig) {

        int wins = 0;
        int ties = 0;
        int losses = 0;

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

            List<List<Card>> hands = new LinkedList<>();

            List<Card> playerHand = new LinkedList<>(hand);
            playerHand.addAll(comm);
            hands.add(playerHand);

            for (int j = 0; j < numPlayers - 1; j++) {
                List<Card> cpu = new LinkedList<>();
                cpu.add(deck.remove(0));
                cpu.add(deck.remove(0));
                cpu.addAll(comm);
                hands.add(cpu);
            }

            String outcome = HandEvaluator.evaluate(hands);

            switch(outcome) {
                case WIN:
                    wins++;
                    break;
                case TIE:
                    ties++;
                    break;
                case LOSS:
                    losses++;
                    break;
            }

        }

        double winPercent = round(((double)wins / ((double)wins + (double)ties + (double)losses) * 100), 2);
        double tiePercent = round(((double)ties / ((double)wins + (double)ties + (double)losses) * 100), 2);
        double lossPercent = round(((double)losses / ((double)wins + (double)ties + (double)losses) * 100), 2);

        Double[] outcomes = new Double[]{winPercent, tiePercent, lossPercent};

        return outcomes;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
