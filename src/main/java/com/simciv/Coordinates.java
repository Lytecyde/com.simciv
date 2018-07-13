package com.simciv;

import java.util.LinkedList;

public class Coordinates {
    public LinkedList<Coordinate> list = new LinkedList<>();
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
