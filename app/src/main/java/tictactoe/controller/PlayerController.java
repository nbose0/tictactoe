package tictactoe.controller;

import tictactoe.model.position.Point;
import tictactoe.model.position.Position;

public interface PlayerController {

    <T> Position<T> getMove();


    Point getPointMove();

}
