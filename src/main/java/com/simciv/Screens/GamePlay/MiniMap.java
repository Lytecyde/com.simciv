package com.simciv.Screens.GamePlay;

import com.simciv.Graphics.Colors;
import javafx.scene.layout.GridPane;

import static com.simciv.GameStats.*;
import static com.simciv.GameStats.maxY;

class MiniMap extends GridPane {

    MiniMap(String mapSize) {

        selectMapSize(mapSize);
        makeMap(maxX, maxY);
    }

    private void selectMapSize(String mapSize) {
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
                tilegrid[x][y].label.setStyle(Colors.incognita);
                GridPane.setConstraints(tilegrid[x][y].label, x, y);
                getChildren().add(tilegrid[x][y].label);
            }
        }
    }

    private void init(int maxX, int maxY, Tile[][] tilegrid) {
        for(int x = 0; x < maxX ; x++ ){
            for (int y = 0; y < maxY ; y++) {
                tilegrid[x][y] = new Tile(2);
            }
        }
    }

    GridPane get(){
        return this;
    }
}
