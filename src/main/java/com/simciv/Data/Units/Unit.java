package com.simciv.Data.Units;

import com.simciv.Civ;
import com.simciv.Data.DataManager;
import com.simciv.ID;

import java.util.Map;

public class Unit {
    ID id = new ID();
    DataManager dm = Civ.getDataManager();
    Map<String, Object> unitdata = DataManager.getUnitData(0);
}
