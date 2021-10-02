package tictactoe.model;

public class Pair<T, V> {

    private final T _first;
    private final V _second;

    private Pair(T first, V second) {
        _first = first;
        _second = second;
    }

    public static <T, V> Pair<T,V> of(T first, V second) {
        return new Pair(first, second);
    }

    public T first() {
        return _first;
    }

    public V second() {
        return _second;
    }
}
