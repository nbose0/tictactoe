package ttt.controllers;

import ttt.models.Board;
import ttt.models.Position;
import ttt.models.Square;
import ttt.models.Value;

public abstract class GameController <P extends Position, V extends Value, S extends Square<V>, B extends Board<P, V, S>>{

    BoardContoller<P, V, S, B> _boardController;

    abstract boolean isValidMove(B board, P position, V value);

    abstract boolean isWinningMove(B board, P position, V value);
}
