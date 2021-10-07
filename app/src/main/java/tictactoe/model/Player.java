package tictactoe.model;

import tictactoe.controller.PlayerController;
import tictactoe.model.position.Pair;
import tictactoe.model.position.Point;
import tictactoe.model.position.Position;

public class Player {

    private final String _name;
    private final Symbol _symbol;
    private final PlayerController _contoller;

    private Player(String name, Symbol symbol, PlayerController controller) {
        _name = name;
        _symbol = symbol;
        _contoller = controller;
    }

    public static Player create(String name, Symbol symbol, PlayerController controller) {
        return new Player(name, symbol, controller);
    }

    public static Player create(Symbol symbol, PlayerController controller) {
        return new Player(symbol.name(), symbol, controller);
    }

    public String name() {
        return _name;
    }

    public Symbol symbol() {
        return _symbol;
    }

    public <T> Pair<Symbol, Position<T>> getMove() {
        return Pair.of(_symbol, _contoller.getMove());
    }

    public Point getPointMove() {
        return _contoller.getPointMove();
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", _name, _symbol);
    }
}