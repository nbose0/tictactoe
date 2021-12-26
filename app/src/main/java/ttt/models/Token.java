package ttt.models;

public enum Token {
    X, O;

    public Token next() {
        return Token.values()[(this.ordinal() + 1) % Token.values().length];
    }
}
