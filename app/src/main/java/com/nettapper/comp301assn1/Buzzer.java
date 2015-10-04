package com.nettapper.comp301assn1;

/**
 * Created by Conner on 15-10-01.
 */
public class Buzzer {
    Boolean gameRunning = false;

    public void start(){
        gameRunning = true;
    }
    public Boolean stop(Player player, int gameid){
        if(gameRunning){
            player.incBuzzerWon(gameid);
            gameRunning = false;
            return true;
        }
        return false;
    }
    public void reset(){
        gameRunning = false;
    }
}
