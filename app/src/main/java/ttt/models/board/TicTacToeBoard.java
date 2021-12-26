package ttt.models.board;

import ttt.models.position.Coordinate1D;
import ttt.models.position.CoordinateValue;
import ttt.models.square.Square;
import ttt.models.value.Value;
import ttt.models.position.Coordinate;

public abstract class TicTacToeBoard<C extends CoordinateValue, P extends Coordinate<C>, V extends Value, S extends Square<V>> implements Board<P, V, S> {
    public static final int BOARD_SIDE_LENGTH = 3;

    protected S[][] _board;

    public abstract boolean areBoardsSame(TicTacToeBoard<C, P, V, S> boardToCompare);
}
