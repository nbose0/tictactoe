package ttt.original;

import ttt.models.Token;
import ttt.models.value.Value;

public class BasicValue implements Value {

    protected Token _token;

    private BasicValue(Token _token) {
        this._token = _token;
    }

    public Token getToken() {
        return _token;
    }

    @Override
    public boolean areValuesSame(Value valueToCompare) {
        if (valueToCompare instanceof BasicValue && ((BasicValue) valueToCompare).getToken() == _token) {
            return true;
        }
        return false;
    }

    public static BasicValue of(Token token) {
        return new BasicValue(token);
    }
}
