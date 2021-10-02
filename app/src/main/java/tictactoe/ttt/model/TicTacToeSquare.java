package tictactoe.ttt.model;

import tictactoe.model.Square;
import tictactoe.model.Symbol;

public class TicTacToeSquare extends Square<Symbol> {

    public TicTacToeSquare() {}

    public TicTacToeSquare(Symbol symbol) {
        _value = symbol;
    }

    @Override
    public String toString() {
        return isEmpty() ? " " :  _value.name();
    }
}
