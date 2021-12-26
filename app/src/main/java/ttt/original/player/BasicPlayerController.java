package ttt.original.player;

import ttt.controllers.TicTacToePlayerController;
import ttt.models.Player;
import ttt.models.position.Coordinate1D;
import ttt.models.position.IntegerCoordinateValue;
import ttt.original.BasicTicTacToeSquare;
import ttt.original.BasicTicTacToeValue;
import ttt.original.Board;

public abstract class PlayerController implements TicTacToePlayerController<IntegerCoordinateValue, Coordinate1D, BasicTicTacToeValue, BasicTicTacToeSquare, Board> {

    protected Player _player;

    public PlayerController(Player player) {
        this._player = player;
    }
}
