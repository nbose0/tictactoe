package tictactoe.ttt;

import java.util.Random;

class Player {

    private final Random _random;
    private final String _name;
    private final Symbol _symbol;

    private Player(String name, Symbol symbol) {
        _name = name;
        _symbol = symbol;
        _random = new Random();
    }

    public static Player of(Symbol symbol) {
        return new Player(symbol.name(), symbol);
    }

    public static Player of(String name, Symbol symbol) {
        return new Player(name, symbol);
    }

    public int getRow() {
        return getRow(true);
    }

    public int getRow(boolean shouldPrint) {
        int row = _random.nextInt(TicTacToeBoard.SIZE);
        if (shouldPrint) {
            System.out.print("Row: ");
            System.out.println(row);
        }
        return row;
    }


    public int getColumn() {
        return getColumn(true);
    }

    public int getColumn(boolean shouldPrint) {
        int column = _random.nextInt(TicTacToeBoard.SIZE);
        if (shouldPrint) {
            System.out.print("Column: ");
            System.out.println(column);
        }
        return column;
    }

    public int getPlayerSymbolValue() {
        return _symbol.value();
    }

    public String name() {
        return _name;
    }

    static enum Symbol {
        X(1), O(2);

        private final int _value;

        private Symbol(int value) {
            _value = value;
        }

        public int value() {
            return _value;
        }
    }
}
