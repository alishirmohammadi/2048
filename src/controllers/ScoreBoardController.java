package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.User;
import views.Main;

import java.io.IOException;
import java.util.ArrayList;

public class ScoreBoardController {
    @FXML public Label label3_1, label3_2, label3_3;
    @FXML public Label label4_1, label4_2, label4_3;
    @FXML public Label label5_1, label5_2, label5_3;
    @FXML public Label label6_1, label6_2, label6_3;
    @FXML public Label label7_1, label7_2, label7_3;
    @FXML public Label label8_1, label8_2, label8_3;
    @FXML public Label label3_1_score, label3_2_score, label3_3_score;
    @FXML public Label label4_1_score, label4_2_score, label4_3_score;
    @FXML public Label label5_1_score, label5_2_score, label5_3_score;
    @FXML public Label label6_1_score, label6_2_score, label6_3_score;
    @FXML public Label label7_1_score, label7_2_score, label7_3_score;
    @FXML public Label label8_1_score, label8_2_score, label8_3_score;
    public static ScoreBoardController instance;

    { instance = this; }
    
    public void showLabels(ArrayList<User> users) {
        showScores(users, 3, new Label[] {label3_1, label3_1_score, label3_2, label3_2_score, label3_3, label3_3_score});
        showScores(users, 4, new Label[] {label4_1, label4_1_score, label4_2, label4_2_score, label4_3, label4_3_score});
        showScores(users, 5, new Label[] {label5_1, label5_1_score, label5_2, label5_2_score, label5_3, label5_3_score});
        showScores(users, 6, new Label[] {label6_1, label6_1_score, label6_2, label6_2_score, label6_3, label6_3_score});
        showScores(users, 7, new Label[] {label7_1, label7_1_score, label7_2, label7_2_score, label7_3, label7_3_score});
        showScores(users, 8, new Label[] {label8_1, label8_1_score, label8_2, label8_2_score, label8_3, label8_3_score});
    }

    private static void showScores(ArrayList<User> users, int n, Label[] labels) {
        users.sort((user1, user2) -> (-Integer.compare(user1.getBestScore(n), user2.getBestScore(n))));
        if(users.size() > 0) {
            labels[0].setText("1. " + users.get(0).getUsername());
            labels[1].setText("" + users.get(0).getBestScore(n));
        }
        if(users.size() > 1) {
            labels[2].setText("2. " + users.get(1).getUsername());
            labels[3].setText("" + users.get(1).getBestScore(n));
        }
        if(users.size() > 2) {
            labels[4].setText("2. " + users.get(2).getUsername());
            labels[5].setText("" + users.get(2).getBestScore(n));
        }
    }

    public void handleReturnButtonAction(ActionEvent actionEvent) {
        try {
            Main.showLoginMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
