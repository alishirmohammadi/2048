package views;

import controllers.GameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.User;
import models.Game;
import java.io.IOException;

public class Main extends Application {
    private static Stage stage;
    private static User loggedInUser;

    private static final int CELL_SIZE = 90;
    private static final int CELL_MARGIN = 10;
    private static final int GAME_MARGIN = 10;

    public static Label[][] labels;
    public static Game game;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("layouts/Login.fxml"));
        primaryStage.setResizable(false);
        Scene scene = new Scene(root, 480, 400);
        scene.getStylesheets().add("views/stylesheets/style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
        stage = primaryStage;
    }


    private static int getGameWidth(int n) {
        return n * (CELL_MARGIN + CELL_SIZE) + CELL_MARGIN + GAME_MARGIN * 2;
    }

    private static int getGameHeight(int n) {
        return n * (CELL_MARGIN + CELL_SIZE) + CELL_MARGIN + GAME_MARGIN * 2;
    }

    public static void setLoggedInUser(User user) {
        loggedInUser = user;
    }

    public static void showMenu() throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("layouts/GameMenu.fxml"));
        Scene scene = new Scene(root, 240, 380);
        scene.getStylesheets().add("views/stylesheets/style.css");
        stage.setScene(scene);
    }

    public static void startGame(int n) throws IOException {
        game = new Game(n);
        game.insert();
        game.insert();
        Group root = new Group();
        labels = new Label[n][n];
        root.setStyle("-fx-background-color: #FAF8EF");
        Label label = new Label("");
        label.setPrefWidth(n * (CELL_MARGIN + CELL_SIZE)+ CELL_MARGIN);
        label.setPrefHeight(n * (CELL_MARGIN + CELL_SIZE) + CELL_MARGIN);
        label.relocate(GAME_MARGIN, GAME_MARGIN);
        label.setStyle("-fx-background-color: #BBADA0; -fx-background-radius: 10px");
        root.getChildren().add(label);
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                labels[i][j] = new Label("");
                labels[i][j].getStyleClass().add("cell");
                labels[i][j].getStyleClass().add("cell-0");
                labels[i][j].relocate(i * (CELL_MARGIN + CELL_SIZE) + GAME_MARGIN + CELL_MARGIN, j * (CELL_MARGIN + CELL_SIZE) + GAME_MARGIN + CELL_MARGIN);
                root.getChildren().add(labels[i][j]);
            }
        }
        Scene scene = new Scene(root, getGameWidth(n), getGameHeight(n));
        scene.getStylesheets().add("views/stylesheets/style.css");
        stage.setScene(scene);
        scene.setOnKeyPressed(GameController.keyEventEventHandler);
        GameController.showLabels();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
