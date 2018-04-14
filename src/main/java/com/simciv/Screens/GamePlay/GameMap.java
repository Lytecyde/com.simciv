package com.simciv.Screens.GamePlay;

import com.simciv.Graphics.Colors;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


public class GameMap extends GridPane {
    private int maxX = 15;
    private int maxY = 12;
    private Tile[][] visiblegrid = new Tile[maxX][maxY];

    public void make() {
        this.setMaxSize(600,400);
        init();
        makeGrid();
    }

    private void makeGrid() {
        for(int x = 0; x < maxX ; x++ ){
            for (int y = 0; y < maxY ; y++) {
                Label tile = visiblegrid[x][y].label;
                tile.setStyle(Colors.incognita);
                tile.setMinSize(35,35);
                setConstraints(tile, x, y);
                getChildren().add(tile);
            }
        }
    }

    private void init() {
        for(int x = 0; x < maxX ; x++ ){
            for (int y = 0; y < maxY ; y++) {
                visiblegrid[x][y] = new Tile(1);
            }
        }
    }
    public GridPane getMap(){
        return this;
    }
}
