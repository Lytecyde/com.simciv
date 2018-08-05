package com.simciv.Screens.GamePlay;

import com.simciv.Coordinate;
import com.simciv.GameStats;

class CursorFrame {
    private final GameMap gameMap;
    private Tile[][] tiling;
    CursorFrame(GameMap gameMap) {
        this.gameMap = gameMap;
        tiling = gameMap.getTiling();
    }

    void setFocusTile() {
        int x = GameStats.CENTER.x;
        int y = GameStats.CENTER.y;
        tiling[x][y].label.setStyle(
                gameMap.getColorMapSelection()[x][y] +
                "-fx-border-color: white;");
    }

    void moveFocusTile(Coordinate oldFocus) {
        removeCursorBox(oldFocus);
        Coordinate newFocus = getAdjustedCoordinates();
        placeCursorBox(newFocus);
        gameMap.setFocus(newFocus);
    }

    void placeCursorBox(Coordinate newFocus) {
        tiling[newFocus.x][newFocus.y].label.setStyle(
                gameMap.getViewport().landscape[newFocus.x + GameStats.CENTER.x]
                        [newFocus.y + GameStats.CENTER.y]
                        + "-fx-border-color: white;"
        );
    }

    void removeCursorBox(Coordinate oldFocus) {
        tiling[oldFocus.x][oldFocus.y].label.setStyle(
                gameMap.getViewport().landscape
                        [oldFocus.x + GameStats.CENTER.x]
                        [oldFocus.y + GameStats.CENTER.y] +
                        "-fx-border-color: transparent;"
        );
    }

    Coordinate getAdjustedCoordinates() {
        return getNewFocus(gameMap.getFocus(), gameMap.getDiff());
    }

    private Coordinate getNewFocus(Coordinate f, Coordinate diff) {
        int x = (f.x + diff.x) >= Viewport.maxX ? Viewport.maxX - 1 :
                (f.x + diff.x) < GameStats.START.x ? GameStats.START.x :
                        (f.x + diff.x);
        int y = (f.y + diff.y) >= Viewport.maxY ? Viewport.maxY - 1 :
                (f.y + diff.y) < GameStats.START.y ? GameStats.START.y :
                        (f.y + diff.y);
        resetDifference();
        return new Coordinate(x, y);
    }

    private void resetDifference() {
        gameMap.setDiff();
    }
}