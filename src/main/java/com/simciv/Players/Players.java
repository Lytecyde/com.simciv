package com.simciv.Players;


import com.simciv.Coordinates;
import com.simciv.Data.Civilizations.Create;
import com.simciv.GameStats;

import java.util.LinkedList;

public class Players {
    public LinkedList<Player> list = new LinkedList<>();
    public LinkedList<Coordinates> allSpots = new LinkedList<>();
    Create start = new Create();

    public Players() {
        initPlayers();
        allSpots.addAll(start.getSpots());
        makeStartingSpots();
    }

    private void initPlayers() {
        for (int i = 0; i < GameStats.playerCount; i++) {
            Player p = new Player();
            list.add(p);
        }
    }

    private void makeStartingSpots() {
        for (int i = 0; i < GameStats.playerCount; i++) {
            Coordinates c = allSpots.get(i);
            list.get(i).startLocation = c;
        }
    }
}
