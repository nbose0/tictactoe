package ttt.tictactoe.player;

import ttt.models.Pair;
import ttt.models.Player;
import ttt.models.Token;
import ttt.models.position.Coordinate1D;
import ttt.tictactoe.BasicBoard;
import ttt.tictactoe.player.ai.AI;

public class BasicAIPlayerController extends BasicPlayerController {

    private final AI _ai;

    BasicAIPlayerController(Player player) {
        super(player);
        _ai = AI.of();
    }

    public static BasicAIPlayerController of(Player player) {
        return new BasicAIPlayerController(player);
    }

    public static BasicAIPlayerController of(Token token) {
        return new BasicAIPlayerController(Player.of(token));
    }

    @Override
    public Pair<Coordinate1D, Token> getMove(BasicBoard basicBoard) {
        return Pair.of(_ai.makeMove(basicBoard, _player.getToken()), _player.getToken());
    }

    @Override
    public Player player() {
        return _player;
    }
}
