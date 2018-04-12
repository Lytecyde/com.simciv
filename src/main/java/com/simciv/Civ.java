package com.simciv;

import com.simciv.Screens.GamePlay.GameMap;
import com.simciv.Screens.GamePlay.GameMenuBar;
import com.simciv.Screens.GamePlay.SideBoard;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
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
        GameMap visibleMap = new GameMap();
        primaryStage.setTitle("Init");
        BorderPane borderPane =  new BorderPane();
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

        ChoiceBox<String> mapSize = new ChoiceBox<>();
        mapSize.getItems().addAll("Small", "Medium","Large", "Huge");
        mapSize.setValue(mapSizeLevel);
        GridPane.setConstraints(mapSize, 0, 2);
        grid.getChildren().add(mapSize);

        Label size = new Label("World size");
        GridPane.setConstraints(size, 1, 2);
        grid.getChildren().add(size);

        Button play = new Button();
        play.setText("Play");
        play.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(name.getText().equals(""))return;
                playerName[0] = name.getText();
                if(lastName.getText().equals(""))return;
                playerName[1] = lastName.getText();

                mapSizeLevel = mapSize.getValue();

                grid.getChildren().removeAll(name,lastName, play);
                visibleMap.make();

                GameMenuBar menubar = new GameMenuBar();
                borderPane.setTop(menubar.getMenuBar());
                borderPane.setCenter(visibleMap.getMap());
                borderPane.setRight(new SideBoard());
                Scene scene = new Scene(borderPane);

                primaryStage.setMinWidth(100);
                primaryStage.setMinHeight(100);
                primaryStage.setScene(scene);
                primaryStage.setTitle("SimpleCivilization");
                primaryStage.show();
            }
        });
        GridPane.setConstraints(play, 1, 3);
        grid.getChildren().add(play);

        primaryStage.setScene(new Scene(grid, 640, 480));
        primaryStage.show();
    }
}
