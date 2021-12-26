package ttt.original.player;

import ttt.models.Pair;
import ttt.models.Player;
import ttt.models.Token;
import ttt.models.position.Coordinate1D;
import ttt.original.Board;
import ttt.original.player.ai.AI;

public class AIPlayerController extends PlayerController {

    private final AI _ai;

    AIPlayerController(Player player) {
        super(player);
        _ai = AI.of();
    }

    public static AIPlayerController of(Player player) {
        return new AIPlayerController(player);
    }

    public static AIPlayerController of(Token token) {
        return new AIPlayerController(Player.of(token));
    }

    @Override
    public Pair<Coordinate1D, Token> getMove(Board board) {
        return Pair.of(_ai.makeMove(board, _player.getToken()), _player.getToken());
    }

    @Override
    public Player player() {
        return _player;
    }
}
