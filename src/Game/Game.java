package Game;

import Algorightms.AlphaBeta;
import Algorightms.AlphaBeta2;
import Algorightms.MinMax;
import Algorightms.RandomMoveGenerator;

import java.util.Random;

public final class Game implements Runnable{
    private Board board = new Board();
    private Random coin = new Random();
    private Player white, black;
    private Player player1;
    private int playerOneSide;
    private int playerTwoSide;
    private Player player2;
    private Spectator spectator;
    private static Game running;
    private boolean keepRunning;
    private Log log;

    public void run(){
        if(running != null){
            running.doStop();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        running = this;

        flipCoin();
        try {
            broadcastBoard();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        keepRunning = true;

        //wait for player ready
        synchronized (this){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //game loop
        while(keepRunning){
            sendYourTurnNotification();
            try {
                waitForNextMove();
                makeMove();
                broadcastBoard();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(board.getPlaying()==0) {
                keepRunning = false;
                break;
            }
        }
        log.close();
    }

    public Game(){
        RandomMoveGenerator randomMoves = new RandomMoveGenerator();
        AlphaBeta alphaBeta = new AlphaBeta(8);
        AlphaBeta alphaBeta2 = new AlphaBeta(8);
        MinMax minMax = new MinMax(8);
        setPlayer1(randomMoves);
        setPlayer2(alphaBeta);
        log = new Log(player1.getName(), player2.getName());
        new Thread(this, "Othello").start();
    }

    public void setPlayer1(Player player){
        this.player1 = player;
    }

    public void setPlayer2(Player player){
        this.player2 = player;
    }

    public void start(){
        new Thread(this::startGame).start();
    }
    private void startGame(){
        synchronized (this){
            notify();
        }
    }

    public String getPlayerOneName(){
        return player1.getName();
    }
    public String getPlayerTwoName(){
        return player2.getName();
    }

    public void addSpectator(Spectator p){
        spectator = p;
    }

    public int getBlackScore(){
        return board.getBlackScore();
    }

    public int getWhiteScore(){
        return board.getWhiteScore();
    }

    private void doStop(){
        keepRunning = false;
    }

    private void flipCoin(){
        log.addInfo("Tossing coin to decide who starts.");
        boolean toss = coin.nextBoolean();

        if(toss){
            white = player1;
            black = player2;
            playerOneSide = 1;
            playerTwoSide = -1;
        } else {
            white = player2;
            black = player1;
            playerOneSide = -1;
            playerTwoSide = 1;
        }

        log.addInfo("\tBlack player: " + black.getName());
        log.addInfo("\tWhite player: " + white.getName());
    }

    public String getBlackName(){
        return black.getName();
    }

    public String getWhiteName(){
        return white.getName();
    }

    public int getBoardWeight(){
        return board.getWeight();
    }

    private void broadcastBoard() throws InterruptedException{
        Board b = new Board(board);
        ScoreBoard sb = new ScoreBoard(this, board);
        player1.update(b);
        player2.update(b);
        if(spectator != null){
            spectator.update(sb);
        }
        log.addOutgoing("BOARD_UPDATE SENT");

        Thread.sleep(200);
        log.addBoard(board.getBoardCopy(), board.getPlaying());
    }

    public int[][] getBoard(){
        return board.getBoardCopy();
    }

    private void sendYourTurnNotification(){
        int p = board.getPlaying();
        if(p == 1) white.yourTurnNotification(this);
        if(p == -1) black.yourTurnNotification(this);
        log.addOutgoing("YOUR_TURN SENT");
    }

    private boolean flag;
    private void waitForNextMove() throws InterruptedException{
        flag = false;
        Thread.sleep(delay);
        synchronized (this) {
            if(!flag)
                wait();
        }
    }

    private int move;
    public void requestToMakeMove(int i, Player p) throws IllegalCallerException, IllegalArgumentException, IllegalStateException{
        if(p==null) throw new NullPointerException();
        log.addIncoming( "REQUEST from " + p.getName() + " to MAKE_MOVE on " + i + ".");

        if(board.getPlaying() == -1 && p != black || board.getPlaying() == 1 && p != white  ) {
            log.addAlert("REQUEST DENIED, not that players turn.");
            throw new IllegalCallerException("Not your turn.");
        }

        if(board.getPlaying() == 0) {
            log.addAlert("REQUEST DENIED, not an ongoing game.");
            throw new IllegalStateException("!! Othello.Game is over. !!");
        }

        if(!board.isLegal(i)) {
            log.addAlert("REQUEST DENIED, not a legal move.");
            throw new IllegalArgumentException("!! Not allowed to put a piece there. !!");
        }

        log.addInfo(  "REQUEST ACCEPTED");
        synchronized (this){
            move = i;
            flag = true;
            notify();
        }
    }

    private void makeMove()throws InterruptedException{
        if(pauseFlag) {
            pauseFlag = false;
            synchronized (this) {
                wait();
            }
        }
        board.putDownPiece(move);
    }

    private long delay = 0;
    public void setDelay(long timeInMillis) {
        this.delay = timeInMillis;
    }

    private boolean pauseFlag;

    public void resume() {
        if(!pauseFlag)
            synchronized (this){ notify(); }
        pauseFlag = false;
    }

    public void pause() {
        pauseFlag = true;
    }

    public int getPlayerTwoScore() {
        if(playerTwoSide == 1){
            return board.getWhiteScore();
        } else {
            return board.getBlackScore();
        }
    }

    public int getPlayerOneScore() {
        if(playerOneSide == 1){
            return board.getWhiteScore();
        } else {
            return board.getBlackScore();
        }
    }
}
