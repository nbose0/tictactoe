package ttt.tictactoe;

import ttt.models.board.TicTacToeBoard;
import ttt.models.position.Coordinate1D;
import ttt.models.position.IntegerCoordinateValue;

import java.util.Optional;

public class BasicBoard extends TicTacToeBoard<IntegerCoordinateValue, Coordinate1D, BasicValue, BasicSquare> {

    BasicBoard() {
        _board = new BasicSquare[BOARD_SIDE_LENGTH][BOARD_SIDE_LENGTH];
    }

    @Override
    public Optional<BasicSquare> getSquare(Coordinate1D position) {
        return Optional.ofNullable(_board[position.x().getInt()][position.y().getInt()]);
    }

    @Override
    public void setSquare(Coordinate1D position, BasicSquare square) {
        _board[position.x().getInt()][position.y().getInt()] = square;
    }

    public static BasicBoard of() {
        return new BasicBoard();
    }

    public BasicBoard copy() {
        BasicBoard basicBoardCopy = new BasicBoard();
        for (int i = 0; i < TicTacToeBoard.BOARD_SIDE_LENGTH; i++) {
            for (int j = 0; j < TicTacToeBoard.BOARD_SIDE_LENGTH; j++) {
                if (getSquare(Coordinate1D.of(i, j)).isPresent()) {
                    basicBoardCopy.setSquare(Coordinate1D.of(i, j), getSquare(Coordinate1D.of(i, j)).map(BasicSquare::copy).get());
                }
            }
        }
        return basicBoardCopy;
    }

    @Override
    public boolean areBoardsSame(TicTacToeBoard<IntegerCoordinateValue, Coordinate1D, BasicValue, BasicSquare> boardToCompare) {
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
