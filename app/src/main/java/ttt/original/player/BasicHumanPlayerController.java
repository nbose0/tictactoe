package ttt.original.player;

import ttt.models.Pair;
import ttt.models.Player;
import ttt.models.Token;
import ttt.models.board.TicTacToeBoard;
import ttt.models.position.Coordinate1D;
import ttt.original.Board;

import java.util.Random;
import java.util.Scanner;

public class HumanPlayerController extends PlayerController {

    private final Random _randomGenerator;
    private final Scanner _scanner;

    public HumanPlayerController(Player player, Scanner scanner) {
        super(player);
        _randomGenerator = new Random();
        _scanner = scanner;
    }

    public static HumanPlayerController of(Player player, Scanner scanner) {
        return new HumanPlayerController(player, scanner);
    }

    @Override
    public Pair<Coordinate1D, Token> getMove(Board board) {
        System.out.println("Please enter next move:\nRow: ");
        int row = _scanner.nextInt();
        System.out.println("Col: ");
        int col = _scanner.nextInt();
        return Pair.of(Coordinate1D.of(row, col), _player.getToken());
    }

    @Override
    public Player player() {
        return _player;
    }
}
