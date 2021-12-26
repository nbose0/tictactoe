package ttt.original.player;

import ttt.models.Pair;
import ttt.models.Player;
import ttt.models.Token;
import ttt.models.board.TicTacToeBoard;
import ttt.models.position.Coordinate1D;
import ttt.original.BasicBoard;

import java.util.Random;

public class BasicRandomPlayerController extends BasicPlayerController {

    private final Random _randomGenerator;

    public BasicRandomPlayerController(Player player) {
        super(player);
        _randomGenerator = new Random();
    }

    public static BasicRandomPlayerController of(Token token) {
        return new BasicRandomPlayerController(Player.of(token, token.name()));
    }

    @Override
    public Pair<Coordinate1D, Token> getMove(BasicBoard basicBoard) {
        int row = _randomGenerator.nextInt(TicTacToeBoard.BOARD_SIDE_LENGTH);
        int col = _randomGenerator.nextInt(TicTacToeBoard.BOARD_SIDE_LENGTH);
        return Pair.of(Coordinate1D.of(row, col), _player.getToken());
    }

    @Override
    public Player player() {
        return _player;
    }
}
