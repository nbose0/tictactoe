package ttt.controllers;

import ttt.models.Pair;
import ttt.models.Player;
import ttt.models.Token;
import ttt.models.board.TicTacToeBoard;
import ttt.models.position.Coordinate;
import ttt.models.position.CoordinateValue;
import ttt.models.square.Square;
import ttt.models.value.Value;

public interface PlayerController<C extends CoordinateValue, P extends Coordinate<C>, V extends Value, S extends Square<V>, B extends TicTacToeBoard<C, P, V, S>> {

    Pair<P, Token> getMove(B board);

    Player player();
}
