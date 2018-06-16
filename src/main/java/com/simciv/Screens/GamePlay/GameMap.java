package com.simciv.Screens.GamePlay;

import com.simciv.Cities;
import com.simciv.Coordinates;
import com.simciv.GameStats;
import com.simciv.Graphics.Colors;

import com.simciv.Improvements;
import com.simciv.Screens.CityManager.Units;
import com.simciv.Icons.Icon;
import com.simciv.Players.Player;
import com.simciv.Players.Players;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.LinkedList;

public class GameMap extends GridPane {
    private static int maxX = 15;
    private static int maxY = 12;
    private static Tile[][] visiblegrid = new Tile[maxX][maxY];
    private Tile[][] worldMap = new Tile[GameStats.maxX][GameStats.maxY];
    private static String[][] visibleMap = new String[GameStats.maxX][GameStats.maxY];
    private String[][] colorMapSelection = new String[maxX][maxY];
    private int dx = 0;
    private int dy = 0;
    private int ds = 0;
    private int di = 0;
    private int tileSize = 35;
    private final Coordinates CENTER = new Coordinates(7, 5);
    private Coordinates start = new Coordinates(CENTER.x, CENTER.y);
    private Coordinates focus = new Coordinates(CENTER.x, CENTER.y);
    private final Coordinates START = new Coordinates(0, 0);
    private boolean mapShift = false;
    private int limitY = GameStats.maxY;
    private int limitX = GameStats.maxX;
    public GameMap() {
        setFocusTraversable(true);
        new KeyHandlerK(this);
        drawCities(this);
        drawImprovements(this);
        drawUnits(this);

    }

    public void setReturnFocuseable() {
        setFocusTraversable(true);
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

    private Coordinates getNewFocus(Coordinates f) {
        int x = (f.x + dx) >= maxX ? maxX - 1 :
                (f.x + dx) < START.x ? START.x :
                        (f.x + dx);
        int y = (f.y + dy) >= maxY ? maxY - 1 :
                (f.y + dy) < START.y ? START.y :
                        (f.y + dy);
        resetDifference();
        return new Coordinates(x, y);
    }

    private Coordinates getNewSelectionStart(Coordinates f) {
        int x = (f.x + ds) >=
                GameStats.maxX - maxX ? GameStats.maxX - maxX  :
                (f.x + ds) < CENTER.x ? CENTER.x :
                        (f.x + ds);
        int y = (f.y + di) >=
                GameStats.maxY - maxY ? GameStats.maxY - maxY  :
                (f.y + di) < CENTER.y ? CENTER.y :
                        (f.y + di);
        ds = 0;
        di = 0;
        return new Coordinates(x, y);
    }


    public void make() {
        setMaxSize(600, 400);

        worldMap = initWorldMap();
        makeWorldMap(worldMap);

        setVisibleMapSelection(focus);

        initVisibleGrid();
        makeGrid();
        setFocusTile();
    }

    private Tile[][] initWorldMap() {
        System.out.println("world map initVisibleGrid");
        for (int x = 0; x < GameStats.maxX; x++) {
            for (int y = 0; y < GameStats.maxY; y++) {
                worldMap[x][y] = new Tile(tileSize);
            }
        }
        return worldMap;
    }

    private void makeWorldMap(Tile[][] worldMap) {
        visibleMap = makeVisibleMap();
        String[][] unitsMap = makeUnitsMap();
        for (int x = 0; x < GameStats.maxX; x++) {
            for (int y = 0; y < GameStats.maxY; y++) {
                Label tile = worldMap[x][y].label;
                tile.setMinSize(tileSize, tileSize);
                tile.setStyle(visibleMap[x][y]);
                tile.setTextFill(Color.BLACK);
                tile.setText(unitsMap[x][y]);
                worldMap[x][y].label = tile;
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
            units[x][y] = Icon.unit[0];
        }

        return units;
    }

    private String[][] initUnitsMap() {
        String[][] units = new String[GameStats.maxX][GameStats.maxY];
        for (int x = 0; x < GameStats.maxX; x++) {
            for (int y = 0; y < GameStats.maxY; y++) {
                System.out.println(getRandomLandIndex());
            }
        }
        return units;
    }

    private String[][] makeVisibleMap() {
        for (int x = 0; x < GameStats.maxX; x++) {
            for (int y = 0; y < GameStats.maxY; y++) {
                visibleMap[x][y] = Colors.lands[getRandomLandIndex()];
            }
        }
        return visibleMap;
    }

    private int getRandomLandIndex() {
        int r = (int) Math.floor(Math.random() * (Colors.lands.length - 2)) + 2;
        System.out.println(r);
        return r;
    }

    private void setVisibleMapSelection(Coordinates start) {
        for (int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                int selectionX = x + start.x;
                int selectionY = y + start.y;
                //System.out.println(selectionX + " , " + selectionY);
                String s;
                Bounds bounds = new Bounds(selectionX, selectionY);
                if (bounds.isWithin()){
                    s = visibleMap[selectionX][selectionY];
                    colorMapSelection[x][y] = s;
                } else {
                    System.out.println("erroneous numbers!!!!");
                }
            }
        }
    }

    private void initVisibleGrid() {
        for (int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                visiblegrid[x][y] = new Tile(1);
            }
        }
    }

