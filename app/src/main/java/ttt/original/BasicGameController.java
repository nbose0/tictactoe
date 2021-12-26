package ttt.original;

import ttt.controllers.TicTacToeGameController;
import ttt.models.position.Coordinate1D;
import ttt.models.position.IntegerCoordinateValue;
import ttt.original.player.PlayerController;

public class GameController extends TicTacToeGameController<IntegerCoordinateValue, Coordinate1D, BasicTicTacToeValue, BasicTicTacToeSquare, Board> {

    GameController(BoardController boardController, RuleEngine ruleEngine, PlayerController player1, PlayerController player2) {
        super(boardController, ruleEngine, player1, player2);
    }

    public static GameController of(PlayerController player1, PlayerController player2) {
        return new GameController(BoardController.of(), RuleEngine.of(), player1, player2);
    }
}
