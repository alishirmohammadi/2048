package views;

import controllers.GameController;
import controllers.ScoreBoardController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private static final int GAME_TOP_MARGIN = 100;

    public static Label[][] labels;
    public static Button scoreButton, bestScoreBoard;
    public static Game game;

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        primaryStage.setResizable(false);
        showLoginMenu();
    }

    public static void showLoginMenu() throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("layouts/Login.fxml"));
        Scene scene = new Scene(root, 480, 400);
        scene.getStylesheets().add("views/stylesheets/style.css");
        stage.setScene(scene);
        stage.show();
    }


    private static int getGameWidth(int n) {
        return n * (CELL_MARGIN + CELL_SIZE) + CELL_MARGIN + GAME_MARGIN * 2;
    }

    private static int getGameHeight(int n) {
        return n * (CELL_MARGIN + CELL_SIZE) + CELL_MARGIN + GAME_MARGIN * 2 + GAME_TOP_MARGIN;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User user) {
        loggedInUser = user;
    }

    public static void showScoreBoard() throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("layouts/ScoreBoard.fxml"));
        Scene scene = new Scene(root, 480, 400);
        scene.getStylesheets().add("views/stylesheets/style.css");
        ScoreBoardController.instance.showLabels(User.getUsers());
        stage.setScene(scene);
    }

    public static void showMenu() throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("layouts/GameMenu.fxml"));
        Scene scene = new Scene(root, 240, 380);
        scene.getStylesheets().add("views/stylesheets/style.css");
        stage.setScene(scene);
    }

    private static void addBackground(Group root, int n) {
        Label label = new Label("");
        label.setPrefWidth(n * (CELL_MARGIN + CELL_SIZE) + CELL_MARGIN);
        label.setPrefHeight(n * (CELL_MARGIN + CELL_SIZE) + CELL_MARGIN);
        label.relocate(GAME_MARGIN, GAME_MARGIN + GAME_TOP_MARGIN);
        label.setStyle("-fx-background-color: #BBADA0; -fx-background-radius: 10px");
        root.getChildren().add(label);
    }

    private static void addLabels(Group root, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                labels[i][j] = new Label("");
                labels[i][j].getStyleClass().add("cell");
                labels[i][j].getStyleClass().add("cell-0");
                labels[i][j].relocate(i * (CELL_MARGIN + CELL_SIZE) + GAME_MARGIN + CELL_MARGIN, j * (CELL_MARGIN + CELL_SIZE) + GAME_MARGIN + CELL_MARGIN + GAME_TOP_MARGIN);
                root.getChildren().add(labels[i][j]);
            }
        }
    }

    private static void addReturnButton(Group root) {
        Button button = new Button("Return\nto menu");
        button.setStyle("-fx-background-color: #8F7A66; -fx-padding: 10px; -fx-text-fill: #FAF8EF; -fx-text-alignment: center; -fx-font-size: 20;");
        button.relocate(GAME_MARGIN * 3 + 240, GAME_MARGIN * 2);
        button.setOnAction(event -> {
            try {
                showMenu();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        root.getChildren().add(button);
    }

    private static void addScoreLabel(Group root) {
        scoreButton = new Button("0");
        scoreButton.getStyleClass().add("score-board");
        scoreButton.relocate(GAME_MARGIN, GAME_MARGIN * 2);
        root.getChildren().add(scoreButton);

        bestScoreBoard = new Button(GameController.getScoreString(loggedInUser.getBestScore(game.getN())));
        bestScoreBoard.getStyleClass().add("score-board");
        bestScoreBoard.relocate(GAME_MARGIN * 2 + 120, GAME_MARGIN * 2);
        root.getChildren().add(bestScoreBoard);

        Label scoreLabel = new Label("SCORE");
        scoreLabel.relocate(30 + GAME_MARGIN, 27);
        scoreLabel.getStyleClass().add("score-label");
        root.getChildren().add(scoreLabel);

        Label bestScoreLabel = new Label("BEST");
        bestScoreLabel.relocate(156 + GAME_MARGIN * 2, 27);
        bestScoreLabel.getStyleClass().add("score-label");
        root.getChildren().add(bestScoreLabel);
    }

    public static void startGame(int n) {
        game = new Game(n);
        game.insert();
        game.insert();
        Group root = new Group();
        labels = new Label[n][n];
        root.setStyle("-fx-background-color: #FAF8EF");
        addBackground(root, n);
        addLabels(root, n);
        addScoreLabel(root);
        addReturnButton(root);
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
