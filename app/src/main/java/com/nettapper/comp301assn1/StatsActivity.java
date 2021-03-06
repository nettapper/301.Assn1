/*
    Implements the controller and view in order to display the stats.
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity {
    private ArrayList<String> statsToDisplay = new ArrayList<>();
    ArrayAdapter<String> adapter;
    Boolean isOnReactionStats = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        loadReactionStats();
    }


    @Override
    protected void onStart() {
        // Oct 2 2015, Joshua Campbell, https://github.com/nettapper/lonelyTwitter/blob/4bf7fba49ee31d203ef37a27740e94f289532844/app/src/main/java/ca/ualberta/cs/lonelytwitter/LonelyTwitterActivity.java
        super.onStart();
        ListView lv = (ListView) findViewById(R.id.stats_vertLinLayout);
        adapter = new ArrayAdapter<String>(this,
                R.layout.list_item, statsToDisplay);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        // end Joshua Campbell

        Button reactionStats = (Button) findViewById(R.id.stats_reactionGame);
        Button buzzerStats = (Button) findViewById(R.id.stats_buzzerStats);

        reactionStats.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                loadReactionStats();
                onStart();
            }
        });

        buzzerStats.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                loadBuzzerStats();
                onStart();
            }
        });
    }

    private void loadReactionStats() {
        isOnReactionStats = true;
        ArrayList<Player> players = getPlayers("player.sav");
        statsToDisplay = players.get(0).getReactionStats();
    }

    private void loadBuzzerStats() {
        isOnReactionStats = false;
        ArrayList<Player> players = getPlayers("player.sav");
        statsToDisplay = new ArrayList<>();

        statsToDisplay.add("One Player Games:");
        statsToDisplay.add(String.format("Player 1: %d", players.get(0).getBuzzersWon(0)));
        statsToDisplay.add("");  // new line for formatting

        statsToDisplay.add("Two Player Games:");
        statsToDisplay.add(String.format("Player 1: %d", players.get(0).getBuzzersWon(1)));
        statsToDisplay.add(String.format("Player 2: %d", players.get(1).getBuzzersWon(1)));
        statsToDisplay.add("");  // new line for formatting

        statsToDisplay.add("Three Player Games:");
        statsToDisplay.add(String.format("Player 1: %d", players.get(0).getBuzzersWon(2)));
        statsToDisplay.add(String.format("Player 2: %d", players.get(1).getBuzzersWon(2)));
        statsToDisplay.add(String.format("Player 3: %d", players.get(2).getBuzzersWon(2)));
        statsToDisplay.add("");  // new line for formatting

        statsToDisplay.add("Four Player Games:");
        statsToDisplay.add(String.format("Player 1: %d", players.get(0).getBuzzersWon(3)));
        statsToDisplay.add(String.format("Player 2: %d", players.get(1).getBuzzersWon(3)));
        statsToDisplay.add(String.format("Player 3: %d", players.get(2).getBuzzersWon(3)));
        statsToDisplay.add(String.format("Player 4: %d", players.get(3).getBuzzersWon(3)));
    }

    private ArrayList<Player> getPlayers(String fileName){
        RecordManager recMan = new RecordManager();
        FileInputStream fis = null;
        ArrayList<Player> players = new ArrayList<>();
        for(int i = 0; i < 4; i++) {   // I want the default ret val to be 4 players
            players.add(i, new Player());
        }
        try {
            fis = openFileInput(fileName);
            players = recMan.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return players;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_stats, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_send_email) {
            // Oct 4 2015, becomputer06, http://stackoverflow.com/questions/8701634/send-email-intent
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/html");
            intent.putExtra(Intent.EXTRA_SUBJECT, "My awesome stats!");
            String body = stringify(statsToDisplay);
            intent.putExtra(Intent.EXTRA_TEXT, body);

            startActivity(Intent.createChooser(intent, "Send Email"));
            // end of becomputer06
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_clear_stats) {
            RecordManager recMan = new RecordManager();
            try {
                ArrayList<Player> empty = new ArrayList<>();
                for(int i = 0; i < 4; i++){
                    empty.add(new Player());
                }
                recMan.save(empty, openFileOutput("player.sav", 0));
                if(isOnReactionStats){
                    loadReactionStats();
                } else {
                    loadBuzzerStats();
                }
                onStart();
                return true;
            } catch (FileNotFoundException e) {
                statsToDisplay = new ArrayList<>();
                if(isOnReactionStats){
                    loadReactionStats();
                } else {
                    loadBuzzerStats();
                }
                onStart();
            }
            return false;
        }

        return super.onOptionsItemSelected(item);
    }

    private String stringify(ArrayList<String> statsToDisplay) {
        String longString = "";
        for(int i = 0; i < statsToDisplay.size(); i++){
            longString = longString.concat(statsToDisplay.get(i));
            longString = longString.concat("\n"); // new lines for formatting
        }
        return longString;
    }
}
