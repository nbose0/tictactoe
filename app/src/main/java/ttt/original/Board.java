package ttt.original;

import ttt.models.board.TicTacToeBoard;
import ttt.models.position.Coordinate1D;
import ttt.models.position.IntegerCoordinateValue;

import java.util.Optional;

public class BasicTicTacToeBoard extends TicTacToeBoard<IntegerCoordinateValue, Coordinate1D, BasicTicTacToeValue, BasicTicTacToeSquare> {

    BasicTicTacToeBoard() {
        _board = new BasicTicTacToeSquare[BOARD_SIDE_LENGTH][BOARD_SIDE_LENGTH];
    }

    @Override
    public Optional<BasicTicTacToeSquare> getSquare(Coordinate1D position) {
        return Optional.ofNullable(_board[position.x().getInt()][position.y().getInt()]);
    }

    @Override
    public void setSquare(Coordinate1D position, BasicTicTacToeSquare square) {
        _board[position.x().getInt()][position.y().getInt()] = square;
    }

    public static BasicTicTacToeBoard of() {
        return new BasicTicTacToeBoard();
    }
}
