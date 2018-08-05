package com.simciv.Screens.GamePlay;

import com.simciv.GameStats;

class Bounds {
    private int x, y;

    Bounds( int sx, int sy) {
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
        return sx >= GameStats.START.x && sy >= GameStats.START.y;
    }
}
