/*
    Implements the controller and view for the main menu.
    Copyright (C) 2015  Conner Dunn

    This file is part of ccdunn-reflex.

    ccdunn-reflex is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    ccdunn-reflex is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with ccdunn-reflex.  If not, see <http://www.gnu.org/licenses/>.
 */
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
