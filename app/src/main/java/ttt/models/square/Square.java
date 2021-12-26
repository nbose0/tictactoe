package ttt.models.square;

import ttt.models.value.Value;

public class Square <V extends Value> {

    protected V _value;

    protected Square(V value) {
        this._value = value;
    }

    public V getValue() {
        return _value;
    }

    public void setValue(V value) {
        _value = value;
    }

    public boolean areSquaresSame(Square<V> squareToCompare) {
        return _value.areValuesSame(squareToCompare.getValue());
    }

}
