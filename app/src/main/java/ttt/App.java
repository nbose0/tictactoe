/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package tictactoe;

import tictactoe.model.Player;
import tictactoe.model.Symbol;
import tictactoe.qttt.Game;
import tictactoe.ttt.controller.RandomMovePlayerController;
import tictactoe.ttt.model.TicTacToeGame;

public class App {

    public static void main(String[] args) {
        Player player1 = Player.create(Symbol.X, new RandomMovePlayerController());
        Player player2 = Player.create(Symbol.O, new RandomMovePlayerController());

//        TicTacToeGame game = new TicTacToeGame(player1, player2);
//        game.play(true);

        Game qGame = new Game(player1, player2);
        qGame.play();
    }
}
