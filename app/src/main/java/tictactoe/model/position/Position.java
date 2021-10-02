package tictactoe.model.position;

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

    @Override
    public String toString() {
        return "Position " + _position;
    }
}
