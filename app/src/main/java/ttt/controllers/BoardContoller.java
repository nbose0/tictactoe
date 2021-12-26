package ttt.controllers;

import ttt.models.Token;
import ttt.models.board.TicTacToeBoard;
import ttt.models.position.Coordinate;
import ttt.models.position.CoordinateValue;
import ttt.models.square.Square;
import ttt.models.value.Value;

public abstract class BoardContoller<C extends CoordinateValue, P extends Coordinate<C>, V extends Value, S extends Square<V>, B extends TicTacToeBoard<C, P, V, S>>{

    protected B _board;

    protected BoardContoller(B board) {
        this._board = board;
    }

    public B board() {
        return _board;
    }

    public abstract void set(P position, Token token);

    public abstract void print();
}
