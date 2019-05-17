package controllers;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import models.Direction;
import models.Game;
import views.Main;

public class GameController {
    private static Label[][] labels;
    private static Game game;

    public static void showLabels() {
        game = Main.game;
        labels = Main.labels;
        for(int i = 0; i < game.getN(); i++)
            for(int j = 0; j < game.getN(); j++) {
                labels[i][j].getStyleClass().clear();
                labels[i][j].getStyleClass().addAll("cell", "cell-" + game.getCell(i, j));
                if(game.getCell(i, j) != 0)
                    labels[i][j].setText("" + game.getCell(i, j));
            }
    }

    public static EventHandler<KeyEvent> keyEventEventHandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            if(event.getCode() == KeyCode.RIGHT)
                game.move(Direction.RIGHT);
            if(event.getCode() == KeyCode.LEFT)
                game.move(Direction.LEFT);
            if(event.getCode() == KeyCode.UP)
                game.move(Direction.UP);
            if(event.getCode() == KeyCode.DOWN)
                game.move(Direction.DOWN);
            game.insert();
            showLabels();
        }
    };
}
