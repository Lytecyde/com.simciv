package com.simciv.Players;

import com.simciv.Coordinates;
import com.simciv.ID;

public class Player {
    ID id = new ID("player");
    public Coordinates startLocation;


    public Player() {

    }

    public Coordinates getStartLocation() {
        return startLocation;
    }
}
