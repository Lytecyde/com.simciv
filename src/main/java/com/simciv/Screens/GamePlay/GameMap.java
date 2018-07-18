package com.simciv.Screens.GamePlay;

import com.simciv.*;
import com.simciv.Icons.Icon;
import com.simciv.Players.Player;
import com.simciv.Players.Players;
import com.simciv.Screens.CityManager.Units;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.LinkedList;

public class GameMap extends GridPane {

    private static Tile[][] tiling =
            new Tile[Viewport.maxX][Viewport.maxY];
    private final CursorFrame cursorFrame = new CursorFrame(this);
    private Tile[][] worldMap = new Tile[GameStats.maxX][GameStats.maxY];
    String[][] colorMapSelection = new String[Viewport.maxX][Viewport.maxY];
    private Coordinate diff = new Coordinate(0, 0);
    Viewport viewport;
    private int tileSize = 35;
    private Coordinate topLeftCorner =
            new Coordinate(GameStats.CENTER.x, GameStats.CENTER.y);
    private Coordinate focus =
            new Coordinate(GameStats.CENTER.x, GameStats.CENTER.y);
    private boolean mapShift = false;
    private int limitY = GameStats.maxY;
    private int limitX = GameStats.maxX;
    private String[][] improvementsMap =
            new String[GameStats.maxX][GameStats.maxY];

    public GameMap() {
        new Viewport(this);
        setFocusTraversable(true);
        triggerKeyHandler();
        drawCities(this);
        drawImprovements(this);
        drawUnits(this);
        make();
    }

    public static Tile[][] getTiling() {
        return tiling;
    }

    private void triggerKeyHandler() {
        new KeyHandlerK(this);
    }

    private void drawUnits(GameMap gameMap) {
        Units units = new Units();
        new Draw(units, gameMap);
    }

    private void drawImprovements(GameMap gameMap) {
        Improvements improvements = new Improvements();
        new Draw(improvements, gameMap);
    }

    private void drawCities(GameMap gameMap) {
        Cities cities = new Cities();
        new Draw(cities, gameMap);
    }

    private Coordinate getNewTopLeftCorner(Coordinate f) {
        int x = (f.x + viewport.diffX) >=
                GameStats.maxX - Viewport.maxX ? GameStats.maxX - Viewport
                .maxX :
                (f.x + viewport.diffX) < GameStats.CENTER.x ? GameStats
                        .CENTER.x :
                        (f.x + viewport.diffX);
        int y = (f.y + viewport.diffY) >=
                GameStats.maxY - Viewport.maxY ? GameStats.maxY - Viewport
                .maxY :
                (f.y + viewport.diffY) < GameStats.CENTER.y ? GameStats
                        .CENTER.y :
                        (f.y + viewport.diffY);
        viewport.resetDifference();
        return new Coordinate(x, y);
    }


    public void make() {
        setMaxSize(600, 400);

        worldMap = initWorldMap();
        makeWorldMap(worldMap);

        setViewport(focus);

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
                System.out.println("");
            }
        }
        return units;
    }

    private void setViewport(Coordinate start) {
        for (int x = 0; x < Viewport.maxX; x++) {
            for (int y = 0; y < Viewport.maxY; y++) {
                int selectionX = x + start.x;
                int selectionY = y + start.y;
                String s;
                Bounds bounds = new Bounds(this, selectionX, selectionY);
                if (bounds.isWithin()) {
                    s = GameStats.colorMap[selectionX][selectionY];
                    colorMapSelection[x][y] = s;
                } else {
                    System.out.println("erroneous numbers!!!!");
                }
            }
        }
    }

    private void initTiling() {
        for (int x = 0; x < Viewport.maxX; x++) {
            for (int y = 0; y < Viewport.maxY; y++) {
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

    /*public static String[][] getVisibleMap() {
        return visibleMap;
    }*/
    public static void setTiling(Tile[][] tiling) {
        GameMap.tiling = tiling;
    }
    
    public Coordinate getDiff() {
        return diff;
    }

    public void setDiff() {
        diff = new Coordinate(0, 0);
    }

    public Coordinate getFocus() {
        return focus;
    }

    public String[][] getColorMapSelection() {
        return colorMapSelection;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public void setFocus(Coordinate focus) {
        this.focus = focus;
    }

    class KeyHandlerK {

        KeyHandlerK(GameMap gameMap) {
            gameMap.setOnKeyPressed(new EventHandler<KeyEvent>() {
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
                    Bounds bounds = new Bounds(GameMap.this, selectionX,
                            selectionY);
                    if (bounds.isWithin()) {
                        System.out.println("in bounds");
                        Coordinate n =
                                GameMap.this.getNewTopLeftCorner(topLeftCorner);
                        topLeftCorner = new Coordinate(n.x,
                                n.y);
                        GameMap.this.setViewport(topLeftCorner);
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
