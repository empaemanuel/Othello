package Algorightms;

import Game.Board;
import Game.Game;
import Game.Player;
import java.util.Random;

public class RandomMoveGenerator implements Player{
    private Board board;

    @Override
    public void update(Board b) {
        this.board = b;
    }

    private Game game;
    @Override
    public void yourTurnNotification(Game game) {
        this.game = game;
        new Thread(this::generateMove).start();
    }

    @Override
    public String getName() {
        return "Randomizer";
    }

    private void generateMove(){
        int move = randomMoveGenerator();
        game.requestToMakeMove(move, this);
    }

    public int randomMoveGenerator(){
        int tmp = -1;
        Random random = new Random();
        random.nextBoolean();
        int val;
        for(byte i = 0; i < 64; i++) {
            val = board.getValue(i);
            if (val > 1) {
                tmp = i;
                if (random.nextBoolean() && random.nextBoolean()) {
                    break;
                }
            }
        }
        return tmp;
    }


}
