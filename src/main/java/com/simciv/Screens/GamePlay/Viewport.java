package com.simciv.Screens.GamePlay;

import com.simciv.Coordinate;
import com.simciv.GameStats;
import com.simciv.Graphics.Colors;

import static com.simciv.GameStats.START;
import static com.simciv.Graphics.Colors.*;

public class Viewport {
    static int maxX = 15;
    static int maxY = 12;
    String[][] landscape ;
    private Coordinate topLeftCorner;
    int diffX;
    int diffY;
    private GameMap gameMap;
    Viewport(GameMap g) {
        diffX = 0;
        diffY = 0;
        topLeftCorner = START;
        gameMap = g;
        landscape = makeViewport();
    }

    Coordinate getAdjustedStart(Coordinate f) {
        int x;
        int y;
        //value for x
        if ((f.x + gameMap.viewport.diffX) >= GameStats.maxX - maxX) {
            x = f.x;
        } else if ((f.x + gameMap.viewport.diffX) < START.x) {
            x = f.x;
        } else {
            x = (f.x + gameMap.viewport.diffX);
        }
        //value for y
        if ((f.y + gameMap.viewport.diffY) >= GameStats.maxY - maxY) {
            y = f.y;
        } else if ((f.y + gameMap.viewport.diffY) < START.y) {
            y = f.y;
        } else {
            y = (f.y + gameMap.viewport.diffY);
        }
        resetDifference();
        return new Coordinate(x, y);
    }

    void redrawViewPort(Coordinate topLeftCorner) {
        for (int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                String s;
                Bounds bounds = new Bounds(gameMap,
                        topLeftCorner.x + x,
                        topLeftCorner.y + y);
                if (bounds.isWithin()) {
                    s = gameMap.viewport.landscape[topLeftCorner.x + x][topLeftCorner.y + y];
                    gameMap.colorMapSelection[x][y] = s;
                } else {
                    System.out.println("erroneous numbers!!!!");
                }
            }
        }
    }

    void resetDifference() {
        gameMap.viewport.diffX = 0;
        gameMap.viewport.diffY = 0;
    }

    /**
     * @return the array for the landscape map colors
     */
    private String[][] makeViewport() {
        String[][] landscape = new String[GameStats.maxX][GameStats.maxY];
        for (int x = 0; x < Viewport.maxX; x++) {
            for (int y = 0; y < Viewport.maxY; y++) {
                String color = colorLandscape();
                landscape[x][y] = color;
            }
        }
        GameStats.colorMap = landscape;
        return landscape;
    }

    private String colorLandscape() {
        int color = getRandomLandIndex();
        System.out.println(""+color+lands[color]);
        return lands[color];
    }

    private int getRandomLandIndex() {
        return (int) Math.floor(Math.random() * (lands.length - 2)) + 2;
    }
}
