package ttt.original.player.ai;

import ttt.models.Token;
import ttt.models.board.TicTacToeBoard;
import ttt.models.position.Coordinate1D;
import ttt.original.*;

import java.util.*;

public class TreeBuilder {

    private final RuleEngine _ruleEngine;

    TreeBuilder(RuleEngine ruleEngine) {
        _ruleEngine = ruleEngine;
    }

    public static TreeBuilder of() {
        return new TreeBuilder(RuleEngine.of());
    }

    public Node buildTree() {
        Token currentToken = Token.X;
        Node root = Node.of(Board.of(), 0);

        Queue<Node> nextNodes = new ArrayDeque<>();
        nextNodes.add(root);

        int depth = 0;
        int currentLevelSize = nextNodes.size();
        while (!nextNodes.isEmpty()) {
            Node currentNode = nextNodes.poll();
            if (!_ruleEngine.isGameOver(currentNode.getBoard())) {
                List<Node> children = buildChildren(currentNode, currentToken, depth);
                currentNode.setChildren(children);
                nextNodes.addAll(children);
            }
            if (--currentLevelSize == 0) {
                currentToken = switchCurrentToken(currentToken);
                currentLevelSize = nextNodes.size();
                depth++;
            }
        }

        return root;
    }

    private List<Node> buildChildren(Node parentNode, Token token, int depth) {
        List<Node> children = new ArrayList<>();
        for (int i = 0; i < TicTacToeBoard.BOARD_SIDE_LENGTH; i++) {
            for (int j = 0; j < TicTacToeBoard.BOARD_SIDE_LENGTH; j++) {
                Board board = parentNode.getBoard().copy();
                if (board.getSquare(Coordinate1D.of(i, j)).isEmpty()) {
                    board.setSquare(Coordinate1D.of(i, j), BasicTicTacToeSquare.of(BasicTicTacToeValue.of(token)));
                    children.add(Node.of(board, Coordinate1D.of(i, j), depth));
                }
            }
        }
        return children;
    }

    private Token switchCurrentToken(Token currentToken) {
        return currentToken == Token.X ? Token.O : Token.X;
    }
}
