//package Player;
//
//import Game.Game;
//import Player;
//
//import java.util.Random;
//
//public class Computer implements Player, Runnable{
//    private int[][] board;
//    private Game game;
//    private String name;
//
//    public Computer(Game game, String name){this.game = game; this.name = name;}
//
//    @Override
//    public void run() {
//
//    }
//
//    protected int mySide;
//    @Override
//    public void setSide(int side, String notificationName) {
//        this.mySide = side;
//        this.notificationName = notificationName;
//        new Thread(this::sendReceipt, getName()).start();
//    }
//
//    private String notificationName;
//    private void sendReceipt(){
//        game.addReceiptToLog(notificationName);
//    }
//
//    @Override
//    public String getName() {
//        return name;
//    }
//
//    @Override
//    public void updatedBoardNotification(String notificationName) {
//        this.notificationName = notificationName;
//        this.board = game.getBoard();
//        new Thread(this::sendReceipt, getName()).start();
//    }
//
//    private int playing;
//    @Override
//    public void playing(int side, String notificationName) {
//        this.playing = side;
//        this.notificationName = notificationName;
//        new Thread(this::generateMove, getName()).start();
//    }
//
//    private void generateMove() {
//        sendReceipt();
//        if (playing != mySide) return;
//        try {
//            System.out.println("@" + Thread.currentThread().getName() + " ...Waiting " + duration + " millisec...");
//            Thread.sleep(duration);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        //todo make MINMAX algorithm
//        int move = generateMove(board);
//
//        submitMove(move);
//    }
//
//    private long duration;
//    public void setDuration(long timeInMillis){
//        this.duration = timeInMillis;
//    }
//
//    private void submitMove(int move){
//        try{
//            game.requestToMakeMove(move, this);
//        } catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//    }
//
//    protected static int row(int i){
//        return i / 8;
//    }
//
//    protected static int col(int i){
//        return i % 8;
//    }
//
//    protected int generateMove(int[][] board){
//        return randomChoice(board);
//    }
//
//    private int randomChoice(int[][] board){
//        int tmp = -1;
//        Random random = new Random();
//        random.nextBoolean();
//        int val;
//        for(byte i = 0; i < 8*8; i++) {
//            val = board[row(i)][col(i)];
//            if (val > 1) {
//                tmp = i;
//                if (random.nextBoolean() && random.nextBoolean()) {
//                    break;
//                }
//            }
//        }
//        return tmp;
//    }
//
//
//}
//
//
//
//
//
//
//
//
//
//
//
//
