package tictactoe.ttt.model;

import tictactoe.model.Symbol;
import tictactoe.model.board.GridBoard;

public class TicTacToeBoard extends GridBoard<Symbol, TicTacToeSquare> {

    public static final int ROW_SIZE = 3;
    public static final int COLUMN_SIZE = 3;

    public TicTacToeBoard() {
        super(new TicTacToeSquare[ROW_SIZE][COLUMN_SIZE]);
    }

    @Override
    protected TicTacToeSquare defaultSquare() {
        return new TicTacToeSquare();
    }
}

