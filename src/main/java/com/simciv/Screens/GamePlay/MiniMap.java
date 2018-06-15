package com.simciv.Screens.GamePlay;

import javafx.scene.layout.GridPane;

import static com.simciv.GameStats.*;

class MiniMap extends GridPane {
    private String[][] cm;
    private int maxX, maxY;
    MiniMap(String mapSize) {
        cm = colorMap;
        selectMapSize(mapSize);
        makeMap();
    }
    /*
    TODO:reduce redundancy
     */
    private void selectMapSize(String mapSize) {
        switch(mapSize) {
            case "Small" :
                this.maxX = 50;
                this.maxY = 32;
                break;
            case "Medium" :
                this.maxX = 64;
                this.maxY = 40;
                break;
            case "Large" :
                this.maxX = 72;
                this.maxY = 48;
                break;
            case "Huge" :
                this.maxX = 90;
                this.maxY = 64;
                break;
            default :
                this.maxX = 64;
                this.maxY = 40;
        }
    }

    void makeMap() {
        Tile[][] tilegrid = new Tile[this.maxX][this.maxY];
        init(this.maxX, this.maxY, tilegrid);

        setVgap(0);
        setHgap(0);

        make(maxX, maxY, tilegrid);
    }

    private void make(int maxX, int maxY, Tile[][] tilegrid) {
        for(int x = 0; x < maxX ; x++ ){
            for (int y = 0; y < maxY ; y++) {

                String color = cm[x][y];
                System.out.println("value of tilegrid element" +
                        x+
                        "  "+
                        y+
                        cm[x][y]);
                tilegrid[x][y].label.setStyle(color);
                tilegrid[x][y].label.setVisible(true);
                tilegrid[x][y].label.setMinSize(2,2);
                tilegrid[x][y].label.setMaxSize(2,2);
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

    public String[][] getColorMap(){
        return cm;
    }

    GridPane get(){
        return this;
    }
}
