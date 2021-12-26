package ttt.models;

public interface Square <V extends Value> {

    V getValue();

    void setValue(V value);
}
