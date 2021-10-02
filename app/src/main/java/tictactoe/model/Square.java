package tictactoe.model;

import java.util.Optional;

public abstract class Square<T> {

    protected T _value;

    public boolean isEmpty() {
        return _value == null;
    }

    public Optional<T> getValue() {
        return Optional.ofNullable(_value);
    }

    public Square<T> setValue(T value) {
        _value = value;
        return this;
    }

    public void print() {
        System.out.printf("\t%s\t", this);
    }

    @Override
    public abstract String toString();
}