package ttt.models.position;

public class IntegerCoordinateValue implements CoordinateValue {
    Integer _value;

    protected IntegerCoordinateValue(int value) {
        _value = value;
    }

    public int getInt() {
        return _value;
    }

    public static IntegerCoordinateValue of(int value) {
        return new IntegerCoordinateValue(value);
    }
}
