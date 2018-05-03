package com.simciv;

import com.simciv.Players.Player;
import com.simciv.Players.Players;

public class GameStats {
    public static String mapSize;
    public static String[] playerName = new String[2];
    public static int maxX;
    public static int maxY;
    public static int unitsInTotal;
    public static int tileSize = 20;
    public static int time = -4020;
    public static int playerCount = 2;
    public static Players players = new Players();

    public static int idCount;

    public static Players makePlayers() {
        Player p =  new Player();
        players.list.add(p);
        return players;
    }

    public static void saveInit(String[] name , String size){
        playerName[0] = name[0];
        playerName[1] = name[1];
        mapSize = size;

        switch(size) {
            case "Small":
                maxX = 50;
                maxY = 32;
                break;
            case "Medium":
                maxX = 60;
                maxY = 36;
                break;
            case "Large":
                maxX = 72;
                maxY = 44;
                break;
            case "Huge":
                maxX = 90;
                maxY = 52;
                break;
            default:
                maxX = 60;
                maxY = 36;
        }
        unitsInTotal = 2;
    }
}
