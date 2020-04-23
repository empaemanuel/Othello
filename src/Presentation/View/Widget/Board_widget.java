package Presentation.View.Widget;

import Game.Board;
import Game.ScoreBoard;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class Board_widget extends GridPane {
    private boolean showHints = false;
    private boolean showValues = false;

    public Board_widget() {
        init();
    }

    private void init() {
        for (int i = 0; i < 64; i++) {
            add(new Tile(), i % 8, i / 8);
        }
        setGridLinesVisible(true);
        //setStyle("-fx-background-color: #344b33");
        setMinSize(400,400);
        setAlignment(Pos.CENTER);
    }

    private int[][] board;
    public void draw(ScoreBoard scoreBoard){
        board = scoreBoard.getBoard();
        updateScreen();
    }

    private void updateScreen(){
        Tile tile;
        for (int i = 0; i < 64; i++) {
            tile = (Tile) (getChildren().get(i));
            tile.draw(board[i/board.length][i%board.length]);
        }
    }

    public void showHints() {
        showHints = true;
        updateScreen();
    }

    public void hideHints() {
        showHints = false;
        updateScreen();
    }

    public void hideValues() {
        showValues = false;
        updateScreen();
    }

    public void showValues(){
        showValues = true;
        updateScreen();
    }

    private String tileColor, hintsColor, frameColor;
    public void setupStyle(String tileColor, String tileColor_hints, String frameColor) {
        this.tileColor = tileColor;
        this.hintsColor = tileColor_hints;
        this.frameColor = frameColor;
        setStyle("-fx-background-color: " + frameColor);
    }


    private final class Tile extends StackPane {
        private final int TILE_WIDTH = 50;
        private final int BRICK_WIDTH = TILE_WIDTH / 2;
        private Rectangle background = new Rectangle(TILE_WIDTH, TILE_WIDTH);
        private Circle brick = new Circle(BRICK_WIDTH - 5);
        private Label value = new Label();



        Tile() {
            brick.setCenterY(BRICK_WIDTH);
            brick.setCenterX(BRICK_WIDTH);
            brick.setFill(Color.TRANSPARENT);
            value.setFont(new Font(18));
            setAlignment(Pos.CENTER);
            getChildren().addAll(background, brick, value);
            //setOnMouseClicked(new clickHandler());
        }

        private void draw(int value) {
            drawBackground(value);
            drawBrick(value);
            drawValue(value);
        }

        private void drawBackground(int value) {
            background.setFill(Paint.valueOf(
                    value > 1 && showHints ? hintsColor : tileColor
            ));
        }

        private void drawBrick(int value) {
            brick.setFill(value == 1 ? Color.WHITE :
                    value == -1 ? Color.BLACK :
                            Color.TRANSPARENT
            );
        }

        private void drawValue(int value) {
            this.value.setText(showValues && value > 1 ? Integer.toString(value) : "");
        }

//        class clickHandler implements EventHandler<MouseEvent> {
//            @Override
//            public void handle(MouseEvent e) {
//                GameDisplay.this.handleInput(((Tile) e.getSource()));
//            }
//        }

    }
}
