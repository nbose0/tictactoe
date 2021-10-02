package tictactoe.ttt.controller;

import tictactoe.controller.PlayerController;
import tictactoe.model.position.Point;
import tictactoe.model.position.Position;
import tictactoe.ttt.model.TicTacToeBoard;

import java.util.Random;

public class RandomMovePlayerController implements PlayerController {

    private final Random _randomGenerator;

    public RandomMovePlayerController() {
        _randomGenerator = new Random(TicTacToeBoard.ROW_SIZE);
    }

    @Override
    public Position<Point> getMove() {
        return Position.of(Point.of(_randomGenerator.nextInt(), _randomGenerator.nextInt()));
    }
}
