package ttt.original;

import ttt.models.board.TicTacToeBoard;
import ttt.models.position.Coordinate1D;
import ttt.models.position.IntegerCoordinateValue;

import java.util.Optional;

public class Board extends TicTacToeBoard<IntegerCoordinateValue, Coordinate1D, BasicTicTacToeValue, BasicTicTacToeSquare> {

    Board() {
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

    public static Board of() {
        return new Board();
    }

    public Board copy() {
        Board boardCopy = new Board();
        for (int i = 0; i < TicTacToeBoard.BOARD_SIDE_LENGTH; i++) {
            for (int j = 0; j < TicTacToeBoard.BOARD_SIDE_LENGTH; j++) {
                if (getSquare(Coordinate1D.of(i, j)).isPresent()) {
                    boardCopy.setSquare(Coordinate1D.of(i, j), getSquare(Coordinate1D.of(i, j)).map(BasicTicTacToeSquare::copy).get());
                }
            }
        }
        return boardCopy;
    }

    @Override
    public boolean areBoardsSame(TicTacToeBoard<IntegerCoordinateValue, Coordinate1D, BasicTicTacToeValue, BasicTicTacToeSquare> boardToCompare) {
        for (int i = 0; i < BOARD_SIDE_LENGTH; i++) {
            for (int j = 0; j < BOARD_SIDE_LENGTH; j++) {
                Coordinate1D coordinate = Coordinate1D.of(i, j);
                if (getSquare(coordinate).isEmpty() && boardToCompare.getSquare(coordinate).isEmpty()) {
                } else if (!getSquare(coordinate).map(square -> boardToCompare.getSquare(coordinate).map(square::areSquaresSame).orElseGet(() -> false)).orElseGet(() -> false)) {
                    return false;
                }
            }
        }
        return true;
    }
}
