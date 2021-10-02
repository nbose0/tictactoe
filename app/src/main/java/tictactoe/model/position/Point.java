package tictactoe.model.position;

public class Point {
    private final int _row;
    private final int _column;

    private Point(int row, int column) {
        _row = row;
        _column = column;
    }

    public static Point of(int row, int column) {
        return new Point(row, column);
    }

    public int row() {
        return _row;
    }

    public int column() {
        return _column;
    }

    @Override
    public String toString() {
        return _row + ", " + _column;
    }
}