import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import javax.swing.*;


/***
 * Handling all display of the actual game, scores and tiles.
 */
public class GameDisplay extends VBox implements Player{
    private GridPane grid = new GridPane();
    private boolean showValues = false;
    private boolean showHints = false;

    private HBox sliderBox;
    private Slider slider;
    private Label sliderLabel;
    private Button pauseAndPlayButton = new Button();

    private Othello game;
    private int[][] board;

    private Label scoreBlack = new Label();
    private Label scoreWhite = new Label();
    private Label turnLabel = new Label();
    private Label weightScore = new Label();
    private VBox  scoreBox = new VBox();

    private BooleanProperty booleanProperty = new SimpleBooleanProperty(true);

    GameDisplay(){
        //=================VBOX
        setStyle("-fx-background-color: #344b33");
        setAlignment(Pos.CENTER);
        HBox hbox = new HBox();
        sliderBox = new HBox();
        getChildren().addAll(sliderBox, grid, hbox);

        //======== ComputerDelayInput
        slider = new Slider();
        sliderLabel  = new Label(2.5 + " seconds");
        sliderLabel.setTextFill(Color.BLACK);
        sliderBox.setAlignment(Pos.CENTER);
        sliderBox.getChildren().addAll(slider, sliderLabel, pauseAndPlayButton);
        sliderBox.setPadding(new Insets(0,0,15,0));
        sliderBox.setSpacing(10);
        slider.setMin(0);
        slider.setMax(2000);
        slider.setValue(1000);
        slider.setShowTickLabels(false);
        slider.setShowTickMarks(false);
        slider.setBlockIncrement(100);
        slider.setSnapToTicks(true);
        sliderBox.setVisible(false);
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                game.setDelay(t1.longValue());
                sliderLabel.setText(String.format("%.2f", t1.doubleValue() / 1000) + " seconds.");
            }
        });

        //Pause and Play
        pauseAndPlayButton.setText("Pause");
        pauseAndPlayButton.setVisible(false);
        pauseAndPlayButton.defaultButtonProperty();
        pauseAndPlayButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(pauseAndPlayButton.getText().equals("Play")) {
                    pauseAndPlayButton.setText("Pause");
                    game.resume();
                } else {
                    pauseAndPlayButton.setText("Play");
                    game.pause();
                }
            }
        });

        //======== GRID
        grid.setAlignment(Pos.BOTTOM_CENTER);
        loadGridWithNewTiles();
        grid.setGridLinesVisible(true);

        //======== HBOX
        hbox.setAlignment(Pos.TOP_CENTER);
        hbox.getChildren().addAll(scoreBlack, scoreBox,scoreWhite);
        hbox.setSpacing(10);
        //== Labels
        //= Scores
        scoreBlack.setText("Black\n" + 0);
        scoreWhite.setText("White\n" + 0);
        Font scoreFont = new Font("Cambria", 36);
        scoreBlack.setFont(scoreFont);
        scoreWhite.setFont(scoreFont);
        scoreBlack.setTextAlignment(TextAlignment.CENTER);
        scoreWhite.setTextAlignment(TextAlignment.CENTER);
        scoreBlack.setTextFill(Color.BLACK);
        scoreWhite.setTextFill(Color.WHITE);

        //= Weight
        scoreBox.setAlignment(Pos.CENTER);
        scoreBox.setSpacing(10);
        scoreBox.setMinWidth(60);
        weightScore.setFont(new Font(22));
        weightScore.setTextFill(Paint.valueOf("#7f886e"));
        weightScore.setText(" ");
        weightScore.setAlignment(Pos.BOTTOM_CENTER);
        turnLabel.setFont(new Font(18));
        turnLabel.setTextFill(Paint.valueOf("#7f886e"));
        turnLabel.setText(" ");
        turnLabel.setAlignment(Pos.BOTTOM_CENTER);
        scoreBox.getChildren().addAll(turnLabel, weightScore);
    }

    public void newGame(){
        System.out.println("@" + Thread.currentThread().getName() + " >> Sending REQUEST to start new game");
        game = new Othello(this);

        showSlider();
    }

    public void newSimulation(){
        //TODO seperate human player from display so the display also can act as a spectator.
        System.out.println("@" + Thread.currentThread().getName() + " >> Sending REQUEST to start new simulated game");
        game = new Othello();
        game.addSpectator(this);
        showSlider();
    }

    private void showSlider(){
        sliderBox.setVisible(true);
        game.setDelay((long) slider.getValue());
        pauseAndPlayButton.setVisible(true);
        pauseAndPlayButton.fire();
    }

    @Override
    public String getName() {
        return "Empa";
    }


    private int mySide;
    @Override
    public void setSide(int side, String notificationName) {
        this.mySide = side;
        this.notificationName = notificationName;
        Platform.runLater(this::printNotificationReceipt);
    }
    private void printNotificationReceipt(){
        System.out.println("@" + Thread.currentThread().getName() + " << " + notificationName + " received");
    }

    private String notificationName;
    @Override
    public void updatedBoardNotification(String notificationName) {
        this.notificationName = notificationName;
        booleanProperty.setValue(!booleanProperty.get());
        Platform.runLater(this::updateScreen);
    }

    private int currentSide;
    private boolean myTurn;
    @Override
    public void playing(int side, String notificationName) {
        this.currentSide = side;
        this.notificationName = notificationName;
        if(side == mySide) myTurn = true;
        Platform.runLater(this::updateSide);
    }


    private void updateSide(){
        printNotificationReceipt();
        if(currentSide == -1){
            turnLabel.setText("Black");
        } else if (currentSide == 1) {
            turnLabel.setText("White");
        } else {
            turnLabel.setText(" ");
        }

    }

    private void updateScreen(){
        updateBoard();
        updateScores();
    }

    private void updateBoard(){
        board = game.getBoard();
        Tile tile;
        for (int i = 0; i < board.length * board.length; i++) {
            tile = (Tile) (grid.getChildren().get(i));
            tile.draw(board[row(i)][col(i)]);
        }
        System.out.println("@" + Thread.currentThread().getName() + " ! Screen Updated");
    }

    private void updateScores(){
        scoreBlack.setText(game.getBlackName()+"\n" + game.getBlackScore());
        scoreWhite.setText(game.getWhiteName()+"\n" + game.getWhiteScore());
        weightScore.setText("" + game.getBoardWeight());
        System.out.println("@" + Thread.currentThread().getName() + " ! Scores Updated");
    }


    public void values(boolean show){
        showValues=show;
        updateBoard();
    }

    public void hints(boolean show){
        showHints = show;
        updateBoard();
    }

    private void loadGridWithNewTiles(){
        for(int i = 0; i < 64; i++){
            grid.add(new Tile(), col(i),row(i));
        }
    }

    private int row(int i){
        return i / 8;
    }
    private int col(int i){
        return i % 8;
    }

    private void handleInput(Tile t){
        if(!myTurn) return;

        int i = grid.getChildren().indexOf(t);
        int r = row(i);
        int c = col(i);
        System.out.println("@" + Thread.currentThread().getName() + " <> Handling click on tile index "+ i + " --> (row: " + r + ", col: " + c + ")");

        if(board[r][c] <= 1) return;

        try {
            game.requestToMakeMove(i,this);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private final class Tile extends Pane{
        private final int TILE_WIDTH = 50;
        private final int BRICK_WIDTH = TILE_WIDTH/2;
        private Rectangle background = new Rectangle(TILE_WIDTH, TILE_WIDTH);
        private Circle brick = new Circle( BRICK_WIDTH - 5);
        private Label value = new Label();


        Tile() {
            brick.setCenterY(BRICK_WIDTH);
            brick.setCenterX(BRICK_WIDTH);
            brick.setFill(Color.TRANSPARENT);
            background.setFill(Paint.valueOf("#7f886e"));
            getChildren().addAll(background,brick,value);
            setOnMouseClicked(new clickHandler());
        }

        private void draw(int value) {
            drawBackground(value);
            drawBrick(value);
            drawValue(value);
        }

        private void drawBackground(int value) {
            background.setFill(Paint.valueOf(
                    value > 1 && showHints ? "#a6ba5b" : "#7f886e"
            ));
        }

        private void drawBrick(int value) {
            brick.setFill( value == 1 ? Color.WHITE :
                            value == -1 ? Color.BLACK :
                                    Color.TRANSPARENT
            );
        }

        private void drawValue(int value) {
            this.value.setText( showValues ? Integer.toString(value) : "");
        }

        class clickHandler implements EventHandler<MouseEvent> {
            @Override
            public void handle(MouseEvent e) {
                GameDisplay.this.handleInput(((Tile) e.getSource()));
            }
        }

    }
}

