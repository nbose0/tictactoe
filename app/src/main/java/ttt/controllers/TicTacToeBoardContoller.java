package ttt.controllers;

import ttt.models.board.Board;
import ttt.models.square.Square;
import ttt.models.position.Position;
import ttt.models.value.Value;

public abstract class BoardContoller <P extends Position, V extends Value, S extends Square<V>, B extends Board<P, V, S>>{

    abstract void setValue(B board, P position, V value);

    abstract boolean isBoardFull(B board);
}
