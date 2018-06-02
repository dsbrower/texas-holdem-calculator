package org.duckdns.dbro94.texasholdemcalculator.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import org.duckdns.dbro94.texasholdemcalculator.R;

public class MainActivity extends AppCompatActivity {

    int numPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numPlayers = 2;
    }

    public void submit(View v) {
        Intent sim = new Intent(MainActivity.this, Simulation.class);
        sim.putExtra("numPlayers", numPlayers);
        startActivity(sim);
    }

    public void plus(View v) {
        if (numPlayers < 8) {
            String id = "p" + ++numPlayers;
            ImageView playerIcon;
            switch (id) {
                case "p3":
                    playerIcon = (ImageView) findViewById(R.id.p3);
                    break;
                case "p4":
                    playerIcon = (ImageView) findViewById(R.id.p4);
                    break;
                case "p5":
                    playerIcon = (ImageView) findViewById(R.id.p5);
                    break;
                case "p6":
                    playerIcon = (ImageView) findViewById(R.id.p6);
                    break;
                case "p7":
                    playerIcon = (ImageView) findViewById(R.id.p7);
                    break;
                case "p8":
                    playerIcon = (ImageView) findViewById(R.id.p8);
                    break;
                default:
                    playerIcon = (ImageView) findViewById(R.id.p1);
                    break;
            }
            playerIcon.setBackgroundResource(R.drawable.person);
        }
    }

    public void minus(View v) {
        if (numPlayers > 2) {
            String id = "p" + numPlayers--;
            ImageView playerIcon;
            switch (id) {
                case "p3":
                    playerIcon = (ImageView) findViewById(R.id.p3);
                    break;
                case "p4":
                    playerIcon = (ImageView) findViewById(R.id.p4);
                    break;
                case "p5":
                    playerIcon = (ImageView) findViewById(R.id.p5);
                    break;
                case "p6":
                    playerIcon = (ImageView) findViewById(R.id.p6);
                    break;
                case "p7":
                    playerIcon = (ImageView) findViewById(R.id.p7);
                    break;
                case "p8":
                    playerIcon = (ImageView) findViewById(R.id.p8);
                    break;
                default:
                    playerIcon = (ImageView) findViewById(R.id.p1);
                    break;
            }
            playerIcon.setBackgroundResource(R.drawable.person_empty);
        }
    }
}
