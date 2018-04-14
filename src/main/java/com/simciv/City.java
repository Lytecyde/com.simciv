package com.simciv;

import com.simciv.Enums.CivilizationType;

public class City {
    public ID id;
    public byte status;
    public byte[] buildings;
    public byte X, Y;
    public byte actualSize;
    public byte currentProduction;
    public CivilizationType owner;
    public Sentiment support;
    public short food, shields, tools, books;
    public byte[] resourceTiles;
    public byte[] fortifiedUnits;
}
