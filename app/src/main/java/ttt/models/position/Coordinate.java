package ttt.models;

public class Coordinate implements Position, CoordinateValue {

    CoordinateValue _x;
    CoordinateValue _y;

    CoordinateValue x(){
        return _x;
    }

    CoordinateValue y() {
        return _y;
    }
}
