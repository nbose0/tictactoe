package ttt.tictactoe;

import ttt.controllers.GameController;
import ttt.models.position.Coordinate1D;
import ttt.models.position.IntegerCoordinateValue;
import ttt.tictactoe.player.BasicPlayerController;

public class BasicGameController extends GameController<IntegerCoordinateValue, Coordinate1D, BasicValue, BasicSquare, BasicBoard> {

    BasicGameController(BasicBoardController basicBoardController, BasicRuleEngine basicRuleEngine, BasicPlayerController player1, BasicPlayerController player2) {
        super(basicBoardController, basicRuleEngine, player1, player2);
    }

    public static BasicGameController of(BasicPlayerController player1, BasicPlayerController player2) {
        return new BasicGameController(BasicBoardController.of(), BasicRuleEngine.of(), player1, player2);
    }
}
