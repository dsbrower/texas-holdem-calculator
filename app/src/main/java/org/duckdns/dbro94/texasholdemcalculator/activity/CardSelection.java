package org.duckdns.dbro94.texasholdemcalculator.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.duckdns.dbro94.texasholdemcalculator.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CardSelection extends AppCompatActivity {

    private static List<String> values = new LinkedList<>();
    private static List<String> suits = new LinkedList<>();
    static {
        values.addAll(Arrays.asList("ace", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "jack", "queen", "king"));
        suits.addAll(Arrays.asList("S", "C", "H", "D"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_selection);

        ArrayList<String> selectedCards = getIntent().getStringArrayListExtra("SELECTED_CARDS");
        for (String value : values) {
            for (String suit : suits) {
                String id = value + suit;
                int resId = getResources().getIdentifier(id, "id", getPackageName());
                Button button = (Button) findViewById(resId);
                if (selectedCards.contains(button.getText().toString())) {
                    button.setVisibility(View.INVISIBLE);
                }
                else {
                    button.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void selectCard(View v) {
        Intent selectedCard = new Intent();
        Button card = (Button) v;
        selectedCard.putExtra("CARD", card.getText().toString());
        setResult(Activity.RESULT_OK, selectedCard);
        finish();
    }
}
