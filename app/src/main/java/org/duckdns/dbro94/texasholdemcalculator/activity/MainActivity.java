package org.duckdns.dbro94.texasholdemcalculator.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;
import org.duckdns.dbro94.texasholdemcalculator.R;

public class MainActivity extends AppCompatActivity {

    TextView txtNumPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNumPlayers = (TextView) findViewById(R.id.numPlayers);
    }

    public void submit(View v) {
        if (!StringUtils.isBlank(txtNumPlayers.getText().toString())) {
            int numPlayers = Integer.parseInt(txtNumPlayers.getText().toString());
            if (numPlayers >= 2 && numPlayers <= 10) {
                Intent sim = new Intent(MainActivity.this, Simulation.class);
                sim.putExtra("numPlayers", numPlayers);
                startActivity(sim);
            }
        }
    }
}
