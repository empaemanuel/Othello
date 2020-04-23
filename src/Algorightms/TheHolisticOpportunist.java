//package Algorightms;
//
//import Game.Game;
//import Computer;
//import Game.Board;
//
//import java.util.Random;
//
//public class TheHolisticOpportunist extends Computer {
//
//    public TheHolisticOpportunist(Game game){
//        super(game, "Holistic");
//    }
//
//    @Override
//    protected int generateMove(int[][] board) {
//        return theHolisticOpportunist(board);
//    }
//
//
//    private int theHolisticOpportunist(int[][] board) {
//        Random random = new Random();
//        random.nextBoolean();
//        int val;
//        int opportunities = 1000;
//        int index = -1;
//        for(int i = 0; i < 64; i++) {
//            val = board[row(i)][col(i)];
//            if (val > 1) {
//                Board tmp = new Board(board, mySide);
//                tmp.putDownPiece(i);
//                int sum = countOpportunities(tmp.getBoardCopy());
//                if(sum < opportunities){
//                    opportunities = sum;
//                    index = i;
//                }
//            }
//        }
//        return index;
//    }
//
//
//
//    private int countOpportunities(int[][] board){
//        int sum = 0;
//        for(int i = 0; i<64 ; i++){
//            if(board[row(i)][col(i)] > 1) sum++;
//        }
//        return sum;
//    }
//}
