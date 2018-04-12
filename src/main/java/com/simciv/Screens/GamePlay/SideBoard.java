package com.simciv.Screens.GamePlay;

import com.simciv.GameStats;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import static com.simciv.GameStats.mapSize;


public class SideBoard extends GridPane {


    public SideBoard(){
        setPadding(new Insets(5,
                5,
                5,
                5));
        setVgap(2);
        setHgap(2);

        MiniMap map = new MiniMap(mapSize);
        map.makeMap(GameStats.maxX, GameStats.maxY);
        GridPane g = map.get();
        g.setMinHeight(100);
        g.setMinWidth(74);
        setConstraints(g,0,1);
        getChildren().add(g);

        Label bank = new Label("Bank: ");
        setConstraints(bank,0,2);
        getChildren().add(bank);
        Label happy = new Label("Happiness: ");
        setConstraints(happy,0,3);
        getChildren().add(happy);
        Label risk = new Label("Risk: ");
        setConstraints(risk,0,4);
        getChildren().add(risk);
        Label line = new Label("_____________");
        setConstraints(line,0,5);
        getChildren().add(line);
        Label movement = new Label("Movement: ");
        setConstraints(movement,0,6);
        getChildren().add(movement);
        Label power = new Label("Power: ");
        setConstraints(power,0,7);
        getChildren().add(power);
        Label xp = new Label("Experience: ");
        setConstraints(xp,0,8);
        getChildren().add(xp);
    }
}
