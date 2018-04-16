package com.simciv.Screens.GamePlay;

import com.simciv.Coordinates;
import com.simciv.GameStats;
import com.simciv.Graphics.Colors;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


public class GameMap extends GridPane {
    private static int maxX = 15;
    private static int maxY = 12;
    private static Tile[][] visiblegrid = new Tile[maxX][maxY];
    private Tile[][] worldMap = new Tile[GameStats.maxX][GameStats.maxY];
    private String[][] colorMap = new String[GameStats.maxX][GameStats.maxY];
    private String[][] colorMapSelection = new String[maxX][maxY];
    private Coordinates focus = new Coordinates(7,5);
    public int dx = 0;
    public int dy = 0;
    public GameMap() {
        setFocusTraversable(true);
        this.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    dy = -1;
                    break;
                case DOWN:
                    dy = +1;
                    break;
                case LEFT:
                    dx = -1;
                    break;
                case RIGHT:
                    dx = +1;
                    break;
            }
            Coordinates newFocus = getNewFocus(focus);
            moveFocusTile(focus, newFocus);
        });
    }

    public Coordinates getNewFocus(Coordinates f) {
        int x = (f.x + dx) >= 15 ? 14 :
                (f.x + dx) < 0 ? 0 : (f.x + dx) ;
        int y = (f.y + dy) >= 12 ? 11 :
                (f.y + dy) < 0 ? 0 : (f.y + dy);
        reset();
        return new Coordinates (x, y);
    }

    public void make() {
        System.out.println("gamemap");
        setMaxSize(600,400);

        worldMap = initWorldMap();
        makeWorldMap(worldMap);

        setColorMapSelection(focus);

        init();
        makeGrid();
    }

    private Tile[][] initWorldMap() {
        System.out.println("world map init");
        for(int x = 0; x < GameStats.maxX ; x++ ){
            for (int y = 0; y < GameStats.maxY ; y++) {
                worldMap[x][y] = new Tile(35);
            }
        }
        return worldMap;
    }

    private Tile[][] makeWorldMap(Tile[][] worldMap) {
        colorMap = makeColorMap();
        for(int x = 0; x < GameStats.maxX ; x++ ) {
            for (int y = 0; y < GameStats.maxY ; y++) {
                Label tile = worldMap[x][y].label;
                tile.setStyle(colorMap[x][y]);
                tile.setMinSize(35,35);
                worldMap[x][y].label = tile;
            }
        }
        return worldMap;
    }

    private String[][] makeColorMap() {
        for(int x = 0; x < GameStats.maxX ; x++ ) {
            for (int y = 0; y < GameStats.maxY ; y++) {
                System.out.println(getRandomLandIndex());
                colorMap[x][y] = Colors.lands[getRandomLandIndex()];
            }
        }
        return colorMap;
    }

    private int getRandomLandIndex() {
        int r = (int)Math.floor(Math.random() * (Colors.lands.length - 2)) + 2;
        System.out.println(r);
        return r;
    }

    private void setColorMapSelection(Coordinates focus) {
        Coordinates start = new Coordinates(focus.x - 7 % GameStats.maxX, focus.y - 5 % GameStats.maxY);
        for(int x = start.x; x < start.x + maxX ; x++ ){
            for (int y = start.y; y < start.y + maxY ; y++) {
               colorMapSelection[x - start.x][y - start.y ] = colorMap[x][y];
            }
        }
    }

    private void makeGrid() {
        for(int x = 0; x < maxX ; x++ ){
            for (int y = 0; y < maxY ; y++) {
                Label tile = visiblegrid[x][y].label;
                tile.setStyle(colorMapSelection[x][y]);
                tile.setMinSize(35,35);
                setConstraints(tile, x, y);
                getChildren().add(tile);
            }
        }
        setFocusTile();
    }

    private void setFocusTile() {
        int x = 7;
        int y = 5;
        visiblegrid[x][y].label.setStyle(colorMap[x][y] + "-fx-border-color: white;");
    }

    public void moveFocusTile(Coordinates oldFocus,Coordinates newFocus) {
        visiblegrid[oldFocus.x][oldFocus.y].label.setStyle(
            colorMap[oldFocus.x][oldFocus.y] + "-fx-border-color: transparent;");
        visiblegrid[newFocus.x][newFocus.y].label.setStyle(
                colorMap[newFocus.x][newFocus.y] + "-fx-border-color: white;");

        focus = newFocus;
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

    public String[][] getColorMap() {
        return colorMap;
    }

    private void reset() {
        dx = 0;
        dy = 0;
    }
}
