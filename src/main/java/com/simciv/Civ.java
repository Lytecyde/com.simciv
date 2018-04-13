package com.simciv;


import com.simciv.Screens.Init.Init;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Civ extends Application {
    private String[] playerName = {"",""};
    private String mapSizeLevel = "Medium";
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Init init = new Init(primaryStage);
        Scene scene = init.getScene();
        GridPane grid = init.getGrid();

        primaryStage.setScene(new Scene(grid, 640, 480));
        primaryStage.show();
    }
}
