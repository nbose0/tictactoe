package ttt.original;

import ttt.controllers.TicTacToeBoardContoller;
import ttt.models.Token;
import ttt.models.board.TicTacToeBoard;
import ttt.models.position.Coordinate1D;
import ttt.models.position.IntegerCoordinateValue;

import java.util.Optional;

public class BoardController extends TicTacToeBoardContoller<IntegerCoordinateValue, Coordinate1D, BasicTicTacToeValue, BasicTicTacToeSquare, Board> {

    BoardController(Board board) {
        super(board);
    }

    @Override
    public void set(Coordinate1D position, Token token) {
        _board.setSquare(position, BasicTicTacToeSquare.of(BasicTicTacToeValue.of(token)));
    }

    @Override
    public void print() {
        for (int i = 0; i < TicTacToeBoard.BOARD_SIDE_LENGTH; i++) {
            for (int j = 0; j < TicTacToeBoard.BOARD_SIDE_LENGTH; j++) {
                Optional<BasicTicTacToeSquare> maybeSquare = _board.getSquare(Coordinate1D.of(i, j));
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

    public static void printS(Board board) {
        for (int i = 0; i < TicTacToeBoard.BOARD_SIDE_LENGTH; i++) {
            for (int j = 0; j < TicTacToeBoard.BOARD_SIDE_LENGTH; j++) {
                Optional<BasicTicTacToeSquare> maybeSquare = board.getSquare(Coordinate1D.of(i, j));
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

    public static BoardController of() {
        return new BoardController(Board.of());
    }
}
