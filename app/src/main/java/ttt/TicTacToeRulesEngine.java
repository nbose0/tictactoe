package ttt;

import ttt.models.Token;
import ttt.models.board.TicTacToeBoard;
import ttt.models.position.Coordinate;
import ttt.models.position.CoordinateValue;
import ttt.models.square.Square;
import ttt.models.value.Value;

public abstract class TicTacToeRulesEngine<C extends CoordinateValue, P extends Coordinate<C>, V extends Value, S extends Square<V>, B extends TicTacToeBoard<C, P, V, S>>  {


    public abstract boolean isValidMove(B board, P position, Token token);

    public abstract boolean hasValidMove(B board);

    public boolean isWinningMove(B board, P position, Token token) {
        return isRowWinner(board, position, token) || isColWinner(board, position, token) || isDiagonalDownWinner(board, position, token) || isDiagonalUpWinner(board, position, token);
    }

    public abstract boolean isRowWinner(B board, P position, Token token);

    public abstract boolean isColWinner(B board, P position, Token token);

    public abstract boolean isDiagonalDownWinner(B board, P position, Token token);

    public abstract boolean isDiagonalUpWinner(B board, P position, Token token);

}
