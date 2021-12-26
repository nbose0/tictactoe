package ttt.original;

import ttt.controllers.BoardContoller;
import ttt.models.Token;
import ttt.models.board.TicTacToeBoard;
import ttt.models.position.Coordinate1D;
import ttt.models.position.IntegerCoordinateValue;

import java.util.Optional;

public class BasicBoardController extends BoardContoller<IntegerCoordinateValue, Coordinate1D, BasicValue, BasicSquare, BasicBoard> {

    BasicBoardController(BasicBoard basicBoard) {
        super(basicBoard);
    }

    @Override
    public void set(Coordinate1D position, Token token) {
        _board.setSquare(position, BasicSquare.of(BasicValue.of(token)));
    }

    @Override
    public void print() {
        for (int i = 0; i < TicTacToeBoard.BOARD_SIDE_LENGTH; i++) {
            for (int j = 0; j < TicTacToeBoard.BOARD_SIDE_LENGTH; j++) {
                Optional<BasicSquare> maybeSquare = _board.getSquare(Coordinate1D.of(i, j));
                if (maybeSquare.isEmpty()) {
                    System.out.print("   ");
                } else {
                    maybeSquare.get().print();
                }
                if (j < TicTacToeBoard.BOARD_SIDE_LENGTH - 1) {
                    System.out.print("|");
                }
            }
            if (i < TicTacToeBoard.BOARD_SIDE_LENGTH - 1) {
                System.out.println("\n-----------");
            }
        }
    }

    public static void printS(BasicBoard basicBoard) {
        for (int i = 0; i < TicTacToeBoard.BOARD_SIDE_LENGTH; i++) {
            for (int j = 0; j < TicTacToeBoard.BOARD_SIDE_LENGTH; j++) {
                Optional<BasicSquare> maybeSquare = basicBoard.getSquare(Coordinate1D.of(i, j));
                if (maybeSquare.isEmpty()) {
                    System.out.print("   ");
                } else {
                    maybeSquare.get().print();
                }
                if (j < TicTacToeBoard.BOARD_SIDE_LENGTH - 1) {
                    System.out.print("|");
                }
            }
            if (i < TicTacToeBoard.BOARD_SIDE_LENGTH - 1) {
                System.out.println("\n-----------");
            }
        }
    }

    public static BasicBoardController of() {
        return new BasicBoardController(BasicBoard.of());
    }
}
