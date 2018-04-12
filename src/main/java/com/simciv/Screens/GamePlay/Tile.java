package com.simciv.Screens.GamePlay;

import javafx.scene.control.Label;

class Tile {
    double height = 20;
    double width = 20;
    Label label = new Label();

    Tile(double size) {
        label.setMinSize(size, size);
        label.setMaxSize(size, size);
    }

}
