package ttt.models;

import ttt.models.board.TicTacToeBoard;
import ttt.models.position.Coordinate;
import ttt.models.position.CoordinateValue;
import ttt.models.square.Square;
import ttt.models.value.Value;

public class TicTacToeGame<C extends CoordinateValue, P extends Coordinate<C>, V extends Value, S extends Square<V>> {

    TicTacToeBoard<C, P, V, S> _board;
    Player[] _players;


}
