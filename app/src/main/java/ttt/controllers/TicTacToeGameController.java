package ttt.controllers;

import ttt.TicTacToeRulesEngine;
import ttt.models.Pair;
import ttt.models.Token;
import ttt.models.board.TicTacToeBoard;
import ttt.models.position.Coordinate;
import ttt.models.position.CoordinateValue;
import ttt.models.square.Square;
import ttt.models.value.Value;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeGameController<C extends CoordinateValue, P extends Coordinate<C>, V extends Value, S extends Square<V>, B extends TicTacToeBoard<C, P, V, S>> {

    TicTacToeBoardContoller<C, P, V, S, B> _boardController;
    TicTacToeRulesEngine<C, P, V, S, B> _rulesEngine;
    PlayerSequence _players;
    private TicTacToePlayerController<C, P, V, S, B> _winner;

    protected TicTacToeGameController(TicTacToeBoardContoller<C, P, V, S, B> boardController, TicTacToeRulesEngine<C, P, V, S, B> rulesEngine, TicTacToePlayerController<C, P, V, S, B> player1, TicTacToePlayerController<C, P, V, S, B> player2) {
        this._boardController = boardController;
        this._rulesEngine = rulesEngine;
        this._players = new PlayerSequence(player1, player2);
    }

    boolean move(P position, Token token) {
        if (_rulesEngine.isValidMove(_boardController.board(), position, token)) {
            _boardController.set(position, token);
            return true;
        }
        return false;
    }

    Pair<P, Token> move(TicTacToePlayerController<C, P, V, S, B> currentPlayer) {
        Pair<P, Token> playerMove = currentPlayer.getMove(_boardController.board());
        while (!move(playerMove.first(), playerMove.second())) {
            invalidMoveAction();
            playerMove = currentPlayer.getMove(_boardController.board());
        }
        return playerMove;
    }

    public void play() {
        TicTacToePlayerController<C, P, V, S, B> currentPlayer;
        Pair<P, Token> playerMove;
        do {
            currentPlayer = _players.next();
            newTurnAction(currentPlayer);
            playerMove = move(currentPlayer);
            if (_rulesEngine.isWinningMove(_boardController.board(), playerMove.first(), playerMove.second())) {
                _winner = currentPlayer;
            }

        } while (!_rulesEngine.isGameOver(_boardController.board(), playerMove.first(), playerMove.second()));

        newTurnAction(currentPlayer);

        if (_winner == null) {
            stalemateAction();
        } else {
            winnerAction(_winner);
        }
    }

    void invalidMoveAction() {
        System.out.println("Invalid move. Please enter a different move.");
    }

    void stalemateAction() {
        System.out.println("Stalemate. Better luck next time.");
    }

    void winnerAction(TicTacToePlayerController<C, P, V, S, B> winner) {
        System.out.printf("%s is the winner! Can't wait to see you again!\n", winner.player().getName());
    }

    void newTurnAction(TicTacToePlayerController<C, P, V, S, B> currentPlayer) {
        System.out.printf("\n\nCurrent Player: %s\n", currentPlayer.player().getName());
        _boardController.print();
        System.out.println("\n");
    }

    private class PlayerSequence {
        List<TicTacToePlayerController<C, P, V, S, B>> _players;
        private int currentPlayerIndex = -1;

        PlayerSequence(TicTacToePlayerController<C, P, V, S, B> player1, TicTacToePlayerController<C, P, V, S, B> player2) {
            _players = new ArrayList<>();
            _players.add(player1);
            _players.add(player2);
        }

        TicTacToePlayerController<C, P, V, S, B> next() {
            return _players.get((++currentPlayerIndex) % _players.size());
        }
    }
}
