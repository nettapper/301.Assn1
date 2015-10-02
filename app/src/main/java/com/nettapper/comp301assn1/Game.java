package com.nettapper.comp301assn1;

import android.graphics.Color;
import android.util.Log;
import android.widget.Button;

import java.security.InvalidKeyException;
import java.util.Random;

/**
 * Created by Conner on 15-10-01.
 */
public class Game {
    private long startTime;
    private int sleepTime;

    public int start(){
        // wait rand time between 10 ms and 2000 ms
        Random r = new Random();
        int sleepTime = (int)(1990 * r.nextDouble() + 10);
        // Oct 1 2015, Konrad Garus, http://stackoverflow.com/questions/3342651/how-can-i-delay-a-java-program-for-a-few-seconds
        /*
        try {
            Thread.sleep(sleepTime);  // time is in ms
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        */

        // set the initial time var
        // Oct 1 2015, Kevin Bourrillion, http://stackoverflow.com/questions/1770010/how-do-i-measure-time-elapsed-in-java
        startTime = System.nanoTime();

        return sleepTime;
    }
    public int stop(Player player) throws InvalidKeyException {
        // get the current time
        // calc diff between current time and init time
        long stopTime = System.nanoTime();
        long diff = Math.abs((stopTime - startTime));
        diff = diff / 1000000;  // diff is now in ms
        if((int) diff < sleepTime) {
            throw new InvalidKeyException();
        }
        player.addTime(((int) diff));
        player.incBuzzerWon();

        return (int) diff;
    }
    public void reset(){

    }
}
