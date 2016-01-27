package com.example.administrator.demo1;

import android.app.Application;

/**
 * Created by Teng on 2015/11/11.
 */
public class cbeer extends Application {
    String [] players = new String[12];
    int numberPlayers;

    protected int getNumberPlayers() {
        return numberPlayers;
    }

    protected void setNumberPlayers(int numberPlayers) {
        this.numberPlayers = numberPlayers;
    }

    protected void setPlayers(String [] players){
        this.players = players;
    }
    protected void setOnePlayer(int number, String player){
        players[number] = player;
    }

    protected String getPlayerByNumber(int number){
        return players[number];
    }

    //If return -1, that means it didn't find the player
    protected int findPlayerNumberByName(String player){
        for(int i = 0;i<12;i++){
            if(player.equals(players[i])) return i;
        }
        return -1;
    }
}
