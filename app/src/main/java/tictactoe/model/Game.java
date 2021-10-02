package tictactoe.model;

import tictactoe.model.board.Board;
import tictactoe.model.position.Pair;
import tictactoe.model.position.Position;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Optional;

public abstract class Game<C, B, T extends Square<B>, D extends Board<C, B, T>> {

    protected final D _board;
    protected final List<Player> _players;
    protected Deque<Player> _playerSequence;
    protected Player _winner;

    protected Game(D board, List<Player> players) {
        _board = board;
        _players = players;
        _playerSequence = new ArrayDeque<>(_players);
    }

    public D board() {
        return _board;
    }

    public List<Player> players() {
        return _players;
    }

    public boolean hasWinner() {
        return _winner != null;
    }

    public Optional<Player> getWinner() {
        return Optional.ofNullable(_winner);
    }

    public void printState(Player currentPlayer) {
        System.out.printf("Current Player : %s\n\n", currentPlayer);
        _board.print();
        System.out.println("\n");
    }

    public void printWinner() {
        if (hasWinner()) {
            System.out.println("And the winner is " + _winner);
        } else {
            System.out.println("It's a draw.");
        }
    }

    public Player play(boolean print) {
        Player currentPlayer = _playerSequence.poll();
        while (!isGameOver()) {
            Pair<Symbol, Position<C>> move;
            do {
                move = currentPlayer.getMove();
            } while (!isValidMove(move.second()));
            _board.setSquare(createSquareFromMove(move), move.second());
            _winner = isWinningMove(move.second()) ? currentPlayer : null;
            if (print) {
                printState(currentPlayer);
            }
            currentPlayer = switchPlayer(currentPlayer);
        }
        if (print) {
            printWinner();
        }
        return _winner;
    }

    private Player switchPlayer(Player currentPlayer) {
        _playerSequence.addLast(currentPlayer);
        return _playerSequence.poll();
    }

    public abstract T createSquareFromMove(Pair<Symbol, Position<C>> move);

    protected abstract boolean isWinningMove(Position<C> move);

    public abstract boolean isValidMove(Position<C> move);

    public abstract boolean isGameOver();


}