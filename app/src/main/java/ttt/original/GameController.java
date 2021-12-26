package ttt.original;

import ttt.controllers.TicTacToeGameController;
import ttt.models.position.Coordinate1D;
import ttt.models.position.IntegerCoordinateValue;

public class BasicTicTacToeGameController extends TicTacToeGameController<IntegerCoordinateValue, Coordinate1D, BasicTicTacToeValue, BasicTicTacToeSquare, BasicTicTacToeBoard> {

    BasicTicTacToeGameController(BasicTicTacToeBoardController boardController, BasicTicTacToeRuleEngine ruleEngine, BasicTicTacToePlayerController player1, BasicTicTacToePlayerController player2) {
        super(boardController, ruleEngine, player1, player2);
    }

    public static BasicTicTacToeGameController of(BasicTicTacToePlayerController player1, BasicTicTacToePlayerController player2) {
        return new BasicTicTacToeGameController(BasicTicTacToeBoardController.of(), BasicTicTacToeRuleEngine.of(), player1, player2);
    }
}
