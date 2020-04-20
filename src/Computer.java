import java.util.Random;

public class Computer implements Player, Runnable{
    private int[][] board;
    private Othello game;
    private String name;

    Computer(Othello game, String name){this.game = game; this.name = name;}

    @Override
    public void run() {

    }

    private int mySide;
    @Override
    public void setSide(int side, String notificationName) {
        this.mySide = side;
        this.notificationName = notificationName;
        new Thread(this::sendReceipt, getName()).start();
    }

    private String notificationName;
    private void sendReceipt(){
        Othello.addRecieptToLog(notificationName);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void updatedBoardNotification(String notificationName) {
        this.notificationName = notificationName;
        this.board = game.getBoard();
        new Thread(this::sendReceipt, getName()).start();
    }

    private int playing;
    @Override
    public void playing(int side, String notificationName) {
        this.playing = side;
        this.notificationName = notificationName;
        new Thread(this::generateMove, getName()).start();
    }

    private void generateMove() {
        sendReceipt();
        if (playing != mySide) return;
        try {
            System.out.println("@" + Thread.currentThread().getName() + " ...Waiting " + duration + " millisec...");
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //todo make MINMAX algorithm
        int move = theOpportunist();

        submitMove(move);
    }

    private long duration;
    public void setDuration(long timeInMillis){
        this.duration = timeInMillis;
    }

    private void submitMove(int move){
        try{
            game.requestToMakeMove(move, this);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static int row(int i){
        return i / 8;
    }

    private static int col(int i){
        return i % 8;
    }


    private int theOpportunist() {
        int tmp = -1;
        Random random = new Random();
        random.nextBoolean();
        int val;
        int max = 1;
        for(byte i = 0; i < 8*8; i++) {
            val = board[row(i)][col(i)];
            if (val > 1) {
                if (val == max) {
                    if (random.nextBoolean()) {
                        tmp= i;
                    }
                } else if (val > max) {
                    max = val;
                    tmp=i;
                }
            }
        }
        return tmp;
    }





//
//
//
//    private int score(byte[][] board){
//        int sum = 0;
//        for(int r = 0; r<board.length; r++){
//            for(int c = 0; c<board.length; c++){
//                if(board[r][c] == 1 || board[r][c] == -1){
//                    sum += board[r][c];
//                }
//            }
//        }
//        return sum;
//    }
//
//    private ArrayList<Integer> getBestOpportunities(byte[][] board){
//        ArrayList<Integer> tmp = new ArrayList<>(4);
//        int val;
//        int max = 1;
//        for(int i = 0; i < Othello.SIZE; i++) {
//            val = board[row((byte)i)][col((byte)i)];
//            if (val > 1) {
//                if(val > max){
//                    tmp.clear();
//                    max = val;
//                }
//                tmp.add(i);
//                max = val;
//            }
//        }
//        return tmp;
//    }
//
//    private int minMaxDriver(byte[][] board){
//        minMax(board, 0);
//    }
//
//    private int minMax(byte[][] board, int depth) {
//        //update board
//        int score = score(board);
//        ArrayList<Integer> bestMoves = getBestOpportunities(board);
//
//        //terminating condition
//        if(depth == 2) return index;
//
//        //if current is maximizer
//
//        return getIndexOfBestOpportunity(board);
//
//
//
//    }
//
//
//
//
////
//
//    static int minimax(int depth, int nodeIndex, boolean  isMax,
//                       int scores[], int h)
//    {
//        // Terminating condition. i.e leaf node is reached
//        if (depth == h)
//            return scores[nodeIndex];
//
//        // If current move is maximizer, find the maximum attainable
//        // value
//        if (isMax)
//            return Math.max(minimax(depth+1, nodeIndex*2, false, scores, h),
//                    minimax(depth+1, nodeIndex*2 + 1, false, scores, h));
//
//            // Else (If current move is Minimizer), find the minimum
//            // attainable value
//        else
//            return Math.min(minimax(depth+1, nodeIndex*2, true, scores, h),
//                    minimax(depth+1, nodeIndex*2 + 1, true, scores, h));
//    }
//
//    // A utility function to find Log n in base 2
//    static int log2(int n)
//    {
//        return (n==1)? 0 : 1 + log2(n/2);
//    }
//
//// Driver code
//
//    public static void main (String[] args) {
//        // The number of elements in scores must be
//        // a power of 2.
//        int scores[] = {3, 5, 2, 9, 12, 5, 23, 23};
//        int n = scores.length;
//        int h = log2(n);
//        int res = minimax(0, 0, true, scores, h);
//        System.out.println( "The optimal value is : "  +res);
//
//    }
}












