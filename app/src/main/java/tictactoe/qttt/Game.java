package tictactoe.qttt;

import tictactoe.model.Player;
import tictactoe.model.Symbol;
import tictactoe.model.position.Pair;
import tictactoe.model.position.Point;
import tictactoe.ttt.model.TicTacToeBoard;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Optional;

public class Game {

    private final Square[][] _board;

    private final Player _player1;
    private final Player _player2;

    private Player _winner;

    public Game(Player player1, Player player2) {
        _player1 = player1;
        _player2 = player2;
        _board = initBoard();
    }

    private Square[][] initBoard() {
        Square[][] board = new Square[3][3];
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = new Square();
            }
        }
        return board;
    }

    public void printState(Pair<Point, Point> previousMove, Point bigBoardMove, Point miniBoardMove, Player currentPlayer) {
        String sb = "\n\nPrevious move : " + previousMove.first() + ", " + previousMove.second() + "\n" +
                "Current player : " + currentPlayer + "\n" +
                "Move : " + bigBoardMove + ", " + miniBoardMove + "\n";
        System.out.println(sb);
        printRow();
        printWinnersBoard();
        if (_winner != null) {
            System.out.println("Winner : " + _winner + "\n");
        }
    }

    public void printRow() {
        StringBuilder sb = new StringBuilder();
        // Big board row
        sb.append("                  ||                   ||                  \n");
        for (int i = 0; i < _board.length; i++) {
            for (int k = 0; k < 3; k++) {
                for (int j = 0; j < _board[i].length; j++) {
                    sb.append(_board[i][j].rowString(k));
                    if (j == 2) {
                        sb.append("\n");
                        if (k < 2) {
                            sb.append("----------------- || ----------------- || -----------------\n");
                        }
                    } else {
                        sb.append(" || ");
                    }
                }
            }
            if (i < 2) {
                sb.append("                  ||                   ||                  \n");
                sb.append("============================================================\n");
                sb.append("                  ||                   ||                  \n");
            }
        }
        sb.append("                  ||                   ||                  \n");

        System.out.println(sb);
    }

    private void printWinnersBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < _board.length; i++) {
            for (int j = 0; j < _board[i].length; j++) {
                sb.append("  ");
                sb.append(_board[i][j].getWinner().isEmpty() ? " " : _board[i][j].getWinner().get().symbol());
                sb.append("  ");
                if (j < 2) {
                    sb.append(_board[i][j].horizontalBorder());
                }
            }
            if (i < 2) {
                sb.append(_board[i][0].verticalBorder());
            }
        }
        System.out.println(sb);
    }

    public void play() {
        Deque<Player> players = new ArrayDeque<>(List.of(_player1, _player2));
        Player currentPlayer = nextPlayer(players);
        Pair<Point, Point> previousBoardMove = Pair.of(null, null);
        while (!isGameOver()) {
            Point bigBoardMove = isBigBoardMoveFixed(previousBoardMove.second()) ? previousBoardMove.second() : getValidBigBoardMove(currentPlayer);
            Point miniBoardMove = getValidMiniBoardMove(bigBoardMove, currentPlayer);
            _board[bigBoardMove.row()][bigBoardMove.column()].setSquare(miniBoardMove.row(), miniBoardMove.column(), currentPlayer);
            if (isWinningMove(bigBoardMove.row(), bigBoardMove.column())) {
                _winner = currentPlayer;
            }
            printState(previousBoardMove, bigBoardMove, miniBoardMove, currentPlayer);
            currentPlayer = nextPlayer(players);
            previousBoardMove = Pair.of(bigBoardMove.deepCopy(), miniBoardMove.deepCopy());
        }
        if (_winner == null) {
            System.out.println("Stalemate");
        }

    }

    private Point getValidBigBoardMove(Player currentPlayer) {
        Point point;
        do {
            point = currentPlayer.getPointMove();
        } while (!isValidBigBoardMove(point));
        return point;
    }

    public boolean isValidBigBoardMove(Point point) {
        boolean a = isPositionInBounds(point.row(), point.column());
        boolean b = !_board[point.row()][point.column()].isFull();
        return a && b;
    }

    private boolean isBigBoardMoveFixed(Point previousMiniBoardMove) {
        if (previousMiniBoardMove == null) {
            return false;
        }
        return !_board[previousMiniBoardMove.row()][previousMiniBoardMove.column()].isGameOver();
    }

    private Player nextPlayer(Deque<Player> players) {
        Player currentPlayer = players.poll();
        players.offer(currentPlayer);
        return currentPlayer;
    }

    private Point getValidMiniBoardMove(Point bigBoardMove, Player currentPlayer) {
        Point point;
        do {
            point = currentPlayer.getPointMove();
        } while (!isValidMove(bigBoardMove.row(), bigBoardMove.column(), point.row(), point.column()));
        return point;
    }

    public Optional<Player> getWinner() {
        return Optional.ofNullable(_winner);
    }

    public boolean isGameOver() {
        if (isFull()) {
            return true;
        }
        for (int i = 0; i < TicTacToeBoard.ROW_SIZE; i++) {
            if (isRowWon(i)) {
                return true;
            }
        }
        for (int i = 0; i < TicTacToeBoard.COLUMN_SIZE; i++) {
            if (isColumnWon(i)) {
                return true;
            }
        }
        return isDiagonalDownWon() || isDiagonalUpWon();
    }

    public boolean isFull() {
        for (Square[] squares : _board) {
            for (Square square : squares) {
                if (!square.isFull()) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isRowWon(int row) {
        Optional<Symbol> firstSquareSymbol = _board[row][0].getWinner().map(Player::symbol);
        if (firstSquareSymbol.isEmpty()) {
            return false;
        }
        for (int i = 1; i < TicTacToeBoard.COLUMN_SIZE; i++) {
            Optional<Symbol> currentSquareSymbol = _board[row][i].getWinner().map(Player::symbol);
            if (currentSquareSymbol.isEmpty() || (currentSquareSymbol.get() != firstSquareSymbol.get())) {
                return false;
            }
        }
        return true;
    }

    private boolean isColumnWon(int column) {
        Optional<Symbol> firstSquareSymbol = _board[0][column].getWinner().map(Player::symbol);
        if (firstSquareSymbol.isEmpty()) {
            return false;
        }
        for (int i = 1; i < TicTacToeBoard.ROW_SIZE; i ++) {
            Optional<Symbol> currentSquareSymbol = _board[i][column].getWinner().map(Player::symbol);
            if (currentSquareSymbol.isEmpty() || (currentSquareSymbol.get() != firstSquareSymbol.get())) {
                return false;
            }
        }
        return true;
    }

    private boolean isDiagonalDownWon() {
        return isDiagonalDownWon(0 ,0 );
    }

    // Left to right, is the diagonal pointing down.
    private boolean isDiagonalDownWon(int row, int column) {
        if (row != column) {
            return false;
        }
        Optional<Symbol> firstSquareSymbol = _board[0][0].getWinner().map(Player::symbol);
        if (firstSquareSymbol.isEmpty()) {
            return false;
        }
        for (int i = 1; i < TicTacToeBoard.ROW_SIZE; i ++) {
            Optional<Symbol> currentSquareSymbol = _board[i][i].getWinner().map(Player::symbol);
            if (currentSquareSymbol.isEmpty() || (currentSquareSymbol.get() != firstSquareSymbol.get())) {
                return false;
            }
        }
        return true;
    }

    private boolean isDiagonalUpWon() {
        return isDiagonalUpWon(0, 2);
    }

    // Left to right, is the diagonal pointing up.
    private boolean isDiagonalUpWon(int row, int column) {
        if (row + column != 2) {
            return false;
        }
        Optional<Symbol> firstSquareSymbol = _board[0][2].getWinner().map(Player::symbol);
        if (firstSquareSymbol.isEmpty()) {
            return false;
        }
        for (int i = 1; i < TicTacToeBoard.ROW_SIZE; i ++) {
            Optional<Symbol> currentSquareSymbol = _board[i][2 - i].getWinner().map(Player::symbol);
            if (currentSquareSymbol.isEmpty() || (currentSquareSymbol.get() != firstSquareSymbol.get())) {
                return false;
            }
        }
        return true;
    }

    protected boolean isWinningMove(int row, int column) {
        return isRowWon(row) || isColumnWon(column) || isDiagonalUpWon(row, column) || isDiagonalDownWon(row, column);
    }

    public boolean isValidMove(int row, int column, int miniRow, int miniColumn) {
        boolean a = isPositionInBounds(row, column);
        boolean b = !_board[row][column].isFull();
        boolean c = _board[row][column].isValidMove(miniRow, miniColumn);
        return a && b && c;
    }

    private boolean isPositionInBounds(int row, int column) {
        return isInBounds(row, TicTacToeBoard.ROW_SIZE) && isInBounds(column, TicTacToeBoard.COLUMN_SIZE);
    }

    private boolean isInBounds(int value, int max) {
        return value >= 0 && value < max;
    }
}
