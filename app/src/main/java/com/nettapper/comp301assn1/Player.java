package com.nettapper.comp301assn1;

import java.util.ArrayList;

/**
 * Created by Conner on 15-10-01.
 */
public class Player {
    Statistics stat = new Statistics();
    public String FILENAME;

    public Player(String fileName){
        FILENAME = fileName;
    }

    public String fileName(){
        return FILENAME;
    }

    public void addTime(Integer t){
        stat.addTime(t);
    }

    public void reset(){
        stat.reset();
    }
}
