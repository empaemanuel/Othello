//import Game.Game;
import Presentation.View.Widget.Player_widget;
import Presentation.View.Speed_view;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


/***
 * Handling all display of the actual game, scores and tiles.
 */
public class GameDisplay{

    //private Game game;
    private int[][] board;

    private Player_widget playerWidget1GUI;
    private Player_widget playerWidget2GUI;
    private Speed_view speedView;

    private Label turnLabel = new Label();
    private Label weightScore = new Label();
    private VBox  scoreBox = new VBox();

//    GameDisplay(){
//        //=================VBOX
//        setStyle("-fx-background-color: #344b33");
//        setAlignment(Pos.CENTER);
//        HBox gameplay = new HBox();
//        HBox settings = new HBox();
//        sliderBox = new HBox();
//        getChildren().addAll(player2GUI, grid, player1GUI);
//
//        //======== GRID
//        loadGridWithNewTiles();
//        setGridLinesVisible(true);
//
//
//        //======== Othello.Computer Controller
//
//
//
//
//
//
//
//
//        //======== HBOX
//        hbox.setAlignment(Pos.TOP_CENTER);
//        hbox.getChildren().addAll(scoreBlack, scoreBox,scoreWhite);
//        hbox.setSpacing(10);
//        //== Labels
//        //= Scores
//
//
//
//
//
//
//
//        //= Weight
//        scoreBox.setAlignment(Pos.CENTER);
//        scoreBox.setSpacing(10);
//        scoreBox.setMinWidth(60);
//        weightScore.setFont(new Font(22));
//        weightScore.setTextFill(Paint.valueOf("#7f886e"));
//        weightScore.setText(" ");
//        weightScore.setAlignment(Pos.BOTTOM_CENTER);
//        turnLabel.setFont(new Font(18));
//        turnLabel.setTextFill(Paint.valueOf("#7f886e"));
//        turnLabel.setText(" ");
//        turnLabel.setAlignment(Pos.BOTTOM_CENTER);
//        scoreBox.getChildren().addAll(turnLabel, weightScore);
//    }
//
//    public void newGame(){
//        System.out.println("@" + Thread.currentThread().getName() + " >> Sending REQUEST to start new game");
//        game = new Othello(this);
//
//        showSlider();
//    }
//
//    public void newSimulation(){
//        //TODO seperate human player from display so the display also can act as a spectator.
//        System.out.println("@" + Thread.currentThread().getName() + " >> Sending REQUEST to start new simulated game");
//        game = new Othello();
//        game.addSpectator(this);
//        showSlider();
//    }
//
//    private void showSlider(){
//        sliderBox.setVisible(true);
//        game.setDelay((long) slider.getValue());
//        pauseAndPlayButton.setVisible(true);
//        pauseAndPlayButton.fire();
//    }
//
//    @Override
//    public String getName() {
//        return "Empa";
//    }
//
//
//    private int mySide;
//    @Override
//    public void setSide(int side, String notificationName) {
//        this.mySide = side;
//        this.notificationName = notificationName;
//        Platform.runLater(this::printNotificationReceipt);
//    }
//    private void printNotificationReceipt(){
//        System.out.println("@" + Thread.currentThread().getName() + " << " + notificationName + " received");
//    }
//
//    private String notificationName;
//    @Override
//    public void updatedBoardNotification(String notificationName) {
//        this.notificationName = notificationName;
//        //booleanProperty.setValue(!booleanProperty.get());
//        Platform.runLater(this::updateScreen);
//    }
//
//    private int currentSide;
//    private boolean myTurn;
//    @Override
//    public void playing(int side, String notificationName) {
//        this.currentSide = side;
//        this.notificationName = notificationName;
//        if(side == mySide) myTurn = true;
//        Platform.runLater(this::updateSide);
//    }
//
//
//    private void updateSide(){
//        printNotificationReceipt();
//        if(currentSide == -1){
//            turnLabel.setText("Black");
//        } else if (currentSide == 1) {
//            turnLabel.setText("White");
//        } else {
//            turnLabel.setText(" ");
//        }
//
//    }
//
//    private void updateScreen(){
//        updateBoard();
//        updateScores();
//    }
//
//    private void updateBoard(){
//        board = game.getBoard();
//        Tile tile;
//        for (int i = 0; i < board.length * board.length; i++) {
//            tile = (Tile) (grid.getChildren().get(i));
//            tile.draw(board[row(i)][col(i)]);
//        }
//        System.out.println("@" + Thread.currentThread().getName() + " ! Screen Updated");
//    }
//
//    private void updateScores(){
//        scoreBlack.setText(game.getBlackName()+"\n" + game.getBlackScore());
//        scoreWhite.setText(game.getWhiteName()+"\n" + game.getWhiteScore());
//        weightScore.setText("" + game.getBoardWeight());
//        System.out.println("@" + Thread.currentThread().getName() + " ! Scores Updated");
//    }
//
//
//    public void values(boolean show){
//        showValues=show;
//        updateBoard();
//    }
//
//    public void hints(boolean show){
//        showHints = show;
//        updateBoard();
//    }
//
//
//
//    private int row(int i){
//        return i / 8;
//    }
//    private int col(int i){
//        return i % 8;
//    }
//
//    private void handleInput(Tile t){
//        if(!myTurn) return;
//
//        int i = grid.getChildren().indexOf(t);
//        int r = row(i);
//        int c = col(i);
//        System.out.println("@" + Thread.currentThread().getName() + " <> Handling click on tile index "+ i + " --> (row: " + r + ", col: " + c + ")");
//
//        if(board[r][c] <= 1) return;
//
//        try {
//            game.requestToMakeMove(i,this);
//        } catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//    }
//
//
}

