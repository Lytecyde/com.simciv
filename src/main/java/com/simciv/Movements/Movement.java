package com.simciv.Movements;

import com.simciv.Coordinates;
import com.simciv.Data.Units.Unit;
import com.simciv.Data.Units.Units;



public class Movement {
    public Movement (Units units) {
        for (Unit u: units.list) {
            Coordinates c = u.location;
        }
    }
}
