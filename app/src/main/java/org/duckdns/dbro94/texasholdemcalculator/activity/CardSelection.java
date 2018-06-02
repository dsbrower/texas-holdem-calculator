package org.duckdns.dbro94.texasholdemcalculator.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.duckdns.dbro94.texasholdemcalculator.R;

public class CardSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_selection);
    }

    public void selectCard(View v) {
        Intent selectedCard = new Intent();
        Button card = (Button) v;
        selectedCard.putExtra("CARD", card.getText().toString());
        setResult(Activity.RESULT_OK, selectedCard);
        finish();
    }
}
