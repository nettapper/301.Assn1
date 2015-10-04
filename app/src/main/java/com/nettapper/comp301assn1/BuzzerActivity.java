package com.nettapper.comp301assn1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class BuzzerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buzzer);
        initOnClickListeners();
    }

    private void initOnClickListeners() {
        Button buzzer_twoPlayer = (Button) findViewById(R.id.buzzer_TwoPlayer);
        buzzer_twoPlayer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Oct 1 2015, Rudi Kershaw, http://stackoverflow.com/questions/24610527/how-do-i-get-a-button-to-open-another-activity-in-android-studio
                startActivity(new Intent(BuzzerActivity.this, TwoPlayerActivity.class));
            }
        });

        Button buzzer_threePlayer = (Button) findViewById(R.id.buzzer_ThreePlayer);
        buzzer_threePlayer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Oct 1 2015, Rudi Kershaw, http://stackoverflow.com/questions/24610527/how-do-i-get-a-button-to-open-another-activity-in-android-studio
                startActivity(new Intent(BuzzerActivity.this, ThreePlayerActivity.class));
            }
        });

        Button buzzer_fourPlayer = (Button) findViewById(R.id.buzzer_FourPlayer);
        buzzer_fourPlayer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Oct 1 2015, Rudi Kershaw, http://stackoverflow.com/questions/24610527/how-do-i-get-a-button-to-open-another-activity-in-android-studio
                startActivity(new Intent(BuzzerActivity.this, FourPlayerActivity.class));
            }
        });
    }
}
