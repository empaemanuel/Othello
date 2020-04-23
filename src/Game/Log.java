package Game;

final class Log {
    private String player1;
    private String player2;
    private String log;

    protected Log(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
        init();
    }

    private void init(){
        log = ("=====================================================\n"
                + "==== New game started between "+ player1 + " and " + player2 + ".\n");
    }

    protected void close(){
        log += ( "==== Game ended between " + player1 + " and " + player2 + ".\n"
                + "=====================================================" );
        printLog();
    }

    public void printLog(){
        System.out.print(log);
    }

    private void add(String msg){
        String tmp = "@" + Thread.currentThread().getName() + " : " + msg ;
        log += tmp + "\n";
    }

    protected void addOutgoing(String msg){
        add("<<<< " + msg);
    }

    protected void addIncoming(String msg){
        add(">>>> " + msg);
    }

    protected void addAlert(String msg){
        add("!!!! " + msg);
    }

    protected void addInfo(String msg){
        add("info " + msg);
    }

    protected void addBoard(int[][] board, int playing){
        String s = "=== BOARD PRINTER ===\n";
        for(int r = 0 ; r < board.length ; r++){
            for(int c = 0; c < board.length ; c++){
                s = s.concat(board[r][c] + "\t");
            }
            s = s.concat( "\n" );
        }
        String tmp = "";
        if(playing == 1) tmp = "=== CURRENT PLAYER : WHITE ===";
        if(playing == -1) tmp = "=== CURRENT PLAYER : BLACK ===";
        if(playing == 0) tmp = "=== GAME OVER ===";
        s += tmp;
        add(s);
    }

}
