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

        // set the initial time var & offset the time to be when the user sees the color change
        // Oct 1 2015, Kevin Bourrillion, http://stackoverflow.com/questions/1770010/how-do-i-measure-time-elapsed-in-java
        startTime = System.nanoTime() + sleepTime * 1000000; // 1 ns = 1 ms * 10^(6)

        return sleepTime;
    }
    public int stop(Player player) throws InvalidKeyException {
        // don't want to record more than one time per game
        if(startTime == 0 && sleepTime == 0){
            return -1;
        }
        // get the current time
        long stopTime = System.nanoTime();
        // calc diff between current time and init time
        long diff = Math.abs((stopTime - startTime));
        diff = diff / 1000000;  // 1 ns * 10^(-6) = 1 ms
        if(stopTime < startTime) {
            throw new InvalidKeyException();
        }
        player.addTime(((int) diff));
        player.incBuzzerWon();

        reset(); // nedd to zero ou the times
        return (int) diff;
    }
    public void reset(){
        startTime = 0;
        sleepTime = 0;
    }
}
