package ttt.models.position;

public class Coordinate<C extends CoordinateValue> implements Position, CoordinateValue {

    C _x;
    C _y;

    protected Coordinate(C x, C y) {
        this._x = x;
        this._y = y;
    }

    public C x(){
        return _x;
    }

    public C y() {
        return _y;
    }
}
