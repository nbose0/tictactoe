package ttt.original.player;

import ttt.controllers.PlayerController;
import ttt.models.Player;
import ttt.models.position.Coordinate1D;
import ttt.models.position.IntegerCoordinateValue;
import ttt.original.BasicSquare;
import ttt.original.BasicValue;
import ttt.original.BasicBoard;

public abstract class BasicPlayerController implements PlayerController<IntegerCoordinateValue, Coordinate1D, BasicValue, BasicSquare, BasicBoard> {

    protected Player _player;

    public BasicPlayerController(Player player) {
        this._player = player;
    }
}
