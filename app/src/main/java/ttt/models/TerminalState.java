package ttt.models;

public enum TerminalState {
    LOSS(-1), STALEMATE(0), WIN(1);

    int _value;

    TerminalState(int value) {
        _value = value;
    }

    public int val() {
        return _value;
    }
}
