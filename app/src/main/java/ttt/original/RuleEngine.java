package ttt.original;

import ttt.TicTacToeRulesEngine;
import ttt.models.Token;
import ttt.models.board.TicTacToeBoard;
import ttt.models.position.Coordinate1D;
import ttt.models.position.IntegerCoordinateValue;

import java.util.Optional;

public class BasicTicTacToeRuleEngine extends TicTacToeRulesEngine<IntegerCoordinateValue, Coordinate1D, BasicTicTacToeValue, BasicTicTacToeSquare, BasicTicTacToeBoard> {
    @Override
    public boolean isValidMove(BasicTicTacToeBoard board, Coordinate1D position, Token token) {
        return board.getSquare(position).isEmpty();
    }

    @Override
    public boolean hasValidMove(BasicTicTacToeBoard board) {
        for (int i = 0; i < TicTacToeBoard.BOARD_SIDE_LENGTH; i++) {
            for (int j = 0; j < TicTacToeBoard.BOARD_SIDE_LENGTH; j++) {
                if (board.getSquare(Coordinate1D.of(i, j)).isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isRowWinner(BasicTicTacToeBoard board, Coordinate1D position, Token token) {
        for (int i = 0; i < TicTacToeBoard.BOARD_SIDE_LENGTH; i ++) {
            Optional<BasicTicTacToeSquare> maybeSquare = board.getSquare(Coordinate1D.of(position.x().getInt(), i));
            if (!maybeSquare.map(square -> square.getValue().getToken().equals(token)).orElse(false)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isColWinner(BasicTicTacToeBoard board, Coordinate1D position, Token token) {
        for (int i = 0; i < TicTacToeBoard.BOARD_SIDE_LENGTH; i ++) {
            Optional<BasicTicTacToeSquare> maybeSquare = board.getSquare(Coordinate1D.of(i, position.y().getInt()));
            if (!maybeSquare.map(square -> square.getValue().getToken().equals(token)).orElse(false)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isDiagonalDownWinner(BasicTicTacToeBoard board, Coordinate1D position, Token token) {
        if (position.x().getInt() != position.y().getInt()) {
            return false;
        }
        for (int i = 0; i < TicTacToeBoard.BOARD_SIDE_LENGTH; i ++) {
            Optional<BasicTicTacToeSquare> maybeSquare = board.getSquare(Coordinate1D.of(i, i));
            if (!maybeSquare.map(square -> square.getValue().getToken().equals(token)).orElse(false)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isDiagonalUpWinner(BasicTicTacToeBoard board, Coordinate1D position, Token token) {
        if (position.x().getInt() + position.y().getInt() != TicTacToeBoard.BOARD_SIDE_LENGTH - 1) {
            return false;
        }
        for (int i = 0; i < TicTacToeBoard.BOARD_SIDE_LENGTH; i ++) {
            Optional<BasicTicTacToeSquare> maybeSquare = board.getSquare(Coordinate1D.of(TicTacToeBoard.BOARD_SIDE_LENGTH - 1 - i, i));
            if (!maybeSquare.map(square -> square.getValue().getToken().equals(token)).orElse(false)) {
                return false;
            }
        }
        return true;
    }

    public static BasicTicTacToeRuleEngine of() {
        return new BasicTicTacToeRuleEngine();
    }
}
