package com.simciv.actor;

import com.simciv.Enums.ExperienceType;
import com.simciv.Enums.MobilityType;
import com.simciv.Enums.UnitType;
import com.simciv.ID;
//import gson.*;

public class Unit {

    int movement;
    int power;

    ExperienceType experience;
    MobilityType mobility;
    UnitType arena;
    //status
    boolean fortified = false;
    boolean ill = false;
    boolean camping = false;

    public Unit(int unitCode) {
        //read json

    }

    ID identification;

    public ExperienceType getExperience() {
        return experience;
    }

    public int getMovement() {
        return movement;
    }

    public int getPower() {
        return power;
    }

    public UnitType getUnitType() {
        return arena;
    }
}