    private void makeGrid() {
        for (int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                Label tile = visiblegrid[x][y].label;
                tile.setStyle(colorMapSelection[x][y]);
                tile.setMinSize(tileSize, tileSize);
                setConstraints(tile, x, y);
                getChildren().add(tile);
            }
        }
    }

    private void setFocusTile() {
        int x = 7;
        int y = 5;
        visiblegrid[x][y].label.setStyle(colorMapSelection[x][y] +
                "-fx-border-color: white;");
    }

    private void moveFocusTile(Coordinates oldFocus) {
        visiblegrid[oldFocus.x][oldFocus.y].label.setStyle(
                visibleMap[oldFocus.x + CENTER.x][oldFocus.y + CENTER.y] +
                        "-fx-border-color: transparent;"
        );
        Coordinates newFocus = getAdjustedCoordinates();
        visiblegrid[newFocus.x][newFocus.y].label.setStyle(
                visibleMap[newFocus.x + CENTER.x][newFocus.y + CENTER.y]
                        + "-fx-border-color: white;"
        );
        focus = newFocus;
    }

    private Coordinates getAdjustedCoordinates() {
        return GameMap.this.getNewFocus(focus);
    }

    public GridPane getMap() {
        return this;
    }

    public static String[][] getVisibleMap() {
        return visibleMap;
    }

    private void resetDifference() {
        dx = 0;
        dy = 0;
    }

    class Bounds {
        private int x, y;

        Bounds(int sx, int sy) {
            x = sx;
            y = sy;
        }

        private boolean isWithin() {
            return isWithinUpper(x,y) &&
                    isWithinLower(x,y);
        }

        private boolean isWithinUpper(int sx, int sy) {
            return (sx < limitX ) &&
                    (sy < limitY);
        }

        private boolean isWithinLower(int sx, int sy) {
            return sx >= CENTER.x && sy >= CENTER.y;
        }
    }

    class KeyHandlerK {

        KeyHandlerK(GameMap gameMap){
               gameMap.setOnKeyPressed(new EventHandler<KeyEvent>() {
                int selectionX;
                int selectionY;

                   //@Override
                public void handle(KeyEvent event) {
                    switch (event.getCode()) {
                        case UP:
                                dy = -1;
                            break;
                        case DOWN:
                                dy = 1;
                            break;
                        case LEFT:
                                dx = -1;
                            break;
                        case RIGHT:
                                dx = 1;
                            break;
                        case C:
                            focus = CENTER;
                            break;
                        case SHIFT:
                            mapShift = !mapShift;

                            break;
                    }

                    if(mapShift){
                        ds = dx;
                        di = dy;
                        System.out.println("moving the map");
                        moveMap();
                    } else {
                        GameMap.this.moveFocusTile(focus);
                        System.out.println("cursor in MOBILE state");
                    }
                }

                private void moveMap() {
                    selectionX = start.x;
                    selectionY = start.y;
                    Bounds bounds = new Bounds(selectionX, selectionY);
                    if (bounds.isWithin()) {
                       System.out.println("in bounds");
                       Coordinates n =
                               GameMap.this.getNewSelectionStart(start);
                       start = new Coordinates(n.x ,
                               n.y );
                       GameMap.this.setVisibleMapSelection(start);
                       GameMap.this.initVisibleGrid();
                       GameMap.this.makeGrid();
                       //GameMap.this.setFocusTile();
                    } else {
                           System.out.println("outside" +
                               selectionX +
                               "   " +
                               selectionY +
                               limitX +
                               "   " +
                               limitY
                       );
                       GameMap.this.setVisibleMapSelection(start);
                       GameMap.this.initVisibleGrid();
                       GameMap.this.makeGrid();
                   }
               }
           });
        }

    }
}
