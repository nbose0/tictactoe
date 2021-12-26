package ttt.original;

import ttt.models.square.Square;

public class BasicTicTacToeSquare extends Square<BasicTicTacToeValue> {

    protected BasicTicTacToeSquare(BasicTicTacToeValue value) {
        super(value);
    }

    public static BasicTicTacToeSquare of(BasicTicTacToeValue value) {
        return new BasicTicTacToeSquare(value);
    }

    public void print() {
        System.out.printf(" %s ", _value.getToken().name());
    }

    public BasicTicTacToeSquare copy() {
        return new BasicTicTacToeSquare(this._value);
    }
}
