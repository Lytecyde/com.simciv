package com.simciv.Screens.GamePlay;

import com.simciv.Coordinates;
import com.simciv.GameStats;
import com.simciv.Graphics.Colors;

import com.simciv.Icons.Icon;
import com.simciv.Players.Player;
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
    private String[][] visibleMap = new String[GameStats.maxX][GameStats.maxY];
    private String[][] colorMapSelection = new String[maxX][maxY];
    private Coordinates focus = new Coordinates(7, 5);
    public int dx = 0;
    public int dy = 0;
    public int ds = 0;
    public int dz = 0;
    private int tileSize = 35;
    private Coordinates start = new Coordinates(7, 5);
    private final Coordinates CENTER = new Coordinates(7, 5);
    private final Coordinates START = new Coordinates(0, 0);
    private boolean cursorMove = false;
    private int limitY = GameStats.maxY;
    private int limitX = GameStats.maxX;

    public GameMap() {
        setFocusTraversable(true);
        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            int selectionX;
            int selectionY;

            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        if (!cursorMove) {
                            dz = -1;
                        } else {
                            dy = -1;
                        }
                        break;
                    case DOWN:
                        if (!cursorMove) {
                            dz = 1;
                        } else {
                            dy = 1;
                        }
                        break;
                    case LEFT:
                        if (!cursorMove) {
                            ds = -1;
                        } else {
                            dx = -1;
                        }
                        break;
                    case RIGHT:
                        if (!cursorMove) {
                            ds = 1;
                        } else {
                            dx = 1;
                        }
                        break;
                    case C:
                        focus = CENTER;
                        break;
                    case SHIFT:
                        cursorMove = !cursorMove;
                        break;
                }
                Coordinates newFocus = GameMap.this.getNewFocus(focus);
                if (cursorMove) {
                    GameMap.this.moveFocusTile(focus, newFocus);
                    GameMap.this.setFocusTile(focus);
                } else {
                    selectionX = start.x;
                    selectionY = start.y;

                    if (isWithinBounds(selectionX, selectionY)) {
                        System.out.println("in bounds");
                        start = GameMap.this.getNewSelectionStart(start);
                        GameMap.this.setVisibleMapSelection(start);
                        GameMap.this.init();
                        GameMap.this.makeGrid();
                        GameMap.this.showWorldMap();//
                        GameMap.this.setFocusTile(start);
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
                        GameMap.this.init();
                        GameMap.this.makeGrid();
                    }
                }
                GameMap.this.setReturnFocuseable();
            }
        });
    }

    public void setReturnFocuseable() {
        setFocusTraversable(true);
    }

    private boolean isWithinBounds(int selectionX, int selectionY) {
        return isWithinUpperBound(selectionX, selectionY) &&
                isWithinLowerBound(selectionX, selectionY);
    }

    private boolean isWithinUpperBound(int sx, int sy) {
        System.out.println("sx " + sx + "sy" + sy);
        return (sx < limitX) &&
                (sy < limitY);
    }

    private boolean isWithinLowerBound(int sx, int sy) {
        return sx >= 7 && sy >= 5;
    }

    public Coordinates getNewFocus(Coordinates f) {
        int x = (f.x + dx) >= 15 ? 14 :
                (f.x + dx) < 0 ? 0 :
                        (f.x + dx);
        int y = (f.y + dy) >= 12 ? 11 :
                (f.y + dy) < 0 ? 0 :
                        (f.y + dy);
        resetDifference();
        return new Coordinates(x, y);
    }

    public Coordinates getNewSelectionStart(Coordinates f) {
        //TODO check where the limit really is ie why 3
        int x = (f.x + ds) >= GameStats.maxX - maxX - 3 ? GameStats.maxX - maxX - 4 :
                (f.x + ds) < 7 ? 7 :
                        (f.x + ds);
        int y = (f.y + dz) >= GameStats.maxY - maxY - 3 ? GameStats.maxY - maxY - 4 :
                (f.y + dz) < 5 ? 5 :
                        (f.y + dz);
        ds = 0;
        dz = 0;
        return new Coordinates(x, y);
    }

    public void make() {
        setMaxSize(600, 400);

        worldMap = initWorldMap();
        worldMap = makeWorldMap();

        setVisibleMapSelection(focus);

        init();
        showWorldMap();
        setFocusTile(CENTER);
    }

    private Tile[][] initWorldMap() {
        System.out.println("world map init");
        for (int x = 0; x < GameStats.maxX; x++) {
            for (int y = 0; y < GameStats.maxY; y++) {
                worldMap[x][y] = new Tile(tileSize);
            }
        }
        return worldMap;
    }

    private Tile[][] makeWorldMap() {
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
        return worldMap;
    }

    private String[][] makeUnitsMap() {
        String[][] units = initUnitsMap();
        GameStats.makePlayers();
        LinkedList<Player> list = new LinkedList<>();
        for (int i = 0; i < GameStats.players.list.size(); i++) {
            list.add(GameStats.players.list.get(i));
        }

        for (Player p : list) {
            units[p.startLocation.x][p.startLocation.y] = Icon.unit[0];
        }
        return units;
    }

    private String[][] initUnitsMap() {
        String[][] units = new String[GameStats.maxX][GameStats.maxY];
        for (int x = 0; x < GameStats.maxX; x++) {
            for (int y = 0; y < GameStats.maxY; y++) {
                units[x][y] = " ";
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
        return r;
    }

    private void setVisibleMapSelection(Coordinates start) {
        for (int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                int selectionX = x + start.x;
                int selectionY = y + start.y;
                System.out.println(selectionX + " , " + selectionY);
                String s;
                if (isWithinLowerBound(selectionX, selectionY) &&
                        isWithinUpperBound(selectionX, selectionY)
                        ) {
                    s = visibleMap[selectionX][selectionY];
                    colorMapSelection[x][y] = s;
                } else {
                    System.out.println("erroneous numbers!!!!");
                    s = "";
                }
            }
        }
    }

    private void init() {
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

    private void showWorldMap() {
        for (int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                Label tile = worldMap[start.x + x][start.y + y].label;
                setConstraints(tile, x, y);
                getChildren().add(tile);
            }
        }
    }

    private void setFocusTile(Coordinates c) {
        visiblegrid[c.x][c.y].label.setStyle(colorMapSelection[c.x][c.y] + "-fx-border-color: white;");
    }

    public void moveFocusTile(Coordinates oldFocus, Coordinates newFocus) {
        visiblegrid[oldFocus.x][oldFocus.y].label.setStyle(
                visibleMap[oldFocus.x][oldFocus.y] +
                        "-fx-border-color: transparent;"
        );
        visiblegrid[newFocus.x][newFocus.y].label.setStyle(
                visibleMap[newFocus.x][newFocus.y] +
                        "-fx-border-color: white;"
        );
        focus = newFocus;
    }

    public GridPane getMap() {
        return this;
    }

    public String[][] getVisibleMap() {
        return visibleMap;
    }

    private void resetDifference() {
        dx = 0;
        dy = 0;
    }
}