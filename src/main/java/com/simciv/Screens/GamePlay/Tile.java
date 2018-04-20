package com.simciv.Screens.GamePlay;

import com.simciv.Coordinates;
import com.simciv.GameStats;
import com.simciv.Graphics.Colors;
import javafx.scene.control.Label;
import com.simciv.Unit;

public class Tile {
    Label label = new Label();
    boolean hasUnit = false;
    public Tile(double size) {
        label.setMinSize(size, size);
        label.setMaxSize(size, size);
        hasUnit = false;
    }

    public Tile(Unit u) {
        Tile withUnit = new Tile(GameStats.tileSize);
        withUnit.hasUnit = true;
        withUnit.label.setText("*");
    }

    public void cursor(Coordinates c) {

    }
}
