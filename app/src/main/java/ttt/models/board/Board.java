package ttt.models.board;

import ttt.models.square.Square;
import ttt.models.value.Value;
import ttt.models.position.Position;

import java.util.Optional;

public interface Board <P extends Position, V extends Value, S extends Square<V>> {

    Optional<S> getSquare(P position);

    void setSquare(P position, S square);
}
