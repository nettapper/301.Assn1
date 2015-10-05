/*
    Implements the model for the players to play a buzzer game.
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
