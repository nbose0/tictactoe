package tictactoe.ttt.model;

import tictactoe.model.*;
import tictactoe.model.position.Pair;
import tictactoe.model.position.Point;
import tictactoe.model.position.Position;

import java.util.List;
import java.util.Optional;

public class TicTacToeGame extends Game<Point, Symbol, TicTacToeSquare, TicTacToeBoard> {

    public TicTacToeGame(Player player1, Player player2) {
        super(new TicTacToeBoard(), List.of(player1, player2));
    }

    @Override
    public TicTacToeSquare createSquareFromMove(Pair<Symbol, Position<Point>> move) {
        return new TicTacToeSquare(move.first());
    }

    @Override
    protected boolean isWinningMove(Position<Point> move) {
        return isRowWon(move.get().row()) || isColumnWon(move.get().column()) || isDiagonalUpWon(move) || isDiagonalDownWon(move);
    }

    @Override
    public boolean isValidMove(Position<Point> move) {
        return isPositionInBounds(move) && _board.getSquare(move).isEmpty();
    }

    private boolean isPositionInBounds(Position<Point> p) {
        return isInBounds(p.get().row(), TicTacToeBoard.ROW_SIZE) && isInBounds(p.get().column(), TicTacToeBoard.COLUMN_SIZE);
    }

    private boolean isInBounds(int value, int max) {
        return value >= 0 && value < max;
    }

    @Override
    public boolean isGameOver() {
        if (_board.isFull()) {
            return true;
        }
        for (int i = 0; i < TicTacToeBoard.ROW_SIZE; i++) {
            if (isRowWon(i)) {
                return true;
            }
        }
        for (int i = 0; i < TicTacToeBoard.COLUMN_SIZE; i++) {
            if (isColumnWon(i)) {
                return true;
            }
        }
        return isDiagonalDownWon() || isDiagonalUpWon();
    }

    private boolean isRowWon(int row) {
        Optional<Symbol> firstSquareSymbol = _board.getSquare(Position.of(Point.of(row, 0))).getValue();
        if (firstSquareSymbol.isEmpty()) {
            return false;
        }
        for (int i = 1; i < TicTacToeBoard.COLUMN_SIZE; i ++) {
            Optional<Symbol> currentSquareSymbol = _board.getSquare(Position.of(Point.of(row, i))).getValue();
            if (currentSquareSymbol.isEmpty() || (currentSquareSymbol.get() != firstSquareSymbol.get())) {
                return false;
            }
        }
        return true;
    }

    private boolean isColumnWon(int column) {
        Optional<Symbol> firstSquareSymbol = _board.getSquare(Position.of(Point.of(0, column))).getValue();
        if (firstSquareSymbol.isEmpty()) {
            return false;
        }
        for (int i = 1; i < TicTacToeBoard.ROW_SIZE; i ++) {
            Optional<Symbol> currentSquareSymbol = _board.getSquare(Position.of(Point.of(i, column))).getValue();
            if (currentSquareSymbol.isEmpty() || (currentSquareSymbol.get() != firstSquareSymbol.get())) {
                return false;
            }
        }
        return true;
    }

    private boolean isDiagonalDownWon() {
        return isDiagonalDownWon(Position.of(Point.of(0,0)));
    }

    // Left to right, is the diagonal pointing down.
    private boolean isDiagonalDownWon(Position<Point> position) {
        if (position.get().row() != position.get().column()) {
            return false;
        }
        Optional<Symbol> firstSquareSymbol = _board.getSquare(Position.of(Point.of(0, 0))).getValue();
        if (firstSquareSymbol.isEmpty()) {
            return false;
        }
        for (int i = 1; i < TicTacToeBoard.ROW_SIZE; i ++) {
            Optional<Symbol> currentSquareSymbol = _board.getSquare(Position.of(Point.of(i, i))).getValue();
            if (currentSquareSymbol.isEmpty() || (currentSquareSymbol.get() != firstSquareSymbol.get())) {
                return false;
            }
        }
        return true;
    }

    private boolean isDiagonalUpWon() {
        return isDiagonalUpWon(Position.of(Point.of(0,2)));
    }

    // Left to right, is the diagonal pointing up.
    private boolean isDiagonalUpWon(Position<Point> position) {
        if (position.get().row() + position.get().column() != TicTacToeBoard.ROW_SIZE - 1) {
            return false;
        }
        Optional<Symbol> firstSquareSymbol = _board.getSquare(Position.of(Point.of(0, TicTacToeBoard.COLUMN_SIZE - 1))).getValue();
        if (firstSquareSymbol.isEmpty()) {
            return false;
        }
        for (int i = 1; i < TicTacToeBoard.ROW_SIZE; i ++) {
            Optional<Symbol> currentSquareSymbol = _board.getSquare(Position.of(Point.of(i, TicTacToeBoard.ROW_SIZE - i))).getValue();
            if (currentSquareSymbol.isEmpty() || (currentSquareSymbol.get() != firstSquareSymbol.get())) {
                return false;
            }
        }
        return true;
    }
}
