package controllers;

import javafx.event.ActionEvent;
import views.Main;

import java.io.IOException;

public class MenuController {
    private void startGame(int n) {
        try {
            Main.startGame(n);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void handle2x2ButtonAction(ActionEvent actionEvent) {
        startGame(2);
    }

    public void handle3x3ButtonAction(ActionEvent actionEvent) {
        startGame(3);
    }

    public void handle4x4ButtonAction(ActionEvent actionEvent) {
        startGame(4);
    }

    public void handle5x5ButtonAction(ActionEvent actionEvent) {
        startGame(5);
    }

    public void handle6x6ButtonAction(ActionEvent actionEvent) {
        startGame(6);
    }

    public void handle7x7ButtonAction(ActionEvent actionEvent) {
        startGame(7);
    }

    public void handle8x8ButtonAction(ActionEvent actionEvent) {
        startGame(8);
    }
}
