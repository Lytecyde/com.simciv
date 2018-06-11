package com.simciv;

import java.util.LinkedList;

public class History {
    private LinkedList<Turn> chronicle = new LinkedList();

    History() {

    }

    public void start() {
        Turn firstTurn = new Turn();
        firstTurn.t = GameStats.time;
        addNextTurn(firstTurn);
    }

    public void addNextTurn(Turn t) {
        chronicle.add(t);
    }
}
