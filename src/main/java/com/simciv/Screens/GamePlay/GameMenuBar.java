package com.simciv.Screens.GamePlay;

import javafx.application.Application;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

public class GameMenuBar extends javafx.scene.control.MenuBar {

    final Menu game = new Menu("Game");
    final Menu options = new Menu("Options");
    final Menu advisors = new Menu("Advisors");
    final Menu wiki = new Menu("Wiki");
    final Menu help = new Menu("Help");

    public GameMenuBar(){
        getMenus().addAll(game, options, advisors, wiki, help);
    }

    public MenuBar getMenuBar(){
        return this;
    }
}
