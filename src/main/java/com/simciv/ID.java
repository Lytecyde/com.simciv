package com.simciv;

import com.simciv.Enums.CivilizationType;

public class ID {
    CivilizationType civ;
    int code;
    String name;

    public ID(){
        code = getCodeValue();
    }

    private int getCodeValue(){
        return GameStats.unitsInTotal;
    }
}
