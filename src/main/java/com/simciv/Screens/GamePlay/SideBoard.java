package com.simciv.Screens.GamePlay;

import com.simciv.GameStats;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import static com.simciv.GameStats.colorMap;
import static com.simciv.GameStats.mapSize;

public class SideBoard extends GridPane {

    public SideBoard(){
        setPadding(new Insets(5,
                5,
                5,
                5));
        setVgap(2);
        setHgap(2);
        setAlignment(Pos.TOP_LEFT);
        MiniMap map = new MiniMap(mapSize);
        colorMap = map.getColorMap();
        map.makeMap();
        GridPane g = map.get();
        g.setAlignment(Pos.CENTER);
        g.setVisible(true);
        setConstraints(g,0,1);
        getChildren().add(g);

        Label bank = new Label("Bank: ");
        setConstraints(bank,0,2);
        getChildren().add(bank);
        Label happy = new Label("Happiness: ");
        setConstraints(happy,0,3);
        getChildren().add(happy);
        Label year = new Label("Year: ");
        setConstraints(year,0,4);
        getChildren().add(year);
        Label line = new Label("______________________");
        setConstraints(line,0,5);
        getChildren().add(line);
        Label xp = new Label("Unit: ");
        setConstraints(xp,0,6);
        getChildren().add(xp);
        Label power = new Label("Power: ");
        setConstraints(power,0,7);
        getChildren().add(power);
        Label movement = new Label("Movement: ");
        setConstraints(movement,0,8);
        getChildren().add(movement);

        Label line2 = new Label("______________________");
        setConstraints(line2,0,9);
        getChildren().add(line2);
        Label land = new Label("Territory:");
        setConstraints(land,0,10);
        getChildren().add(land);
        Label md = new Label("md:");
        setConstraints(md,0,11);
        getChildren().add(md);
        Label shields = new Label("shields:");
        setConstraints(shields,0,12);
        getChildren().add(shields);
        Label food = new Label("food:");
        setConstraints(food,0,13);
        getChildren().add(food);
        Label gold = new Label("gold:");
        setConstraints(gold,0,14);
        getChildren().add(gold);
        Button next = new Button("Next turn");
        next.setOnAction(e -> year.setText("Year:" + GameStats.time++));
        next.setFocusTraversable(false);
        setConstraints(next,0,15);
        getChildren().add(next);

    }
}
