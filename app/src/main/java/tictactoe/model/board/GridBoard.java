package tictactoe.model.board;


import tictactoe.model.position.Point;
import tictactoe.model.position.Position;
import tictactoe.model.Square;
import tictactoe.utils.GridUtils;

public abstract class GridBoard<B, T extends Square<B>> implements Board<Point, B, T> {

    protected final T[][] _board;

    protected GridBoard(T[][] board) {
        _board = board;
        initBoard();
    }

    private void initBoard() {
        for (int i = 0; i < _board.length; i++) {
            for (int j = 0; j < _board[i].length; j++) {
                _board[i][j] = defaultSquare();
            }
        }
    }

    protected abstract T defaultSquare();

    public T getSquare(Position<Point> p) {
        if (isValidPosition(p)){
            return _board[p.get().row()][p.get().column()];
        }
        throw new IllegalArgumentException("Position is out of bounds.");
    }

    public void print() {
        for (int i = 0; i < _board.length; i++) {
            for (int j = 0; j < _board[i].length; j++) {
                _board[i][j].print();
                if (j < _board[i].length - 1) {
                    printHorizontalBorder();
                }
            }
            if (i < _board.length - 1) {
                printVerticalBorder();
            }
        }
    }

    protected void printHorizontalBorder() {
        System.out.print("|");
    }

    protected void printVerticalBorder() {
        System.out.println();
        for (int i = 0; i < _board.length; i++) {
            System.out.print("--------------");
        }
        System.out.println();
    }

    protected boolean isValidPosition(Position<Point> p) {
        return GridUtils.isWithinBoundsInclusive(0, _board.length - 1, p.get().row())
                && GridUtils.isWithinBoundsInclusive(0, _board[0].length - 1, p.get().column());
    }

    public void setSquare(T square, Position<Point> p) {
        this._board[p.get().row()][p.get().column()] = square;
    }

    public boolean isFull() {
        for (int i = 0; i < _board.length; i++) {
            for (int j = 0; j < _board[i].length; j++) {
                if (_board[i][j].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }
}