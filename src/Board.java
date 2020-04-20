public class Board {
    private int[][] board;
    private int playing = -1; //black always starts
    private int size = 8;

    Board(){
        board = new int[size][size];
        for(int i = 0; i < size*size; i++) {
            board[row(i)][col(i)] = 0;
        }
        board[3][3] = 1;
        board[3][4] = -1;
        board[4][3] = -1;
        board[4][4] = 1;
        addHints();
        countScores();
    }

    public int getPlaying(){
        return playing;
    }

    public int[][] getBoard(){
        return board;
    }

    public void putDownPiece(int i){
        putDownPiece(row(i), col(i));
        changePlayer();
        countScores();
    }

    private void changePlayer(){
        playing *= -1;
        addHints();
        if(!hasHints())
            playing *= -1;
            addHints();
            if(!hasHints())
                playing = 0;
    }

    private boolean hasHints(){
        for(int r = 0; r<size; r++){
            for( int c = 0; c<size; c++){
                if(board[r][c] > 1) return true;
            }
        }
        return false;
    }

    public boolean isLegal(int i){
        return accepted(row(i), col(i));
    }

    private void putDownPiece(int row, int col) throws IllegalArgumentException{
        if(!accepted(row,col)) throw new IllegalArgumentException("Not allowed");
        turnables(row,col,true, playing);
        board[row][col] = playing;
    }

    public void print(){
        System.out.println("@" + Thread.currentThread().getName() + ">> === BOARD PRINTER ===");
        for(int r = 0; r<size; r++){
            System.out.print("==\t");
            for(int c = 0; c<size; c++){
                System.out.print(board[r][c] + "\t");
            }
            System.out.println("==");
        }
        System.out.println("===================================");
    }

    private boolean accepted(int row, int col){
        return board[row][col] > 1;
    }

    public int row(int i) {
        return i/size;
    }

    public int col(int i){
        return i%size;
    }

    public int getWeight(){
        return scoreWhite+(scoreBlack * -1);
    }

    public int getBlackScore(){
        return scoreBlack;
    }

    public int getWhiteScore(){
        return scoreWhite;
    }

    private int scoreWhite, scoreBlack;
    private void countScores(){
        scoreWhite = 0;
        scoreBlack = 0;
        for(int r = 0; r<size; r++){
            for(int c = 0; c<size; c++){
                if(board[r][c] == 1){
                    scoreWhite++;
                }
                if(board[r][c] == -1){
                    scoreBlack++;
                }
            }
        }
    }

    private boolean outOfRange(int r, int c){
      return r < 0 || r >= size || c < 0 || c >= size;
    }

    private int turnables(int r, int c, boolean turn, int player){
        int n = -1; int e = 1; int s = 1; int w = -1; int o = 0;
        int nn = turn(r,c,n,o, turn, player);
        int ne = turn(r,c,n,e, turn, player);
        int ee = turn(r,c,o,e, turn, player);
        int se = turn(r,c,s,e, turn, player);
        int ss = turn(r,c,s,o, turn, player);
        int sw = turn(r,c,s,w, turn, player);
        int ww = turn(r,c,o,w, turn, player);
        int nw = turn(r,c,n,w, turn, player);
        return (nn+ne+ee+se+ss+sw+ww+nw);
    }

    private int turn(int r, int c, int dr, int dc, boolean turn, int player){
        return turn((r+dr),(c+dc),dr,dc,turn, player, 0);
    }

    /***
     * recursive search of adjecent bricks. Stops when getting to own brick, wall, or empty tile.
     * @param r row.
     * @param c column.
     * @param dr rows to jump per recursive call.
     * @param dc columns to jump per recursive call.
     * @param turn turns players bricks if true.
     * @param amount number of bricks that can be turned.
     */
    private int turn(int r, int c, int dr, int dc, boolean turn, int player, int amount){
        if(outOfRange(r,c)) return 0;
        int value = board[r][c];

        if(value == 0) return 0;
        if(value > 1) return 0;
        if(value==player) return amount;

        amount++;
        amount = turn((r+dr),(c+dc),dr,dc,turn,player,amount);

        //turning opponents brick
        if(turn && amount > 0) board[r][c] = player;

        return amount;

    }

    private void addHints(){
        addHints(playing);
    }
    private void addHints(int playing){
        for(int r = 0; r<board.length; r++){
            for(int c = 0; c<board.length; c++){
                int value = board[r][c];
                if(value == 0 || value > 1){
                    board[r][c] = 0;
                    int turnables = turnables(r, c, false,playing);
                    if(turnables > 0) board[r][c] = ++turnables;
                }
            }
        }
    }

}
