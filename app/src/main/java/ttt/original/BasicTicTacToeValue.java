package ttt.original;

import ttt.models.Token;
import ttt.models.square.Square;
import ttt.models.value.Value;

public class BasicTicTacToeValue implements Value {

    protected Token _token;

    private BasicTicTacToeValue(Token _token) {
        this._token = _token;
    }

    public Token getToken() {
        return _token;
    }

    @Override
    public boolean areValuesSame(Value valueToCompare) {
        if (valueToCompare instanceof BasicTicTacToeValue && ((BasicTicTacToeValue) valueToCompare).getToken() == _token) {
            return true;
        }
        return false;
    }

    public static BasicTicTacToeValue of(Token token) {
        return new BasicTicTacToeValue(token);
    }
}
