package com.simciv.Screens.GamePlay;

import com.simciv.Coordinate;
import com.simciv.GameStats;

class CursorFrame {
    private final GameMap gameMap;
    private Tile[][] tiling;
    private String[][] landscapeAtViewport;
    CursorFrame(GameMap gameMap) {
        this.gameMap = gameMap;
        landscapeAtViewport = this.gameMap.getViewport().getLandscape();
        tiling = gameMap.getTiling();
    }

    void setFocusTile() {
        int x = GameStats.CENTER.x;
        int y = GameStats.CENTER.y;
        tiling[x][y].label.setStyle(landscapeAtViewport[x][y] +
                        "-fx-border-color: white;");
    }

    void moveFocusTile(Coordinate oldFocus) {
        removeCursorBox(oldFocus);
        Coordinate newFocus = getNewFocus(oldFocus);
        placeCursorBox(newFocus);
        gameMap.setFocus(newFocus);
    }

    private void placeCursorBox(Coordinate newFocus) {
        String style = landscapeAtViewport[newFocus.x][newFocus.y] +
                "-fx-border-color: white;";
        tiling[newFocus.x][newFocus.y].label.setStyle(style);
    }

    void removeCursorBox(Coordinate oldFocus) {
        String style = landscapeAtViewport[oldFocus.x][oldFocus.y] +
                "-fx-border-color: transparent;";
        tiling[oldFocus.x][oldFocus.y].label.setStyle(style);
    }

    private Coordinate getNewFocus(Coordinate f) {
        int x, y;
        Coordinate diff = gameMap.getDiff();
        if ((f.x + diff.x) >= Viewport.maxX) {
            x = Viewport.maxX - 1;
        } else if (f.x + diff.x < GameStats.START.x) {
            x = GameStats.START.x;
        } else {
            x = (f.x + diff.x);
        }

        if ((f.y + diff.y) >= Viewport.maxY) {
            y = Viewport.maxY - 1;
        } else if (f.y + diff.y < GameStats.START.y) {
            y = GameStats.START.y;
        } else {
            y = (f.y + diff.y);
        }

        resetDifference();
        return new Coordinate(x, y);
    }

    private void resetDifference() {
        gameMap.setDiff();
    }
}