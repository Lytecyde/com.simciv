package com.simciv.Data.Civilizations;

import com.simciv.Coordinates;
import com.simciv.GameStats;

import java.util.LinkedList;

public class Create {
    LinkedList<Coordinates> allSpots = new LinkedList<>();
    public Create() {
        allSpots = createStartingSpots();
    }

    public LinkedList<Coordinates> createStartingSpots() {
        Coordinates c;
        LinkedList<Coordinates> allNationsStartingSpots =  new LinkedList<>();
        do{
            c = new Coordinates((int)(Math.random() * GameStats.maxX), (int)(Math.random() * GameStats.maxY));
            allNationsStartingSpots.add(c);
        }while(!among(allNationsStartingSpots, c));
        return allNationsStartingSpots;
    }

    private boolean among(LinkedList<Coordinates> allNationsStartingSpots, Coordinates c) {
        for (Coordinates spot: allNationsStartingSpots) {
            if (spot.equals(c)){
                return false;
            }
        }
        return true;
    }

    public LinkedList<Coordinates> getSpots() {
        return allSpots;
    }

}
