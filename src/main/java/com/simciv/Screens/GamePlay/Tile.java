package com.simciv.Screens.GamePlay;

import javafx.scene.control.Label;

class Tile {
    Label label = new Label();

    Tile(double size) {
        label.setMinSize(size, size);
        label.setMaxSize(size, size);
    }

}
