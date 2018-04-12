package com.simciv.Screens.GamePlay;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

public class GameMenuBar extends javafx.scene.control.MenuBar {



    public GameMenuBar(){
        final Menu game = new Menu("Game");
        final Menu options = new Menu("Options");
        final Menu advisors = new Menu("Advisors");
        final Menu wiki = new Menu("Wiki");
        final Menu help = new Menu("Help");
        getMenus().addAll(game, options, advisors, wiki, help);
    }

    public MenuBar getMenuBar(){
        return this;
    }
}
