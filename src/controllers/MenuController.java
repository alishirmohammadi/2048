package controllers;

import javafx.event.ActionEvent;
import views.Main;

public class MenuController {
    
    public void handle2x2ButtonAction(ActionEvent actionEvent) {
        Main.startGame(2);
    }

    public void handle3x3ButtonAction(ActionEvent actionEvent) {
        Main.startGame(3);
    }

    public void handle4x4ButtonAction(ActionEvent actionEvent) {
        Main.startGame(4);
    }

    public void handle5x5ButtonAction(ActionEvent actionEvent) {
        Main.startGame(5);
    }

    public void handle6x6ButtonAction(ActionEvent actionEvent) {
        Main.startGame(6);
    }

    public void handle7x7ButtonAction(ActionEvent actionEvent) {
        Main.startGame(7);
    }

    public void handle8x8ButtonAction(ActionEvent actionEvent) {
        Main.startGame(8);
    }
}
