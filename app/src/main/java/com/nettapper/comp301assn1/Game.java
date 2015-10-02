package com.nettapper.comp301assn1;

/**
 * Created by Conner on 15-10-01.
 */
public class Game {
    private long startTime;

    public void start(){
        // wait rand time between 10 ms and 2000 ms
        // set the initial time var
        startTime = System.nanoTime();

    }
    public void stop(Player player){
        // get the current time
        // calc diff between current time and init time
        long stopTime = System.nanoTime();
        long diff = Math.abs((stopTime - startTime));
        player.addTime(((int) diff));
        player.incBuzzerWon();

    }
    public void reset(){

    }
}
