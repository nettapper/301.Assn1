package com.nettapper.comp301assn1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initOnClickListeners();
    }

    private void initOnClickListeners() {
        Button main_onePlayer = (Button) findViewById(R.id.main_onePlayer);
        main_onePlayer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Oct 1 2015, Rudi Kershaw, http://stackoverflow.com/questions/24610527/how-do-i-get-a-button-to-open-another-activity-in-android-studio
                startActivity(new Intent(MainActivity.this, OnePlayerActivity.class));
            }
        });

        Button main_Buzzer = (Button) findViewById(R.id.main_Buzzer);
        main_Buzzer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Oct 1 2015, Rudi Kershaw, http://stackoverflow.com/questions/24610527/how-do-i-get-a-button-to-open-another-activity-in-android-studio
                startActivity(new Intent(MainActivity.this, BuzzerActivity.class));
            }
        });

        Button main_Stats = (Button) findViewById(R.id.main_Stats);
        main_Stats.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Oct 1 2015, Rudi Kershaw, http://stackoverflow.com/questions/24610527/how-do-i-get-a-button-to-open-another-activity-in-android-studio
                startActivity(new Intent(MainActivity.this, StatsActivity.class));
            }
        });
    }

}
