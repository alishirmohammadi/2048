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
        for (int i = 0; i < game.getN(); i++)
            for (int j = 0; j < game.getN(); j++) {
                labels[i][j].getStyleClass().clear();
                labels[i][j].getStyleClass().addAll("cell", "cell-" + game.getCell(i, j));
                if (game.getCell(i, j) != 0)
                    labels[i][j].setText("" + game.getCell(i, j));
                else
                    labels[i][j].setText("");
            }
    }

    public static String getScoreString(int n) {
        if(n > 1000) {
            if(n < 10000)
                return String.format("%d.%dK", n / 1000, n / 100 % 10);
            return String.format("%dK", n / 1000);
        }
        return "" + n;
    }

    public static EventHandler<KeyEvent> keyEventEventHandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            if (event.getCode() == KeyCode.RIGHT && game.canMove(Direction.RIGHT))
                game.move(Direction.RIGHT);
            else if (event.getCode() == KeyCode.LEFT && game.canMove(Direction.LEFT))
                game.move(Direction.LEFT);
            else if (event.getCode() == KeyCode.DOWN && game.canMove(Direction.DOWN))
                game.move(Direction.DOWN);
            else if (event.getCode() == KeyCode.UP && game.canMove(Direction.UP))
                game.move(Direction.UP);
            else
                return;
            if (game.getScore() > Main.getLoggedInUser().getBestScore(game.getN())) {
                Main.getLoggedInUser().setBestScore(game.getN(), game.getScore());
            }
            Main.scoreButton.setText(getScoreString(game.getScore()));
            Main.bestScoreBoard.setText(getScoreString(Main.getLoggedInUser().getBestScore(game.getN())));
            game.insert();
            showLabels();
        }
    };
}
