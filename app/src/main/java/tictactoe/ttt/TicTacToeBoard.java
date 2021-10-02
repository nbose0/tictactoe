package tictactoe.ttt;

class TicTacToeBoard extends Board {

    public static final int SIZE = 3;

    public TicTacToeBoard() {
        super(SIZE);
    }

    public static enum Row {
        ONE, TWO, THREE;
    }

    public static enum Column {
        ONE, TWO, THREE;
    }
}
