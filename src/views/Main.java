package views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.User;
import models.Game;
import java.io.IOException;


public class Main extends Application {

    private static Stage stage;
    private static User loggedInUser;
    private static Game game;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("layouts/Login.fxml"));
        primaryStage.setResizable(false);
        primaryStage.setOpacity(0.9);
        Scene scene = new Scene(root, 480, 400);
        scene.getStylesheets().add("views/stylesheets/style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
        stage = primaryStage;
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
        Parent root = FXMLLoader.load(Main.class.getResource("layouts/Game.fxml"));
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("views/stylesheets/style.css");
        stage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
