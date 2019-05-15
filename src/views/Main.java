package views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {

    private static Stage stage;

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

    public static void showMenu() throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("layouts/GameMenu.fxml"));
        Scene scene = new Scene(root, 240, 380);
        scene.getStylesheets().add("views/stylesheets/style.css");
        stage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
