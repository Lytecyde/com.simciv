package com.simciv.Screens.GamePlay;

import com.simciv.GameStats;

class Bounds {
    private GameMap gameMap;
    private int x, y;

    Bounds(GameMap gameMap, int sx, int sy) {
        this.gameMap = gameMap;
        x = sx;
        y = sy;
    }

    boolean isWithin() {
        return isWithinUpper(x,y) &&
                isWithinLower(x,y);
    }

    private boolean isWithinUpper(int sx, int sy) {
        return (sx < GameStats.maxX ) &&
                (sy < GameStats.maxY);
    }

    private boolean isWithinLower(int sx, int sy) {
        return sx >= GameStats.CENTER.x && sy >= GameStats.CENTER.y;
    }
}
