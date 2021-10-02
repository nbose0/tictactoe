package tictactoe.controller;

import tictactoe.model.position.Position;

public interface PlayerController {

    <T> Position<T> getMove();

}
