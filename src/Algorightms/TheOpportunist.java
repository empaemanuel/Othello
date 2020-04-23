//package Algorightms;
//
//import Game.Game;
//import Computer;
//
//import java.util.Random;
//
//public class TheOpportunist extends Computer {
//
//    TheOpportunist(Game game, String name){
//        super(game, name);
//    }
//
//    @Override
//    protected int generateMove(int[][] board) {
//        return theOpportunist(board);
//    }
//
//
//    private int theOpportunist(int[][] board) {
//        int tmp = -1;
//        Random random = new Random();
//        random.nextBoolean();
//        int val;
//        int max = 1;
//        for(int i = 0; i < 64; i++) {
//            val = board[row(i)][col(i)];
//            if (val > 1) {
//                if (val == max) {
//                    if (random.nextBoolean()) {
//                        tmp= i;
//                    }
//                } else if (val > max) {
//                    max = val;
//                    tmp=i;
//                }
//            }
//        }
//        return tmp;
//    }
//}
