package com.nettapper.comp301assn1;

/**
 * Created by Conner on 15-10-01.
 */
public class Buzzer {
    Boolean gameRunning = false;

    public void start(){
        gameRunning = true;
    }
    public void stop(Player player){
        player.incBuzzerWon();
        gameRunning = false;
    }
    public void reset(){
        gameRunning = false;
    }
}
