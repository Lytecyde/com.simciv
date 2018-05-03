package com.simciv.Players;

import com.simciv.Coordinates;
import com.simciv.Data.Civilizations.Create;
import com.simciv.ID;

public class Player {
    ID id = new ID("player");
    public Coordinates startLocation = new Coordinates(0,0);
    Create start = new Create();
    public Player() {

    }
}
