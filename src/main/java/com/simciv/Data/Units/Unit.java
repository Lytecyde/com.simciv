package com.simciv.Data.Units;

import com.simciv.Civ;
import com.simciv.Coordinates;
import com.simciv.Data.Manager;
import com.simciv.ID;

import java.util.Map;

public class Unit {
    public Coordinates location;
    ID id = new ID("unit");
    Manager dm = Civ.getDataManager();
    public Map<String, Object> unitdata = Manager.getUnitData(0);

}
