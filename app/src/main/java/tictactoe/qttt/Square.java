package tictactoe.qttt;

import tictactoe.model.Player;
import tictactoe.model.Symbol;
import tictactoe.ttt.model.TicTacToeBoard;

import java.util.Optional;

public class Square {
    private final Symbol[][] _miniBoard;

    private Player _winner;

    public Square() {
        _miniBoard = new Symbol[3][3];
    }

    public Square setSquare(int row, int column, Player player) {
        if (isValidMove(row, column)) {
            _miniBoard[row][column] = player.symbol();
        }
        if (isWinningMove(row, column) && _winner == null) {
            _winner = player;
        }
        return this;
    }

    public Optional<Player> getWinner() {
        return Optional.ofNullable(_winner);
    }

    public boolean isGameOver() {
        if (isFull()) {
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

    public boolean isFull() {
        for (Symbol[] symbols : _miniBoard) {
            for (Symbol symbol : symbols) {
                if (symbol == null) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isRowWon(int row) {
        Symbol firstSquareSymbol = _miniBoard[row][0];
        if (firstSquareSymbol == null) {
            return false;
        }
        for (int i = 1; i < TicTacToeBoard.COLUMN_SIZE; i ++) {
            Symbol currentSquareSymbol = _miniBoard[row][i];
            if (currentSquareSymbol == null || (currentSquareSymbol != firstSquareSymbol)) {
                return false;
            }
        }
        return true;
    }

    private boolean isColumnWon(int column) {
        Symbol firstSquareSymbol = _miniBoard[0][column];
        if (firstSquareSymbol == null) {
            return false;
        }
        for (int i = 1; i < TicTacToeBoard.ROW_SIZE; i ++) {
            Symbol currentSquareSymbol = _miniBoard[i][column];
            if (currentSquareSymbol == null || (currentSquareSymbol != firstSquareSymbol)) {
                return false;
            }
        }
        return true;
    }

    private boolean isDiagonalDownWon() {
        return isDiagonalDownWon(0 ,0 );
    }

    // Left to right, is the diagonal pointing down.
    private boolean isDiagonalDownWon(int row, int column) {
        if (row != column) {
            return false;
        }
        Symbol firstSquareSymbol = _miniBoard[0][0];
        if (firstSquareSymbol == null) {
            return false;
        }
        for (int i = 1; i < TicTacToeBoard.ROW_SIZE; i ++) {
            Symbol currentSquareSymbol = _miniBoard[i][i];
            if (currentSquareSymbol == null || (currentSquareSymbol != firstSquareSymbol)) {
                return false;
            }
        }
        return true;
    }

    private boolean isDiagonalUpWon() {
        return isDiagonalUpWon(0, 2);
    }

    // Left to right, is the diagonal pointing up.
    private boolean isDiagonalUpWon(int row, int column) {
        if (row + column != 2) {
            return false;
        }
        Symbol firstSquareSymbol = _miniBoard[0][2];
        if (firstSquareSymbol == null) {
            return false;
        }
        for (int i = 1; i < TicTacToeBoard.ROW_SIZE; i ++) {
            Symbol currentSquareSymbol = _miniBoard[i][2 - i];
            if (currentSquareSymbol == null || (currentSquareSymbol != firstSquareSymbol)) {
                return false;
            }
        }
        return true;
    }

    protected boolean isWinningMove(int row, int column) {
        return isRowWon(row) || isColumnWon(column) || isDiagonalUpWon(row, column) || isDiagonalDownWon(row, column);
    }

    public boolean isValidMove(int row, int column) {
        return isPositionInBounds(row, column) && _miniBoard[row][column] == null;
    }

    private boolean isPositionInBounds(int row, int column) {
        return isInBounds(row, TicTacToeBoard.ROW_SIZE) && isInBounds(column, TicTacToeBoard.COLUMN_SIZE);
    }

    private boolean isInBounds(int value, int max) {
        return value >= 0 && value < max;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < _miniBoard.length; i++) {
            for (int j = 0; j < _miniBoard[i].length; j++) {
                sb.append(boardValue(_miniBoard[i][j]));
                if (j < _miniBoard[i].length - 1) {
                    sb.append(horizontalBorder());
                }
            }
            if (i < _miniBoard.length - 1) {
                sb.append(verticalBorder());
            }
        }
        return sb.toString();
    }

    protected String boardValue(Symbol symbol) {
        return String.format("  %s  ", symbol == null ? " " : symbol);
    }

    protected String horizontalBorder() {
        return "|";
    }

    public String verticalBorder() {
        return "\n" + "------".repeat(_miniBoard.length) + "\n";
    }

    public String rowString(int row) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < _miniBoard[row].length; j++) {
            sb.append(boardValue(_miniBoard[row][j]));
            if (j < _miniBoard[row].length - 1) {
                sb.append(horizontalBorder());
            }
        }
        return sb.toString();
    }
}
