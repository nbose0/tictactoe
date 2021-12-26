package ttt.tictactoe;

import ttt.models.square.Square;

public class BasicSquare extends Square<BasicValue> {

    protected BasicSquare(BasicValue value) {
        super(value);
    }

    public static BasicSquare of(BasicValue value) {
        return new BasicSquare(value);
    }

    public void print() {
        System.out.printf(" %s ", _value.getToken().name());
    }

    public BasicSquare copy() {
        return new BasicSquare(this._value);
    }
}
