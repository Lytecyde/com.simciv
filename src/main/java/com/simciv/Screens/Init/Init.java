package com.simciv.Screens.Init;

import com.simciv.GameStats;
import com.simciv.Screens.GamePlay.GameMap;
import com.simciv.Screens.GamePlay.GameMenuBar;
import com.simciv.Screens.GamePlay.SideBoard;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Init {
    private String mapSizeLevel = "Medium";
    private GridPane grid;
    private Scene scene;
    public Init(Stage primaryStage){
        primaryStage.setTitle("Init");
        BorderPane borderPane =  new BorderPane();
        grid = new GridPane();
        grid.setPadding(new Insets(
                10,
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

        ChoiceBox<String> civName = new ChoiceBox<>();
        String[] civNames = {"Zulu", "Egypt", "Greece", "Viking", "India", "China", "Aztec"};
        civName.getItems().addAll(civNames);
        civName.setValue(civNames[(int)Math.floor(Math.random() * civNames.length)]);
        GridPane.setConstraints(civName, 0, 3);
        grid.getChildren().add(civName);

        Label civ = new Label("Your civilization.");
        GridPane.setConstraints(civ, 1, 3);
        grid.getChildren().add(civ);

        Button play = new Button();
        play.setText("Play");
        play.setOnAction(event -> {
            String[] playerName = {"",""};
            if(name.getText().equals(""))return;
            playerName[0] = name.getText();
            if(lastName.getText().equals(""))return;
            playerName[1] = lastName.getText();

            mapSizeLevel = mapSize.getValue();
            GameStats.saveInit(playerName, mapSizeLevel);
            grid.getChildren().removeAll(name, lastName, play);
            GameMap visibleMap = new GameMap();
            visibleMap.make();

            GameMenuBar menubar = new GameMenuBar();
            borderPane.setTop(menubar.getMenuBar());
            borderPane.setCenter(visibleMap.getMap());
            borderPane.setRight(new SideBoard());
            scene = new Scene(borderPane);

            primaryStage.setScene(scene);
            primaryStage.setTitle("SimpleCivilization:" + GameStats.playerName[1]);
            primaryStage.show();
        });
        GridPane.setConstraints(play, 1, 7);
        grid.getChildren().add(play);
    }

    public GridPane getGrid() {
       return grid;
    }
}
