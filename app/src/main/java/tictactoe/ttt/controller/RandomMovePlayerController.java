package tictactoe.ttt.controller;

import tictactoe.controller.PlayerController;
import tictactoe.model.position.Point;
import tictactoe.model.position.Position;
import tictactoe.ttt.model.TicTacToeBoard;

import java.util.Random;

public class RandomMovePlayerController implements PlayerController {

    private final Random _randomGenerator;

    public RandomMovePlayerController() {
        _randomGenerator = new Random();
    }

    @Override
    public Position<Point> getMove() {
        return Position.of(Point.of(_randomGenerator.nextInt(TicTacToeBoard.ROW_SIZE), _randomGenerator.nextInt(TicTacToeBoard.ROW_SIZE)));
    }


    @Override
    public Point getPointMove() {
        int row = _randomGenerator.nextInt(TicTacToeBoard.ROW_SIZE);
        int col = _randomGenerator.nextInt(TicTacToeBoard.ROW_SIZE);
//        System.out.println("row : " + row + ", col : " + col);
        return Point.of(row, col);
    }
}
