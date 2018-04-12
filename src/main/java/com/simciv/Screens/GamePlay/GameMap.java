package com.simciv.Screens.GamePlay;

import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;


public class GameMap extends GridPane {
    Tile tile = new Tile();

    public void make() {
        this.setMaxSize(600,400);
        this.setMinSize(600,400);

        tile.label.setMinSize(tile.width, tile.height);
        tile.label.setMaxSize(tile.width, tile.height);
        tile.label.setTextAlignment(TextAlignment.JUSTIFY);
        setConstraints(tile.label, 0, 0);
        this.getChildren().add(tile.label);
    }

    public GridPane getMap(){
        return this;
    }
}
