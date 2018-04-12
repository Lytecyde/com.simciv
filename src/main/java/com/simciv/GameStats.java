package com.simciv;

public class GameStats {
    public static String mapSize;
    static String[] playerName = new String[2];
    public static int maxX;
    public static int maxY;
    static void save(String[] name , String size){
        playerName[0] = name[0];
        playerName[1] = name[1];
        mapSize = size;
    }
}
