package com.simciv;


import com.simciv.Data.DataManager;
import com.simciv.Screens.Init.Init;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;


public class Civ extends Application {
    private static DataManager dataManager;

    public static void main(String[] args) {
        launch(args);
        try {
            dataManager = new DataManager();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void start(Stage primaryStage) {
        Init init = new Init(primaryStage);
        GridPane grid = init.getGrid();

        primaryStage.setScene(new Scene(grid, 35 * 15, 35 * 12));
        primaryStage.show();

    }

    public static DataManager getDataManager() {
        return dataManager;
    }
}
