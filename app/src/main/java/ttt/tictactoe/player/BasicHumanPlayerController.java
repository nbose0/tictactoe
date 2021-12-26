package ttt.tictactoe.player;

import ttt.models.Pair;
import ttt.models.Player;
import ttt.models.Token;
import ttt.models.position.Coordinate1D;
import ttt.tictactoe.BasicBoard;

import java.util.Random;
import java.util.Scanner;

public class BasicHumanPlayerController extends BasicPlayerController {

    private final Random _randomGenerator;
    private final Scanner _scanner;

    public BasicHumanPlayerController(Player player, Scanner scanner) {
        super(player);
        _randomGenerator = new Random();
        _scanner = scanner;
    }

    public static BasicHumanPlayerController of(Player player, Scanner scanner) {
        return new BasicHumanPlayerController(player, scanner);
    }

    @Override
    public Pair<Coordinate1D, Token> getMove(BasicBoard basicBoard) {
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
