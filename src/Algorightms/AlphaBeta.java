package Algorightms;

import Game.Game;
import Game.Board;
import Game.Player;

import static java.lang.Integer.max;
import static java.lang.Integer.min;

public class AlphaBeta implements Player {
    private Board board;
    private Game game;

    @Override
    public void update(Board b) {
        this.board = b;
    }

    @Override
    public void yourTurnNotification(Game game) {
        this.game = game;
        new Thread(this::generateMove).start();
    }

    @Override
    public String getName() {
        return "Alpha Beta";
    }



    private void generateMove() {
        int i = minmax(board,0,Integer.MIN_VALUE, Integer.MAX_VALUE);
        game.requestToMakeMove(i,this);
    }

    /**
     * @param node  is a representation of the board at the current depth of play.
     * @param alpha the best value that white player currently can guarantee.
     * @param beta  the best value that black player currently can guarantee.
     */
    private int minmax(Board node, int depth, int alpha, int beta) {
        int playing = node.getPlaying();

        //if node is leaf : return value of the node
        if (playing == 0 || depth == 8) {
            return node.getWeight();
        }

        //MAX player (white)
        if (playing == 1) {
            int max = Integer.MIN_VALUE;
            int maxIndex = -1;

            //for each child node
            for (int i = 0; i < 64; i++) {
                if (node.getValue(i) > 1) {
                    Board childNode = new Board(node);
                    childNode.putDownPiece(i);
                    int value = minmax(childNode,depth+1, alpha, beta);
                    if(value > max){
                        max = value;
                        maxIndex = i;
                    }
                    alpha = max(alpha, max);
                    if (beta <= alpha) break;
                }
            }
            if(depth == 0) return maxIndex;
            return max;
        }


        //MIN player (black)
        if (playing == -1) {
            int min = Integer.MAX_VALUE;
            int minIndex = -1;
            //for each child node
            for (int i = 0; i < 64; i++) {
                if (node.getValue(i) > 1) {
                    Board childNode = new Board(node);
                    childNode.putDownPiece(i);
                    int value = minmax(childNode,depth+1,alpha, beta);
                    if(value < min){
                        min = value;
                        minIndex = i;
                    }
                    beta = min(beta, min);
                    if (beta <= alpha) break;
                }
            }
            if(depth == 0) return minIndex;
            return min;
        }
        return -1;
    }
}
