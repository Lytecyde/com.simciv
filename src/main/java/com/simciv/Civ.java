package com.simciv;

import com.simciv.Screens.GamePlay.GameMap;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Civ extends Application {
    String[] playerName = {"",""};
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GameMap visibleMap = new GameMap();
        primaryStage.setTitle("Init");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,
                10,
                10,
                10));
        grid.setVgap(5);
        grid.setHgap(5);
        final TextField name = new TextField();
        name.setPromptText("Enter your first name.");
        name.setPrefColumnCount(10);
        GridPane.setConstraints(name, 0, 0);
        grid.getChildren().add(name);

        TextField lastName = new TextField();
        lastName.setPromptText("Enter your last name.");
        GridPane.setConstraints(lastName, 0, 1);
        grid.getChildren().add(lastName);

        Button btn = new Button();
        btn.setText("Save");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //record name
                playerName[0] = name.getText();
                playerName[1] = lastName.getText();
                grid.getChildren().removeAll(name,lastName, btn);
                visibleMap.make();
                primaryStage.setScene(new Scene(visibleMap.getMap(), 640, 480));
                primaryStage.show();
            }
        });

        GridPane.setConstraints(btn, 1, 1);

        grid.getChildren().add(btn);
        primaryStage.setScene(new Scene(grid, 640, 480));
        primaryStage.show();
    }
}
