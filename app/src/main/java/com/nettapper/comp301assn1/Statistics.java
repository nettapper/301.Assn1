package com.nettapper.comp301assn1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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
        int min = -1;
        if (times.size() >= 1){
            min = times.get(0);  // the min should start as the first element
        }
        for(int i = 0; i < times.size() && i < ofLast; i++){
            if(min > times.get(0)){
                min = times.get(0);
            }
        }

        return min;
    }
    public Integer maxTime(Integer ofLast){
        int max = -1;
        if (times.size() >= 1){
            max = times.get(0);  // the min should start as the first element
        }
        for(int i = 0; i < times.size() && i < ofLast; i++){
            if(max < times.get(0)){
                max = times.get(0);
            }
        }

        return max;
    }
    public Double averageTime(Integer ofLast){
        int i = 0;
        double sum = 0;
        for(i = 0; i < times.size() && i < ofLast; i++){
            sum += times.get(i);
        }
        return sum / i;
    }
    public Integer medianTime(Integer ofLast){
        ArrayList<Integer> copy = new ArrayList<>();
        for(int i = 0; i < times.size() && i < ofLast; i++){
            copy.add(times.get(i));
        }
        Collections.sort(copy);
        int med = -1;
        if(copy.size() >= 1){
            med = copy.get(copy.size() / 2);
        }
        return med;
    }
}
