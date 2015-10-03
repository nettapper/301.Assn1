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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.list_item, statsToDisplay);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        // end Joshua Campbell
    }

    private void loadFromFile() {
        RecordManager recMan = new RecordManager();
        Player player;
        try {
            player = recMan.load(new FileInputStream("player1.sav"));
        } catch (FileNotFoundException e) {
            player = new Player();
        }
        player = new Player();  // todo: make above work
        statsToDisplay = player.getReactionStats();
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
