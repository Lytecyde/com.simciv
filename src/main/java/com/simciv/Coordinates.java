package com.simciv;

public class Coordinates {
    public int x;
    public int y;

    public Coordinates(int t, int u) {
        x = t;
        y = u;
    }

    public Coordinates get() {
        return new Coordinates(x, y);
    }
}
