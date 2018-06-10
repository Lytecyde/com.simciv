package com.simciv;


import com.simciv.Screens.Init.Init;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Civ extends Application {
    int gridTileSide = 35;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Init init = new Init(primaryStage);
        GridPane grid = init.getGrid();

        primaryStage.setScene(new Scene(grid, gridTileSide*15,
                gridTileSide*12));
        primaryStage.show();
    }
}
