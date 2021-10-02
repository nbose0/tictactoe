package tictactoe.ttt;

class Board {
    protected final int[][] board;

    protected Board(int size) {
        board = new int[size][size];
    }

    public int get(int row, int column) {
        return board[row][column];
    }

    public Board move(int row, int column, Player player) {
        board[row][column] = player.getPlayerSymbolValue();
        return this;
    }

    public boolean isSquareEmpty(int row, int column) {
        return board[row][column] == 0;
    }

    public boolean isFull() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (isSquareEmpty(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
                if (j < board[i].length - 1) {
                    printVerticalBorder();
                }
            }
            if (i < board.length - 1) {
                printHorizontalBorder();
            }
        }
        System.out.println();
    }

    public void printVerticalBorder() {
        System.out.print("\t|\t");
    }

    public void printHorizontalBorder() {
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            System.out.print("--------------");
        }
        System.out.println();
    }
}
