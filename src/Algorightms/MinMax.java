package Algorightms;


import Game.Game;
import Game.Board;
import Game.Player;

import static java.lang.Integer.max;
import static java.lang.Integer.min;

public class MinMax implements Player {
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
        return "MinMax";
    }

    private int depth;

    public MinMax(int depth) {
        this.depth = depth;
    }

    public MinMax() {
        this.depth=6;
    }

    private void generateMove() {
        int i = minmax(board,0);
        game.requestToMakeMove(i,this);
    }

    /**
     * @param node  is a representation of the board at the current depth of play.
     */
    private int minmax(Board node, int depth) {
        int playing = node.getPlaying();

        //if node is leaf : return value of the node
        if (playing == 0 || depth == this.depth) {
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
                    int value = minmax(childNode,depth+1);
                    if(value > max){
                        max = value;
                        maxIndex = i;
                    }
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
                    int value = minmax(childNode,depth+1);
                    if(value < min){
                        min = value;
                        minIndex = i;
                    }
                }
            }
            if(depth == 0) return minIndex;
            return min;
        }
        return -1;
    }
}

