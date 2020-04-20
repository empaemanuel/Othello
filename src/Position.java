public final class Position {
    private byte r,c;
    Position() {
        this.r = -1;
        this.c = -1;
    }
    public void set(int row, int col){
        r= (byte) row; c= (byte) col;
    }

    public byte row(){
        return r;
    }
    public byte col(){
        return c;
    }
}
