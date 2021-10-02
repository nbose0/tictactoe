package tictactoe.ttt;

import java.util.Random;
import java.util.Scanner;

class TicTacToeGameController {

    private TicTacToeGame _game;

    public TicTacToeGameController(TicTacToeGame game) {
        _game = game;
    }

    public int playQuiet() {
        double numTurns = 0;
        Player currentPlayer = _game.getPlayer1();
        do {
            Position position = getNextMoveQuiet(currentPlayer);
            _game.board().move(position.row(), position.column(), currentPlayer);
            numTurns += 0.5;

            currentPlayer = currentPlayer != _game.getPlayer1() ? _game.getPlayer1() : _game.getPlayer2(); 
        } while (!isOver());
        return _game.getWinner().isPresent() ? _game.getWinner().get().getPlayerSymbolValue() : 0;
    }

    public void play() {
        double numTurns = 0;
        Player currentPlayer = _game.getPlayer1();
        do {
            _game.printBoard();
            Position position = getNextMove(currentPlayer);
            _game.board().move(position.row(), position.column(), currentPlayer);
            numTurns += 0.5;
            System.out.println("Number of turns: " + numTurns);

            currentPlayer = currentPlayer != _game.getPlayer1() ? _game.getPlayer1() : _game.getPlayer2(); 
        } while (!isOver());
        _game.printBoard();
        printWinner();
        System.out.println("\nThat's the game!");
    }

    public void printWinner() {
        if (_game.getWinner().isPresent()) {
            System.out.println();
            System.out.println("And the winner is " + _game.getWinner().get().name() + "!");
            System.out.println();
        } else {
            System.out.println();
            System.out.println("Gosh dang it. Stalemate. Better luck next time!");
            System.out.println();
        }
    }

    public void printBoard() {
        System.out.println();
        _game.board().printBoard();
        System.out.println();
    }

    private Position getNextMoveQuiet(Player currentPlayer) {
        int row, column;
        do {
            row = currentPlayer.getRow(false);
            column = currentPlayer.getColumn(false);
        } while (!validInputQuiet(row, column));
        return Position.of(row, column);
    }

    private boolean validInputQuiet(int row, int column) {
        if (_game.board().isSquareEmpty(row, column)) return true;
        return false;
    }

    private Position getNextMove(Player currentPlayer) {
        System.out.println("Alright Player " + currentPlayer.name() + ", it's your turn!");
        int row, column;
        do {
            row = currentPlayer.getRow();
            column = currentPlayer.getColumn();
        } while (!validInput(row, column));
        return Position.of(row, column);
    }

    private boolean validInput(int row, int column) {
        if (_game.board().isSquareEmpty(row, column)) return true;
        System.out.println("Looks like that spot is already taken. Try another.");
        return false;
    }

    public boolean isOver() {
        // check rows
        for (TicTacToeBoard.Row r : TicTacToeBoard.Row.class.getEnumConstants()) {
            if (isRowWon(r)) {
                _game.setWinner(_game.board().get(r.ordinal(), 0));
                return true;
            }
        }
        // check columns
        for (TicTacToeBoard.Column c : TicTacToeBoard.Column.class.getEnumConstants()) {
            if (isColumnWon(c)) {
                _game.setWinner(_game.board().get(0, c.ordinal()));
                return true;
            }
        }
        // check diagonals
        for (TicTacToeBoard.Column c : TicTacToeBoard.Column.class.getEnumConstants()) {
            if (isDiagonalWon(c)) {
                _game.setWinner(_game.board().get(0, c.ordinal()));
                return true;
            }
        }
        // check stalemate
        return _game.board().isFull();
    }

    public boolean isRowWon(TicTacToeBoard.Row r) {
        int row = r.ordinal();
        int firstValue = _game.board().get(row, 0);
        for (int column = 0; column < TicTacToeBoard.SIZE; column++) {
            if (_game.board().isSquareEmpty(row, column) || _game.board().get(row, column) != firstValue) {
                return false;
            }
        }
        return true;
    }

    public boolean isColumnWon(TicTacToeBoard.Column col) {
        int column = col.ordinal();
        int firstValue = _game.board().get(0, column);
        for (int row = 0; row < TicTacToeBoard.SIZE; row++) {
            if (_game.board().isSquareEmpty(row, column) || _game.board().get(row, column) != firstValue) {
                return false;
            }
        }
        return true;
    }

    public boolean isDiagonalWon(TicTacToeBoard.Column col) {
        if (col == TicTacToeBoard.Column.TWO) {
            return false;
        }
        int column = col.ordinal();
        int firstValue = _game.board().get(0,column);
        for (int row = 0; row < TicTacToeBoard.SIZE; row++) {
            if (_game.board().isSquareEmpty(row, column) || _game.board().get(row, column) != firstValue) {
                return false;
            }
            column = col == TicTacToeBoard.Column.ONE ? column + 1 : column - 1;
        }
        return true;
    }


    public TicTacToeGameController reset() {
        _game.reset();
        return this;
    }
}
