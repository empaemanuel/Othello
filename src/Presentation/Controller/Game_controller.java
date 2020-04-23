package Presentation.Controller;

import Game.Game;
import Game.ScoreBoard;
import Game.Board;
import Presentation.Model.Game_model;
import Presentation.View.Game_view;
import javafx.application.Platform;

public class Game_controller{
    private Game_view game_view;
    private Settings_controller settings_controller;

    private Spectator_controller spectator;
    //private Game_model game_model;

    public Game_controller(Game_view game_view) {
        //this.game_model = game_model;
        this.game_view = game_view;
        this.settings_controller = new Settings_controller(this, game_view.getSettings_widget(), game_view);
        setupSimulation();
    }

    private Game game;

    void setupSimulation(){
        game = new Game();
        spectator = new Spectator_controller(this);
        game.addSpectator(spectator);
    }

    void setupSinglePlayer(){
        //TODO single player functionality
    }

    void setupMultiplayer(){
        //TODO multiplayer funcationality
    }

    protected void updateScreen(ScoreBoard scoreBoard){
        game_view.draw(scoreBoard);
    }

    public void startGame() {
        game.start();
    }

    public void resetGame() {
        setupSimulation();
    }

    public void setDelay(long timeInMillis) {
        game.setDelay(timeInMillis);
    }

    public void resumeGame() {
        game.resume();
    }

    public void pauseGame() {
        game.pause();
    }
}
