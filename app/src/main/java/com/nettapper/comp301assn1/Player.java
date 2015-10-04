package com.nettapper.comp301assn1;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Conner on 15-10-01.
 */
public class Player {
    private Statistics stat = new Statistics();
    private ArrayList<Integer> buzzersWon = new ArrayList<>();

    public Player(){
        for(int i = 0; i < 4; i++){
            buzzersWon.add(i, 0);
        }
    }

    public void addTime(Integer t){
        stat.addTime(t);
    }

    public void incBuzzerWon(int gameid){
        try {
            Log.d("Player Debug", "we set");
            buzzersWon.set(gameid, buzzersWon.get(gameid) + 1);
        } catch (IndexOutOfBoundsException e){
            Log.d("Player Debug", "we added");
            buzzersWon.add(gameid, 1);
        }
    }

    public Integer getBuzzersWon(int index){
        return buzzersWon.get(index);
    }

    public void reset(){
        stat.reset();
    }

    public ArrayList<String> getReactionStats(){
        ArrayList<String> al = new ArrayList<>();

        al.add(String.format("Avg 10: %.2f", stat.averageTime(10)));
        al.add(String.format("Max 10: %d", stat.maxTime(10)));
        al.add(String.format("Min 10: %d", stat.minTime(10)));
        al.add(String.format("Med 10: %d", stat.medianTime(10)));

        al.add("");  // space out the stats for looks

        al.add(String.format("Avg 100: %.2f", stat.averageTime(100)));
        al.add(String.format("Max 100: %d", stat.maxTime(100)));
        al.add(String.format("Min 100: %d", stat.minTime(100)));
        al.add(String.format("Med 100: %d", stat.medianTime(100)));

        al.add("");  // space out the stats for looks

        al.addAll(stat.getTimes());  // insert the raw data

        return al;
    }
}
