package com.nettapper.comp301assn1;

import java.util.ArrayList;

/**
 * Created by Conner on 15-10-01.
 */
public class Player {
    private Statistics stat = new Statistics();
    private Integer buzzersWon = 0;

    public void addTime(Integer t){
        stat.addTime(t);
    }

    public void incBuzzerWon(){
        buzzersWon++;
    }

    public Integer getBuzzersWon(){
        return buzzersWon;
    }

    public void reset(){
        stat.reset();
    }

    public Statistics getStats(){
        return stat;
    }
}
