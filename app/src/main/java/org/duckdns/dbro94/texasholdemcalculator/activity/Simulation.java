package org.duckdns.dbro94.texasholdemcalculator.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    private static final int H1 = 1;
    private static final int H2 = 2;
    private static final int C1 = 3;
    private static final int C2 = 4;
    private static final int C3 = 5;
    private static final int C4 = 6;
    private static final int C5 = 7;

    TextView winPercentage;
    TextView tiePercentage;
    TextView lossPercentage;
    TextView txtNumPlayers;
    Button txtH1;
    Button txtH2;
    Button txtF1;
    Button txtF2;
    Button txtF3;
    Button txtTurn;
    Button txtRiver;
    int numPlayers;
    int startingNumPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulation);

        Intent sim = getIntent();
        startingNumPlayers = sim.getIntExtra("numPlayers", 5);
        numPlayers = startingNumPlayers;

        winPercentage = (TextView) findViewById(R.id.winPercentage);
        tiePercentage = (TextView) findViewById(R.id.tiePercentage);
        lossPercentage = (TextView) findViewById(R.id.lossPercentage);
        txtNumPlayers = (TextView) findViewById(R.id.numPlayersSim);
        txtH1 = (Button) findViewById(R.id.h1);
        txtH2 = (Button) findViewById(R.id.h2);
        txtF1 = (Button) findViewById(R.id.c1);
        txtF2 = (Button) findViewById(R.id.c2);
        txtF3 = (Button) findViewById(R.id.c3);
        txtTurn = (Button) findViewById(R.id.c4);
        txtRiver = (Button) findViewById(R.id.c5);

        txtNumPlayers.setText(Integer.toString(numPlayers));
    }

    public void selectCard(View v) {
        Intent cs = new Intent(Simulation.this, CardSelection.class);
        switch(v.getId()) {
            case R.id.h1:
                startActivityForResult(cs, H1);
                break;
            case R.id.h2:
                startActivityForResult(cs, H2);
                break;
            case R.id.c1:
                startActivityForResult(cs, C1);
                break;
            case R.id.c2:
                startActivityForResult(cs, C2);
                break;
            case R.id.c3:
                startActivityForResult(cs, C3);
                break;
            case R.id.c4:
                startActivityForResult(cs, C4);
                break;
            case R.id.c5:
                startActivityForResult(cs, C5);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            String c = data.getStringExtra("CARD");
            switch(requestCode) {
                case H1:
                    txtH1.setText(c);
                    break;
                case H2:
                    txtH2.setText(c);
                    break;
                case C1:
                    txtF1.setText(c);
                    break;
                case C2:
                    txtF2.setText(c);
                    break;
                case C3:
                    txtF3.setText(c);
                    break;
                case C4:
                    txtTurn.setText(c);
                    break;
                case C5:
                    txtRiver.setText(c);
                    break;
            }
            submit();
        }
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
        numPlayers = startingNumPlayers;
        txtNumPlayers.setText(Integer.toString(numPlayers));
    }

    public void fold(View v) {
        if (numPlayers > 2) {
            numPlayers--;
            txtNumPlayers.setText(Integer.toString(numPlayers));
            submit();
        }
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

        for (int run = 0; run < 1000; run++) {
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

            for (int j = hand.size(); j < 2; j++) {
                hand.add(deck.remove(0));
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

        double winPercent = round(((double)wins / ((double)wins + (double)ties + (double)losses) * 100), 1);
        double tiePercent = round(((double)ties / ((double)wins + (double)ties + (double)losses) * 100), 1);
        double lossPercent = round(((double)losses / ((double)wins + (double)ties + (double)losses) * 100), 1);

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
