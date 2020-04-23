package Presentation.View;

import Presentation.View.Widget.Board_widget;
import Presentation.View.Widget.Player_widget;
import Presentation.View.Widget.Settings_widget;
import Presentation.View.Widget.Weight_widget;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Game_view extends BorderPane {
    private Player_widget player1_widget;
    private Player_widget player2_widget;
    private Board_widget board_widget;
    private Weight_widget weight_widget;
    private Settings_widget settings_widget;


    public Game_view() {
        this.player1_widget = new Player_widget();
        this.player2_widget = new Player_widget();
        this.board_widget = new Board_widget();
        this.weight_widget = new Weight_widget();
        this.settings_widget = new Settings_widget();
        setupStyle();
        setupLayout();
    }

    private void setupLayout(){
        setTop(player2_widget);
        setBottom(player1_widget);
        setCenter(board_widget);
        setLeft(settings_widget);
        setRight(weight_widget);
        //autosize();
        setAlignment(player1_widget,Pos.CENTER);
        setAlignment(player2_widget, Pos.CENTER);
        setAlignment(board_widget, Pos.CENTER);
        setAlignment(weight_widget, Pos.CENTER);
        setAlignment(settings_widget, Pos.CENTER);
    }

    private void setupStyle(){
        Font settingsFont = new Font(22);
        Font titleFont = new Font(36);
        Font weightFont = new Font(46);
        String enableColor = "#acbda8";
        String disableColor = "#70756e";
        String nameColor = "#dae0ca";
        String backgroundColor = "#344b33";
        String tileColor = "#7f886e";
        String tileColor_hints = "#a6ba5b";
        String frameColor = "#161715";

        setStyle("-fx-background-color: " + backgroundColor);

        this.settings_widget.setupStyle(disableColor, enableColor, settingsFont);
        this.player1_widget.setupStyle(titleFont, nameColor);
        this.player2_widget.setupStyle(titleFont, nameColor);
        this.weight_widget.setupStyle(weightFont, enableColor);
        this.board_widget.setupStyle(tileColor, tileColor_hints, frameColor);

    }

    public Settings_widget getSettings_widget() {
        return settings_widget;
    }


    public void setPlayerOneName(String name){
        player1_widget.setName(name);
    }

    public void setPlayerTwoName(String name) {
        player2_widget.setName(name);
    }

    public Board_widget getBoard_widget() {
        return board_widget;
    }

    public void draw(Game.ScoreBoard scoreBoard) {
        board_widget.draw(scoreBoard);
        player1_widget.setScore(scoreBoard.getScorePlayer1());
        player2_widget.setScore(scoreBoard.getScorePlayer2());
        weight_widget.setWeight(scoreBoard.getWeight());
        player1_widget.setName(scoreBoard.getPlayer1());
        player2_widget.setName(scoreBoard.getPlayer2());
        if(scoreBoard.getBlack().equals(scoreBoard.getPlayer1())){
            player1_widget.setToBlack();
            player2_widget.setToWhite();
        } else{
            player1_widget.setToWhite();
            player2_widget.setToBlack();
        }
    }

    public void showHints() {
        board_widget.showHints();
    }

    public void hideHints() {
        board_widget.hideHints();
    }

    public void showValues() {
        board_widget.showValues();
    }

    public void hideValues() {
        board_widget.hideValues();
    }

}
