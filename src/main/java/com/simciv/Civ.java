package com.simciv;

import com.simciv.Screens.GamePlay.GameMap;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Civ extends Application {
    String[] playerName = {"",""};
    private String mapSizeLevel;
    public static void main(String[] args) {
        launch(args);
    }
    ArrayList<String> mapSizes = new ArrayList<String>(5);

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

        final TextField name = new TextField("");
        name.setPromptText("Enter your first name.");
        name.setPrefColumnCount(10);
        GridPane.setConstraints(name, 0, 0);
        grid.getChildren().add(name);

        TextField lastName = new TextField("");
        lastName.setPromptText("Enter your last name.");
        GridPane.setConstraints(lastName, 0, 1);
        grid.getChildren().add(lastName);

        ChoiceBox<String> mapSize = new ChoiceBox<String>();
        mapSize.getItems().addAll("Small", "Medium","Large", "Huge");
        mapSize.setValue("Medium");
        GridPane.setConstraints(mapSize, 0, 2);
        grid.getChildren().add(mapSize);

        Label size = new Label("World size");
        GridPane.setConstraints(size, 1, 2);
        grid.getChildren().add(size);

        Button btn = new Button();
        btn.setText("Play");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //record name
                if(name.getText().equals(""))return;
                playerName[0] = name.getText();
                if(lastName.getText().equals(""))return;
                playerName[1] = lastName.getText();

                mapSizeLevel = mapSize.getValue();
                grid.getChildren().removeAll(name,lastName, btn);
                visibleMap.make();
                primaryStage.setScene(new Scene(visibleMap.getMap(), 640, 480));
                primaryStage.show();
            }
        });

        GridPane.setConstraints(btn, 1, 3);

        grid.getChildren().add(btn);
        primaryStage.setScene(new Scene(grid, 640, 480));
        primaryStage.show();
    }
}
