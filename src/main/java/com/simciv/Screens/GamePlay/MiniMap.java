package com.simciv.Screens.GamePlay;

import javafx.scene.layout.GridPane;

import static com.simciv.GameStats.*;
import static com.simciv.GameStats.maxY;

class MiniMap extends GridPane {

    MiniMap(String mapSize) {

        switch(mapSize) {
            case "Small" :
                maxX = 50;
                maxY = 32;
                break;
            case "Medium" :
                maxX = 64;
                maxY = 40;
                break;
            case "Large" :
                maxX = 80;
                maxY = 48;
                break;
            case "Huge" :
                maxX = 90;
                maxY = 64;
                break;
            default :
                maxX = 64;
                maxY = 40;
        }
        makeMap(maxX, maxY);
    }

    void makeMap(int maxX, int maxY) {
        Tile[][] tilegrid = new Tile[maxX][maxY];
        init(maxX, maxY, tilegrid);

        setVgap(0);
        setHgap(0);

        make(maxX, maxY, tilegrid);
    }

    private void make(int maxX, int maxY, Tile[][] tilegrid) {
        for(int x = 0; x < maxX ; x++ ){
            for (int y = 0; y < maxY ; y++) {
                tilegrid[x][y].label.setStyle("-fx-background-color: black;");
                GridPane.setConstraints(tilegrid[x][y].label, x, y);
                getChildren().add(tilegrid[x][y].label);
            }
        }
    }

    private void init(int maxX, int maxY, Tile[][] tilegrid) {
        for(int x = 0; x < maxX ; x++ ){
            for (int y = 0; y < maxY ; y++) {
                tilegrid[x][y] = new Tile(1);
            }
        }
    }

    GridPane get(){
        return this;
    }
}
