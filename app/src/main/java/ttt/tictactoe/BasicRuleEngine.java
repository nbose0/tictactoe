package ttt.original;

import ttt.RulesEngine;
import ttt.models.Token;
import ttt.models.board.TicTacToeBoard;
import ttt.models.position.Coordinate1D;
import ttt.models.position.IntegerCoordinateValue;

import java.util.Optional;

public class BasicRuleEngine extends RulesEngine<IntegerCoordinateValue, Coordinate1D, BasicValue, BasicSquare, BasicBoard> {
    @Override
    public boolean isValidMove(BasicBoard basicBoard, Coordinate1D position, Token token) {
        return basicBoard.getSquare(position).isEmpty();
    }

    @Override
    public boolean hasValidMove(BasicBoard basicBoard) {
        for (int i = 0; i < TicTacToeBoard.BOARD_SIDE_LENGTH; i++) {
            for (int j = 0; j < TicTacToeBoard.BOARD_SIDE_LENGTH; j++) {
                if (basicBoard.getSquare(Coordinate1D.of(i, j)).isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isRowWinner(BasicBoard basicBoard, Coordinate1D position, Token token) {
        for (int i = 0; i < TicTacToeBoard.BOARD_SIDE_LENGTH; i ++) {
            Optional<BasicSquare> maybeSquare = basicBoard.getSquare(Coordinate1D.of(position.x().getInt(), i));
            if (!maybeSquare.map(square -> square.getValue().getToken().equals(token)).orElse(false)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isColWinner(BasicBoard basicBoard, Coordinate1D position, Token token) {
        for (int i = 0; i < TicTacToeBoard.BOARD_SIDE_LENGTH; i ++) {
            Optional<BasicSquare> maybeSquare = basicBoard.getSquare(Coordinate1D.of(i, position.y().getInt()));
            if (!maybeSquare.map(square -> square.getValue().getToken().equals(token)).orElse(false)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isDiagonalDownWinner(BasicBoard basicBoard, Coordinate1D position, Token token) {
        if (position.x().getInt() != position.y().getInt()) {
            return false;
        }
        for (int i = 0; i < TicTacToeBoard.BOARD_SIDE_LENGTH; i ++) {
            Optional<BasicSquare> maybeSquare = basicBoard.getSquare(Coordinate1D.of(i, i));
            if (!maybeSquare.map(square -> square.getValue().getToken().equals(token)).orElse(false)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isDiagonalUpWinner(BasicBoard basicBoard, Coordinate1D position, Token token) {
        if (position.x().getInt() + position.y().getInt() != TicTacToeBoard.BOARD_SIDE_LENGTH - 1) {
            return false;
        }
        for (int i = 0; i < TicTacToeBoard.BOARD_SIDE_LENGTH; i ++) {
            Optional<BasicSquare> maybeSquare = basicBoard.getSquare(Coordinate1D.of(TicTacToeBoard.BOARD_SIDE_LENGTH - 1 - i, i));
            if (!maybeSquare.map(square -> square.getValue().getToken().equals(token)).orElse(false)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isGameOver(BasicBoard basicBoard, Coordinate1D positionOfPreviousMove, Token token) {
        return !hasValidMove(basicBoard) || isWinningMove(basicBoard, positionOfPreviousMove, token);
    }

    @Override
    public boolean isGameOver(BasicBoard basicBoard) {
        return !hasValidMove(basicBoard) || getWinner(basicBoard).isPresent();
    }

    @Override
    public Optional<Token> getWinner(BasicBoard basicBoard) {
        for (int i = 0; i < TicTacToeBoard.BOARD_SIDE_LENGTH; i++) {
            Optional<BasicSquare> maybeSquare = basicBoard.getSquare(Coordinate1D.of(i, 0));
            if (maybeSquare.isPresent() && isRowWinner(basicBoard, Coordinate1D.of(i, 0), maybeSquare.get().getValue().getToken())) {
                return Optional.of(maybeSquare.get().getValue().getToken());
            }
            maybeSquare = basicBoard.getSquare(Coordinate1D.of(0, i));
            if (maybeSquare.isPresent() && isColWinner(basicBoard, Coordinate1D.of(0, i), maybeSquare.get().getValue().getToken())) {
                return Optional.of(maybeSquare.get().getValue().getToken());
            }
        }
        Optional<BasicSquare> maybeCenterSquare = basicBoard.getSquare(Coordinate1D.of(1,1));
        if (maybeCenterSquare.isPresent() && (isDiagonalDownWinner(basicBoard, Coordinate1D.of(1,1), maybeCenterSquare.get().getValue().getToken()) || isDiagonalUpWinner(basicBoard, Coordinate1D.of(1,1), maybeCenterSquare.get().getValue().getToken()))) {
            return Optional.of(maybeCenterSquare.get().getValue().getToken());
        }
        return Optional.empty();
    }

    public static BasicRuleEngine of() {
        return new BasicRuleEngine();
    }
}
