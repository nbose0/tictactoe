package ttt.original;

import ttt.models.Pair;
import ttt.models.Player;
import ttt.models.Token;
import ttt.models.board.TicTacToeBoard;
import ttt.models.position.Coordinate1D;

import java.util.Random;

public class RandomPlayerController extends PlayerController {

    private final Random _randomGenerator;

    public RandomPlayerController(Player player) {
        super(player);
        _randomGenerator = new Random();
    }

    public static RandomPlayerController of(Token token) {
        return new RandomPlayerController(Player.of(token, token.name()));
    }

    @Override
    public Pair<Coordinate1D, Token> getMove(Board board) {
        int row = _randomGenerator.nextInt(TicTacToeBoard.BOARD_SIDE_LENGTH);
        int col = _randomGenerator.nextInt(TicTacToeBoard.BOARD_SIDE_LENGTH);
        return Pair.of(Coordinate1D.of(row, col), _player.getToken());
    }

    @Override
    public Player player() {
        return _player;
    }
}
