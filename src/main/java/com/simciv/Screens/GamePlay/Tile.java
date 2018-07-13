package com.simciv.Screens.GamePlay;

import com.simciv.Coordinate;
import com.simciv.GameStats;
import javafx.scene.control.Label;
import com.simciv.actor.Unit;

public class Tile {
    Label label = new Label();
    private boolean hasUnit = false;
    Tile(double size) {
        label.setMinSize(size, size);
        label.setMaxSize(size, size);
        hasUnit = false;
    }

    public Tile(Unit u) {
        Tile withUnit = new Tile(GameStats.tileSize);
        withUnit.hasUnit = true;
        withUnit.label.setText("*");
    }

    public void cursor(Coordinate c) {

    }
}
