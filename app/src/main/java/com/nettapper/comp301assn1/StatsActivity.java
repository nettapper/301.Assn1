package com.nettapper.comp301assn1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity {
    private ArrayList<String> statsToDisplay = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
    }


    @Override
    protected void onStart() {
        // Oct 2 2015, Joshua Campbell, https://github.com/nettapper/lonelyTwitter/blob/4bf7fba49ee31d203ef37a27740e94f289532844/app/src/main/java/ca/ualberta/cs/lonelytwitter/LonelyTwitterActivity.java
        super.onStart();
        ListView lv = (ListView) findViewById(R.id.stats_vertLinLayout);
        loadFromFile();
        adapter = new ArrayAdapter<String>(this,
                R.layout.list_item, statsToDisplay);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        // end Joshua Campbell
    }

    private void loadFromFile() {
        ArrayList<Player> players = getPlayers("player.sav");
        statsToDisplay = players.get(0).getReactionStats();
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_clear_stats) {
            RecordManager recMan = new RecordManager();
            try {
                ArrayList<Player> empty = new ArrayList<>();
                for(int i = 0; i < 4; i++){
                    empty.add(new Player());
                }
                recMan.save(empty, openFileOutput("player.sav", 0));
                onStart();
                return true;
            } catch (FileNotFoundException e) {
                statsToDisplay = new ArrayList<>();
                onStart();
            }
            return false;
        }

        return super.onOptionsItemSelected(item);
    }
}
