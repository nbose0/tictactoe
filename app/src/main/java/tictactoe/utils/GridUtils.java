package tictactoe.utils;

public class GridUtils {

    private GridUtils() {}

    public static boolean isWithinBoundsInclusive(int beginning, int end, int value) {
        return value >= beginning && value <= end;
    }
}
