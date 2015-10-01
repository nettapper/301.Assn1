package com.nettapper.comp301assn1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

        Button main_twoPlayer = (Button) findViewById(R.id.main_TwoPlayer);
        main_twoPlayer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Oct 1 2015, Rudi Kershaw, http://stackoverflow.com/questions/24610527/how-do-i-get-a-button-to-open-another-activity-in-android-studio
                startActivity(new Intent(MainActivity.this, TwoPlayerActivity.class));
            }
        });

        Button main_threePlayer = (Button) findViewById(R.id.main_ThreePlayer);
        main_threePlayer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Oct 1 2015, Rudi Kershaw, http://stackoverflow.com/questions/24610527/how-do-i-get-a-button-to-open-another-activity-in-android-studio
                startActivity(new Intent(MainActivity.this, ThreePlayerActivity.class));
            }
        });

        Button main_fourPlayer = (Button) findViewById(R.id.main_FourPlayer);
        main_fourPlayer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Oct 1 2015, Rudi Kershaw, http://stackoverflow.com/questions/24610527/how-do-i-get-a-button-to-open-another-activity-in-android-studio
                startActivity(new Intent(MainActivity.this, FourPlayerActivity.class));
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
