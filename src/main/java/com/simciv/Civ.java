package com.simciv;


import com.simciv.Data.Manager;
import com.simciv.Screens.Init.Init;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;


public class Civ extends Application {
    int gridTileSide = 35;

    private static Manager dataManager;
    private static History history = new History();

    public static void main(String[] args) {
        launch(args);
        try {
            dataManager = new Manager();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        history.start();
    }

    public void start(Stage primaryStage) {
        Init init = new Init(primaryStage);
        GridPane grid = init.getGrid();

        primaryStage.setScene(new Scene(grid, gridTileSide*15,
                gridTileSide*12));
        primaryStage.show();

    }

    public static Manager getDataManager() {
        return dataManager;
    }
}
