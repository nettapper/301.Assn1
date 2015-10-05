/*
    Implements the controller to save and retrieve players.
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
