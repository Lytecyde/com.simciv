package com.simciv;

public class Coordinate {
    public int x;
    public int y;

    public Coordinate(int t, int u) {
        x = t;
        y = u;
    }

    public Coordinate get() {
        return new Coordinate(x, y);
    }
}
