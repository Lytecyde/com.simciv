package com.simciv;

public class GameStats {
    public static final Coordinate CENTER = new Coordinate(7, 5);
    public static final Coordinate START = new Coordinate(0, 0);
    public static String mapSize;
    public static String[] playerName = new String[2];
    public static int maxX;
    public static int maxY;
    //private static int unitsInTotal;
    public final static int tileSize = 20;
    public static int time = -4020;
    public static int playerCount = 2;
    //public static Players players = new Players();
    static int idCount;
    public static String[][] colorMap;
    public static void init(String[] name , String size){
        playerName[0] = name[0];
        playerName[1] = name[1];
        mapSize = size;

        switch(size) {
            case "Small":
                maxX = 50;
                maxY = 32;
                break;
            case "Medium":
                maxX = 64;
                maxY = 40;
                break;
            case "Large":
                maxX = 72;
                maxY = 48;
                break;
            case "Huge":
                maxX = 90;
                maxY = 64;
                break;
            default:
                maxX = 60;
                maxY = 40;
        }
        colorMap = new String[maxX][maxY];
    }
}
