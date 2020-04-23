package Presentation.Controller;

import Game.Board;
import Game.ScoreBoard;
import Game.Spectator;
import javafx.application.Platform;

public class Spectator_controller implements Spectator {
    private Game_controller game_controller;
    public Spectator_controller(Game_controller game_controller) {
        this.game_controller = game_controller;
    }

    private ScoreBoard scoreBoard;
    @Override
    public void update(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
        Platform.runLater(this::updateScreen);
    }
    private void updateScreen(){
        game_controller.updateScreen(scoreBoard);
    }
}
