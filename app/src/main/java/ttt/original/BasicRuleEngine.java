package ttt.original;

import ttt.TicTacToeRulesEngine;
import ttt.models.Token;
import ttt.models.board.TicTacToeBoard;
import ttt.models.position.Coordinate1D;
import ttt.models.position.IntegerCoordinateValue;

import java.util.Optional;

public class RuleEngine extends TicTacToeRulesEngine<IntegerCoordinateValue, Coordinate1D, BasicTicTacToeValue, BasicTicTacToeSquare, Board> {
    @Override
    public boolean isValidMove(Board board, Coordinate1D position, Token token) {
        return board.getSquare(position).isEmpty();
    }

    @Override
    public boolean hasValidMove(Board board) {
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
    public boolean isRowWinner(Board board, Coordinate1D position, Token token) {
        for (int i = 0; i < TicTacToeBoard.BOARD_SIDE_LENGTH; i ++) {
            Optional<BasicTicTacToeSquare> maybeSquare = board.getSquare(Coordinate1D.of(position.x().getInt(), i));
            if (!maybeSquare.map(square -> square.getValue().getToken().equals(token)).orElse(false)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isColWinner(Board board, Coordinate1D position, Token token) {
        for (int i = 0; i < TicTacToeBoard.BOARD_SIDE_LENGTH; i ++) {
            Optional<BasicTicTacToeSquare> maybeSquare = board.getSquare(Coordinate1D.of(i, position.y().getInt()));
            if (!maybeSquare.map(square -> square.getValue().getToken().equals(token)).orElse(false)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isDiagonalDownWinner(Board board, Coordinate1D position, Token token) {
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
    public boolean isDiagonalUpWinner(Board board, Coordinate1D position, Token token) {
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

    @Override
    public boolean isGameOver(Board board, Coordinate1D positionOfPreviousMove, Token token) {
        return !hasValidMove(board) || isWinningMove(board, positionOfPreviousMove, token);
    }

    @Override
    public boolean isGameOver(Board board) {
        return !hasValidMove(board) || getWinner(board).isPresent();
    }

    @Override
    public Optional<Token> getWinner(Board board) {
        for (int i = 0; i < TicTacToeBoard.BOARD_SIDE_LENGTH; i++) {
            Optional<BasicTicTacToeSquare> maybeSquare = board.getSquare(Coordinate1D.of(i, 0));
            if (maybeSquare.isPresent() && isRowWinner(board, Coordinate1D.of(i, 0), maybeSquare.get().getValue().getToken())) {
                return Optional.of(maybeSquare.get().getValue().getToken());
            }
            maybeSquare = board.getSquare(Coordinate1D.of(0, i));
            if (maybeSquare.isPresent() && isColWinner(board, Coordinate1D.of(0, i), maybeSquare.get().getValue().getToken())) {
                return Optional.of(maybeSquare.get().getValue().getToken());
            }
        }
        Optional<BasicTicTacToeSquare> maybeCenterSquare = board.getSquare(Coordinate1D.of(1,1));
        if (maybeCenterSquare.isPresent() && (isDiagonalDownWinner(board, Coordinate1D.of(1,1), maybeCenterSquare.get().getValue().getToken()) || isDiagonalUpWinner(board, Coordinate1D.of(1,1), maybeCenterSquare.get().getValue().getToken()))) {
            return Optional.of(maybeCenterSquare.get().getValue().getToken());
        }
        return Optional.empty();
    }

    public static RuleEngine of() {
        return new RuleEngine();
    }
}
