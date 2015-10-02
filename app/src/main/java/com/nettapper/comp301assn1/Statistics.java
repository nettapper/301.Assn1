package com.nettapper.comp301assn1;

import java.util.ArrayList;

/**
 * Created by Conner on 15-10-01.
 */
public class Statistics {
    ArrayList<Integer> times = new ArrayList<>();

    public void addTime(Integer time){
        times.add(time);
    }
    public void reset(){
        times = new ArrayList<>();
    }
    public Integer minTime(Integer ofLast){
        return 0;
    }
    public Integer maxTime(Integer ofLast){
        return 0;
    }
    public Integer averageTime(Integer ofLast){
        return 0;
    }
    public Integer medianTime(Integer ofLast){
        return 0;
    }
}
