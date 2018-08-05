package com.simciv.Screens.GamePlay;

import com.simciv.*;
import com.simciv.Icons.Icon;
import com.simciv.Players.Player;
import com.simciv.Players.Players;
import com.simciv.Screens.CityManager.Units;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.LinkedList;

public class GameMap extends GridPane {

    private static Tile[][] tiling =
            new Tile[GameStats.maxX][GameStats.maxY];
    private final CursorFrame cursorFrame = new CursorFrame(this);
    private Tile[][] worldMap = new Tile[GameStats.maxX][GameStats.maxY];
    String[][] colorMapSelection = new String[GameStats.maxX][GameStats.maxY];
    private Coordinate diff = new Coordinate(0, 0);
    Viewport viewport;
    private int tileSize = 35;
    private Coordinate topLeftCorner =
            new Coordinate(GameStats.START.x, GameStats.START.y);
    private Coordinate focus =
            new Coordinate(GameStats.CENTER.x, GameStats.CENTER.y);
    private boolean mapShift = false;
    private int limitY = GameStats.maxY;
    private int limitX = GameStats.maxX;
    private String[][] improvementsMap;
    private String[][] unitsMap;
    private String[][] citiesMap;

    public GameMap() {
        initViewport();
        setFocusTraversable(true);
        triggerKeyHandler();
        initMaps();
        drawAll();
        make();
    }

    private void drawAll() {
        drawImprovements();
        drawUnits();
        drawCities();
    }

    private void initViewport() {
        viewport = new Viewport(this);
    }

    private void initMaps() {
        improvementsMap = new String[GameStats.maxX][GameStats.maxY];
        unitsMap = new String[GameStats.maxX][GameStats.maxY];
        citiesMap = new String[GameStats.maxX][GameStats.maxY];
    }

    static Tile[][] getTiling() {
        return tiling;
    }

    private void triggerKeyHandler() {
        new KeyHandlerK(this);
    }

    private void drawUnits() {
        Units units = new Units();
        new Draw(units, unitsMap );
    }

    private void drawImprovements() {
        Improvements improvements = new Improvements();
        new Draw(improvements, improvementsMap);
    }

    private void drawCities() {
        Cities cities = new Cities();
        new Draw(cities, citiesMap);
    }


    private void make() {
        setMaxSize(600, 400);

        worldMap = initWorldMap();
        makeWorldMap(worldMap);

        viewport.setViewport(focus);

        initTiling();
        makeGrid();
        cursorFrame.setFocusTile();
    }

    private Tile[][] initWorldMap() {
        for (int x = 0; x < GameStats.maxX; x++) {
            for (int y = 0; y < GameStats.maxY; y++) {
                worldMap[x][y] = new Tile(tileSize);
            }
        }
        return worldMap;
    }

    private void makeTile(Tile tile, String unit, int x, int y) {
        Label label = tile.label;
        label.setMinSize(tileSize, tileSize);
        label.setStyle(GameStats.colorMap[x][y]);
        label.setTextFill(Color.BLACK);
        label.setText(improvementsMap[x][y]);
        label.setText(unit);
        tile.label = label;
    }

    private void makeWorldMap(Tile[][] worldMap) {
        String[][] unitsMap = makeUnitsMap();
        for (int x = 0; x < GameStats.maxX; x++) {
            for (int y = 0; y < GameStats.maxY; y++) {
                makeTile(worldMap[x][y], unitsMap[x][y], x, y);
            }
        }
    }

    private String[][] makeUnitsMap() {
        String[][] units = initUnitsMap();
        Players players = new Players();
        LinkedList<Player> list = new LinkedList<>();
        list.addAll(players.list);
        for (Player p : list) {
            Coordinates s = p.getStartLocation();
            int x = s.x;
            int y = s.y;
            int settler = Icon.unitnames.SETTLER.ordinal();
            units[x][y] = Icon.unit[settler];
        }
        return units;
    }

    private String[][] initUnitsMap() {
        String[][] units = new String[GameStats.maxX][GameStats.maxY];
        for (int x = 0; x < GameStats.maxX; x++) {
            for (int y = 0; y < GameStats.maxY; y++) {
                units[x][y] = "";
                System.out.println();
            }
        }
        return units;
    }

    private void initTiling() {
        for (int x = 0; x < GameStats.maxX; x++) {
            for (int y = 0; y < GameStats.maxY; y++) {
                tiling[x][y] = new Tile(1);
            }
        }
    }

    private void makeGrid() {
        for (int x = 0; x < Viewport.maxX; x++) {
            for (int y = 0; y < Viewport.maxY; y++) {
                Label tile = tiling[x][y].label;
                tile.setStyle(colorMapSelection[x][y]);
                tile.setMinSize(tileSize, tileSize);
                setConstraints(tile, x, y);
                getChildren().add(tile);
            }
        }
    }

    public GridPane getMap() {
        return this;
    }
    
    Coordinate getDiff() {
        return diff;
    }

    void setDiff() {
        diff = new Coordinate(0, 0);
    }

    Coordinate getFocus() {
        return focus;
    }

    String[][] getColorMapSelection() {
        return colorMapSelection;
    }

    Viewport getViewport() {
        return viewport;
    }

    void setFocus(Coordinate focus) {
        this.focus = focus;
    }

    class KeyHandlerK {

        KeyHandlerK(GameMap gameMap) {
            gameMap.setOnKeyPressed(new EventHandler<>() {

                int selectionX;
                int selectionY;

                //@Override
                public void handle(KeyEvent event) {
                    switch (event.getCode()) {
                        case UP:
                            diff.y = -1;
                            break;
                        case DOWN:
                            diff.y = 1;
                            break;
                        case LEFT:
                            diff.x = -1;
                            break;
                        case RIGHT:
                            diff.x = 1;
                            break;
                        case C:
                            cursorFrame.removeCursorBox(focus);
                            focus = GameStats.CENTER;
                            break;
                        case SHIFT:
                            mapShift = !mapShift;
                            break;
                    }

                    if (mapShift) {
                        viewport.diffX = diff.x;
                        viewport.diffY = diff.y;
                        System.out.println("moving the map");
                        moveMap();
                    } else {
                        cursorFrame.moveFocusTile(focus);
                        System.out.println("cursor in MOBILE state");
                    }
                }

                private void moveMap() {
                    selectionX = topLeftCorner.x;
                    selectionY = topLeftCorner.y;
                    Bounds bounds = new Bounds( selectionX,
                            selectionY);
                    if (bounds.isWithin()) {
                        System.out.println("in bounds");
                        Coordinate n =
                                GameMap.this.viewport.getNewTopLeftCorner
                                        (topLeftCorner);
                        topLeftCorner = new Coordinate(n.x,
                                n.y);
                        GameMap.this.viewport.setViewport(topLeftCorner);
                        GameMap.this.initTiling();
                        GameMap.this.makeGrid();
                    } else {
                        System.out.println("outside" +
                                selectionX +
                                "   " +
                                selectionY +
                                limitX +
                                "   " +
                                limitY
                        );
                        GameMap.this.viewport.redrawViewPort(topLeftCorner);
                        GameMap.this.initTiling();
                        GameMap.this.makeGrid();
                    }
                }
            });
        }

    }
}
