package com.simciv.Screens.GamePlay;

import com.simciv.Coordinate;
import com.simciv.GameStats;

import static com.simciv.GameStats.START;

public class Viewport {
    static int maxX = 15;
    static int maxY = 12;
    private String[][] landscape ;
    int diffX;
    int diffY;
    private GameMap gameMap;

    Viewport(GameMap g) {
        diffX = 0;
        diffY = 0;
        gameMap = g;
        landscape = makeViewport();
    }

    private void resetDifference() {
        this.diffX = 0;
        this.diffY = 0;
    }

    /**
     * @return the array for the landscape map colors
     *
     */
    private String[][] makeViewport() {
        String[][] landscape = new String[GameStats.maxX][GameStats.maxY];
        for (int x = 0; x < Viewport.maxX; x++) {
            for (int y = 0; y < Viewport.maxY; y++) {
                String color = colorLandscape(x, y);
                landscape[x][y] = color;
            }
        }
        return landscape;
    }

    private String colorLandscape(int x, int y) {
        String color = GameStats.colorMap[x][y];
        return color;
    }



    void setViewport(Coordinate start) {
        for (int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                int selectionX = x + start.x;
                int selectionY = y + start.y;
                String s;
                Bounds bounds = new Bounds( selectionX, selectionY);
                if (bounds.isWithin()) {
                    s = GameStats.colorMap[selectionX][selectionY];
                    gameMap.colorMapSelection[x][y] = s;
                } else {
                    System.out.println("erroneous numbers!!!!");
                }
            }
        }
    }

    public String[][] getLandscape() {
        if(landscape[0][0].equals("")){
            System.out.println("Error an empty landscape");
        }
        return landscape;
    }

    Coordinate getNewTopLeftCorner(Coordinate f) {
        int x;
        int y;
        //value for x
        if ((f.x + diffX) >= GameStats.maxX - maxX) {
            x = f.x;
        } else if ((f.x + diffX) < START.x) {
            x = f.x;
        } else {
            x = (f.x + diffX);
        }
        //value for y
        if ((f.y + diffY) >= GameStats.maxY - maxY) {
            y = f.y;
        } else if ((f.y + diffY) < START.y) {
            y = f.y;
        } else {
            y = (f.y + diffY);
        }

        resetDifference();
        return new Coordinate(x, y);
    }
}
