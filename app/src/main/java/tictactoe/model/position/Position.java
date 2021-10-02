package tictactoe.model;

import tictactoe.model.position.Point;

public class Position<T> {

    private final T _position;

    protected Position(T value) {
        _position = value;
    }

    public static <T> Position<T> of(T value) {
        return new Position<>(value);
    }

    public T get() {
        return _position;
    }
}
