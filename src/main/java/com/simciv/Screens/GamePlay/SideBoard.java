package com.simciv.Screens.GamePlay;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


public class SideBoard extends GridPane {
    private Label bank = new Label("Bank: ");
    private Label happy = new Label("Happiness: ");
    private Label risk = new Label("Risk: ");
    private Label line = new Label("_____________");
    private Label movement = new Label("Movement: ");
    private Label power = new Label("Power: ");
    private Label xp = new Label("Experience: ");
    private MiniMap m = new MiniMap();
    public SideBoard(){
        setPadding(new Insets(10,
                10,
                10,
                10));
        setVgap(2);
        setHgap(2);
        setMinWidth(40);
        setConstraints(bank,0,1);
        getChildren().add(bank);
        setConstraints(happy,0,2);
        getChildren().add(happy);
        setConstraints(risk,0,3);
        getChildren().add(risk);
        setConstraints(line,0,4);
        getChildren().add(line);
        setConstraints(movement,0,5);
        getChildren().add(movement);
        setConstraints(power,0,6);
        getChildren().add(power);
        setConstraints(xp,0,7);
        getChildren().add(xp);
    }
}
