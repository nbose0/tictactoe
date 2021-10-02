package tictactoe.ttt;

import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

class TicTacToeGame {

    private TicTacToeBoard _board;

    private Player _winner;
    private Player _player1;
    private Player _player2;

    public TicTacToeGame(Player player1, Player player2) {
        _board = new TicTacToeBoard();
        _player1 = player1;
        _player2 = player2;
    }

    public static TicTacToeGame create(Player player1, Player player2) {
        return new TicTacToeGame(player1, player2);
    }

    public void printBoard() {
        System.out.println();
        _board.printBoard();
        System.out.println();
    }

    public boolean hasWinner() {
        return _winner != null;
    }

    public Optional<Player> getWinner() {
        return Optional.ofNullable(_winner);
    }

    public void setWinner(int value) {
        if (value == 1) _winner = _player1;
        if (value == 2) _winner = _player2;
    }

    public void setWinner(Player _player) {
        _winner = _player;
    }

    public TicTacToeBoard board() {
        return _board;
    }

    public Player getPlayer1() {
        return _player1;
    }

    public Player getPlayer2() {
        return _player2;
    }

    public TicTacToeGame reset() {
        _board = new TicTacToeBoard();
        _winner = null;
        return this;
    }
}
