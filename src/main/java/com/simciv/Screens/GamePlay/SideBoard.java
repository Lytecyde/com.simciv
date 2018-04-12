package com.simciv.Screens.GamePlay;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


public class SideBoard extends GridPane {
    private Label bank = new Label("Bank: ");
    private Label happy = new Label("Happiness: ");

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
    }
}
