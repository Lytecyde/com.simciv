package com.simciv.Screens.GamePlay;

import com.simciv.Coordinates;
import com.simciv.GameStats;
import com.simciv.Graphics.Colors;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;


public class GameMap extends GridPane {
    private static int maxX = 15;
    private static int maxY = 12;
    private static Tile[][] visiblegrid = new Tile[maxX][maxY];
    private Tile[][] worldMap = new Tile[GameStats.maxX][GameStats.maxY];
    private String[][] colorMap = new String[GameStats.maxX][GameStats.maxY];
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
    private Coordinates oldMapCenter = CENTER;
    private int limitY = GameStats.maxY ;
    private int limitX = GameStats.maxX ;
    public GameMap() {
        setFocusTraversable(true);
        System.out.println("lim" + limitX + "   " + limitY);
        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            int selectionX;
            int selectionY;

            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        if (!cursorMove) {
                            dz = -1;
                        }
                        else{
                            dy = -1;
                        }
                        break;
                    case DOWN:
                        if (!cursorMove) {
                            dz = 1;
                        }
                        else {
                            dy = 1;
                        }
                        break;
                    case LEFT:
                        if (!cursorMove) {
                            ds = -1;
                        }
                        else {
                            dx = -1;
                        }
                        break;
                    case RIGHT:
                        if (!cursorMove) {
                            ds = 1;
                        }
                        else {
                            dx = 1;
                        }
                        break;
                    case C:
                        focus = new Coordinates(7, 5);
                        break;
                    case SHIFT:
                        cursorMove = !cursorMove;
                        break;
                }
                Coordinates newFocus = GameMap.this.getNewFocus(focus);
                if (cursorMove) {
                    GameMap.this.moveFocusTile(focus, newFocus);
                    GameMap.this.setFocusTile();
                } else {
                    selectionX = start.x;
                    selectionY = start.y;

                    System.out.println(selectionX + " , " + selectionY);

                    if (isWithinBounds(selectionX, selectionY)) {
                        System.out.println("in bounds");
                        oldMapCenter = start;
                        start = GameMap.this.getNewSelectionStart(start);
                        GameMap.this.setColorMapSelection(start);
                        GameMap.this.init();
                        GameMap.this.makeGrid();
                        GameMap.this.setFocusTile();
                    } else {
                        System.out.println("outside" +
                                selectionX +
                                "   " +
                                selectionY +
                                limitX +
                                "   " +
                                limitY
                        );
                        GameMap.this.setColorMapSelection(start);
                        GameMap.this.init();
                        GameMap.this.makeGrid();
                    }
                }
            }
        });
    }

    private boolean isWithinBounds(int selectionX, int selectionY) {
        return isWithinUpperBound(selectionX, selectionY) &&
                isWithinLowerBound(selectionX, selectionY);
    }

    private boolean isWithinUpperBound(int sx, int sy) {
        System.out.println("sx " + sx + "sy" + sy);
        return (sx < limitX ) &&
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
        //TODO check where the limit really is
        int x = (f.x + ds) >= GameStats.maxX - maxX -3? GameStats.maxX - maxX - 4 :
                (f.x + ds) < 7 ? 7 :
                        (f.x + ds);
        int y = (f.y + dz) >= GameStats.maxY - maxY -3 ? GameStats.maxY - maxY - 4 :
                (f.y + dz) < 5 ? 5 :
                        (f.y + dz);
        ds = 0;
        dz = 0;
        return new Coordinates(x, y);
    }



    public void make() {
        setMaxSize(600, 400);

        worldMap = initWorldMap();
        makeWorldMap(worldMap);

        setColorMapSelection(focus);

        init();
        makeGrid();
        setFocusTile();
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

    private void initColorMap() {
        System.out.println("color map init");
        for (int x = 0; x < GameStats.maxX; x++) {
            for (int y = 0; y < GameStats.maxY; y++) {
                colorMap[x][y] = "";
            }
        }
    }

    private void initColorMapSelection() {
        System.out.println("color map sel init");
        for (int x = 0; x < GameStats.maxX; x++) {
            for (int y = 0; y < GameStats.maxY; y++) {
                colorMapSelection[x][y] = "";
            }
        }
    }

    private Tile[][] makeWorldMap(Tile[][] worldMap) {
        colorMap = makeColorMap();
        for (int x = 0; x < GameStats.maxX; x++) {
            for (int y = 0; y < GameStats.maxY; y++) {
                Label tile = worldMap[x][y].label;
                tile.setStyle(colorMap[x][y]);
                tile.setMinSize(tileSize, tileSize);
                worldMap[x][y].label = tile;
            }
        }
        return worldMap;
    }

    private String[][] makeColorMap() {
        for (int x = 0; x < GameStats.maxX; x++) {
            for (int y = 0; y < GameStats.maxY; y++) {
                System.out.println(getRandomLandIndex());
                colorMap[x][y] = Colors.lands[getRandomLandIndex()];
            }
        }
        return colorMap;
    }

    private int getRandomLandIndex() {
        int r = (int) Math.floor(Math.random() * (Colors.lands.length - 2)) + 2;
        System.out.println(r);
        return r;
    }

    private void setColorMapSelection(Coordinates start) {
        for (int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                int selectionX = x + start.x;
                int selectionY = y + start.y;
                System.out.println(selectionX + " , " + selectionY);
                String s;
                if (isWithinLowerBound(selectionX,selectionY) &&
                    isWithinUpperBound(selectionX,selectionY)
                        ){
                    s = colorMap[selectionX][selectionY];
                    colorMapSelection[x][y] = s;
                } else {
                    System.out.println("erroneous numbers!!!!");
                    s="";
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

    private void setFocusTile() {
        int x = 7;
        int y = 5;
        visiblegrid[x][y].label.setStyle(colorMapSelection[x][y] + "-fx-border-color: white;");
    }

    public void moveFocusTile(Coordinates oldFocus, Coordinates newFocus) {
        visiblegrid[oldFocus.x][oldFocus.y].label.setStyle(
                colorMap[oldFocus.x][oldFocus.y] +
                        "-fx-border-color: transparent;"
        );
        visiblegrid[newFocus.x][newFocus.y].label.setStyle(
                colorMap[newFocus.x][newFocus.y] +
                        "-fx-border-color: white;"
        );
        focus = newFocus;
    }

    public GridPane getMap() {
        return this;
    }

    public String[][] getColorMap() {
        return colorMap;
    }

    private void resetDifference() {
        dx = 0;
        dy = 0;
    }
}
