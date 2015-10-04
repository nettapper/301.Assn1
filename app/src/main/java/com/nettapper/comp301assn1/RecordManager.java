package com.nettapper.comp301assn1;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Conner on 15-10-01.
 */
public class RecordManager {

    public void save(ArrayList<Player> players, FileOutputStream fos){
        // Oct 1 2015, Joshua Campbell, https://github.com/nettapper/lonelyTwitter/blob/4bf7fba49ee31d203ef37a27740e94f289532844/app/src/main/java/ca/ualberta/cs/lonelytwitter/LonelyTwitterActivity.java
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(players, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Player> load(FileInputStream fis) {
        ArrayList<Player> players = null;
        // Oct 1 2015, Joshua Campbell, https://github.com/nettapper/lonelyTwitter/blob/4bf7fba49ee31d203ef37a27740e94f289532844/app/src/main/java/ca/ualberta/cs/lonelytwitter/LonelyTwitterActivity.java
        BufferedReader in = new BufferedReader(new InputStreamReader(fis));
        Gson gson = new Gson();
        Type playerType = new TypeToken<ArrayList<Player>>() {}.getType();
        players = gson.fromJson(in,playerType);
        if (players == null){
            players = new ArrayList<Player>();
            for(int i = 0; i < 4; i++){
                players.add(i, new Player());
            }
        }
        return players;
    }
}
