package com.simciv.Screens.GamePlay;


import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;


public class GameMap extends GridPane {
    double height = 20;
    double width = 20;
    public Label tile = new Label("+");

    public void make() {

        this.setMaxSize(600,400);
        this.setMinSize(600,400);

        tile.setMinSize(width, height);
        tile.setMaxSize(width, height);
        tile.setTextAlignment(TextAlignment.JUSTIFY);
        this.setConstraints(tile, 0, 0);
        this.getChildren().add(tile);
    }

    public GridPane getMap(){
        return this;
    }
}
