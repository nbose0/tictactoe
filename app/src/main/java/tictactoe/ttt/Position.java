package tictactoe.ttt;

class Position {

    private final int _row;
    private final int _column;

    private Position(int row, int column) {
        _row = row;
        _column = column;
    }

    public static Position of(int row, int column) {
        return new Position(row, column);
    }

    public int row() {
        return _row;
    }

    public int column() {
        return _column;
    }
}
