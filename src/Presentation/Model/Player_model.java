package Presentation.Model;

import Game.Game;
import Game.Player;

public class Player_model{
    private int side;
    private int name;
    private int score;

    public void setSide(int side) {
        this.side = side;
    }

    public void setName(int name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getSide() {
        return side;
    }

    public int getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
