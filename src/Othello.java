import java.util.Random;

public class Othello implements Runnable{
    private Board board = new Board();
    private Random coin = new Random();
    private Player white, black;
    private Player player1;
    private Player player2;
    private Player spectator;
    private static Othello running;
    private boolean keepRunning;

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


        addToLog("==================================================");
        addToLog("==== Incoming game request from " + player1.getName() + ".");
        addToLog("==== New game started between "+ player1.getName() + " and " + player2.getName() + ".");

        flipCoin();
        sendSideInformation();

        keepRunning = true;

        //game loop
        while(keepRunning){
            sendBoardUpdatedNotification();
            if(pause){
                synchronized (this) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            if(board.getPlaying()==0) {
                keepRunning = false;
                break;
            }
            sendNotificationToPlaying();
            waitForNextMove();
            makeMove();
        }
        addToLog(" ! Game ended between " + player1.getName() + " and " + player2.getName() + ".");
        addToLog("\tBlack player (" + black.getName() + "): " + board.getBlackScore() + ".");
        addToLog("\tWhite player (" + white.getName() + "): " + board.getWhiteScore() + ".");
    }

    Othello(Player player1){
        this.player1 = player1;
        this.player2 = new Computer(this, "Computer");
        new Thread(this, "Othello").start();
    }

    Othello(){
        this.player1 = new Computer(this, "MinMax");
        this.player2 = new Computer(this, "Computer");
        new Thread(this, "Othello").start();
    }

    private boolean pause = false;
    public void pause(){
        pause = true;
    }


    public void resume(){
        synchronized (this){
            pause = false;
            notify();
        }
    }
    public void addSpectator(Player p){
        spectator = p;
    }

    public void setDelay(long timeInMillis){
        if(player1 instanceof Computer){
            ((Computer) player1).setDuration(timeInMillis);
        }
        if(player2 instanceof Computer){
            ((Computer) player2).setDuration(timeInMillis);
        }
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
        addToLog(" ! Tossing coin.");
        boolean toss = coin.nextBoolean();

        if(toss){
            white = player1;
            black = player2;
        } else {
            white = player2;
            black = player1;
        }

        System.out.println("\tBlack player: " + black.getName());
        System.out.println("\tWhite player: " + white.getName());
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
    private void sendSideInformation(){
        String SIDE_NOTIFICATION = "SIDE notification";
        white.setSide(1, SIDE_NOTIFICATION);
        black.setSide(-1, SIDE_NOTIFICATION);
        addNotificationToLog(SIDE_NOTIFICATION);
    }

    private void sendBoardUpdatedNotification(){
        String BOARD_UPDATE_NOTIFICATION = "BOARD_UPDATE notification";
        white.updatedBoardNotification(BOARD_UPDATE_NOTIFICATION);
        black.updatedBoardNotification(BOARD_UPDATE_NOTIFICATION);
        if(spectator != null){
            spectator.updatedBoardNotification(BOARD_UPDATE_NOTIFICATION);
        }
        addNotificationToLog(BOARD_UPDATE_NOTIFICATION);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        printBoard();
    }

    public static void addRecieptToLog(String notification){
        addToLog(" << " + notification + " received.");
    }

    private static void addNotificationToLog(String notification){
        addToLog(" >> " + notification + " sent to players.");
    }
    private static void addToLog(String s){
        System.out.println("@" + Thread.currentThread().getName() + s);
    }

    private void printBoard(){
        board.print();
    }

    public int[][] getBoard(){
        return board.getBoard();
    }

    private void sendNotificationToPlaying(){
        flag = false;
        int p = board.getPlaying();
        String PLAY_NOTIFICATION = "PLAY notification";
        white.playing(p, PLAY_NOTIFICATION);
        black.playing(p, PLAY_NOTIFICATION);
        if(spectator != null ){
            spectator.playing(p, PLAY_NOTIFICATION);
        }
        addNotificationToLog(PLAY_NOTIFICATION);
    }

    private void waitForNextMove(){
        String playing = "";
        if(board.getPlaying() == 1){
            playing = white.getName();
        } else if(board.getPlaying() == -1){
            playing = black.getName();
        }
        addToLog( " ! Waiting for " + playing + " to make a move.");
        synchronized (this) {
            try {
                flag = true;
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean flag;
    private int move;
    public void requestToMakeMove(int i, Player p) throws IllegalCallerException, IllegalArgumentException, IllegalStateException{
        if(p==null) throw new NullPointerException();
        addToLog( " << REQUEST to put down piece on index " + i +".");

        if(board.getPlaying() == -1 && p != black || board.getPlaying() == 1 && p != white  )
            throw new IllegalCallerException("!! Not your turn. !!");

        if(board.getPlaying() == 0)
            throw new IllegalStateException("!! Game is over. !!");

        if(!board.isLegal(i))
            throw new IllegalArgumentException("!! Not allowed to put a piece there. !!");

        addToLog(  " ! REQUEST ACCEPTED");
        while(!flag){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        synchronized (this){
            move = i;
            notify();
        }
    }

    private void makeMove(){
        board.putDownPiece(move);
    }

}
