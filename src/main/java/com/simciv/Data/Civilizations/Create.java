package com.simciv.Data.Civilizations;

import com.simciv.Coordinates;
import com.simciv.GameStats;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Create {
    LinkedList<Coordinates> allSpots;
    public Create() {
        allSpots = createStartingSpots();
    }

    public LinkedList<Coordinates> createStartingSpots() {
        Coordinates c;
        LinkedList<Coordinates> allNationsStartingSpots =  new LinkedList<>();
        ArrayList numbersX = new ArrayList();
        ArrayList numbersY = new ArrayList();
        for(int i = 0; i < GameStats.maxX; i++)
        {
            numbersX.add(i);
        }
        for(int i = 0; i < GameStats.maxY; i++)
        {
            numbersY.add(i);
        }

        Collections.shuffle(numbersX);
        Collections.shuffle(numbersY);



        System.out.print("This week's civilization locations are: ");

        for(int j = 0; j < GameStats.playerCount; j++)
        {
            System.out.print(numbersX.get(j) + " " + numbersY.get(j));
            System.out.println("civ" + j);
        }
        return allNationsStartingSpots;
    }



    public LinkedList<Coordinates> getSpots() {
        return allSpots;
    }

}
