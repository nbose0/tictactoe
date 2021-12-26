package ttt.models.position;

public class Coordinate1D extends Coordinate<IntegerCoordinateValue> {

    Coordinate1D(IntegerCoordinateValue x, IntegerCoordinateValue y) {
        super(x, y);
    }

    public static Coordinate1D of(IntegerCoordinateValue x, IntegerCoordinateValue y) {
        return new Coordinate1D(x, y);
    }

    public static Coordinate1D of(int x, int y) {
        return new Coordinate1D(IntegerCoordinateValue.of(x), IntegerCoordinateValue.of(y));
    }
}
