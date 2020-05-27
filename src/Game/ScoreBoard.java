package Game;

public class ScoreBoard {
    private String white;
    private String black;
    private String player1;
    private String player2;
    private int currentPlayer;
    private int scoreBlack;
    private int scoreWhite;
    private int weight;
    private int[][] board;
    private int playerOneScore;
    private int playerTwoScore;

    ScoreBoard(Game g, Board b){
        white = g.getWhiteName();
        black = g.getBlackName();
        player1 = g.getPlayerOneName();
        player2 = g.getPlayerTwoName();
        currentPlayer = b.getPlaying();
        scoreBlack = b.getBlackScore();
        scoreWhite = b.getWhiteScore();
        weight=b.getWeight();
        board = b.getBoardCopy();
        playerOneScore = g.getPlayerOneScore();
        playerTwoScore = g.getPlayerTwoScore();
    }

    public String getWhite() {
        return white;
    }

    public String getBlack() {
        return black;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public String getCurrentPlayer() {
        if(currentPlayer == 1) return white;
        if(currentPlayer == -1) return black;
        return "None";
    }

    public int getScoreBlack() {
        return scoreBlack;
    }

    public int getScoreWhite() {
        return scoreWhite;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue(int i){
        return board[i/board.length][i%board.length];
    }

    public int getScorePlayer1(){
        return playerOneScore;
    }

    public int getScorePlayer2(){
        return playerTwoScore;
    }

    public int[][] getBoard() {
        return board;
    }
}
