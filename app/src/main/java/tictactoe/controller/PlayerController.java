package tictactoe.controller;

import tictactoe.model.board.Board;
import tictactoe.model.position.Point;
import tictactoe.model.position.Position;

public interface PlayerController {

    Point getMove(Board board);

    <T> Position<T> getMove();

    Point getPointMove();

}
