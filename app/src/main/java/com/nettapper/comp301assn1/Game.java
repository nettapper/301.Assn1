/*
    Implements the controller for players to play the reflex game.
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
        player.incBuzzerWon(0);

        reset(); // nedd to zero ou the times
        return (int) diff;
    }
    public void reset(){
        startTime = 0;
        sleepTime = 0;
    }
}
