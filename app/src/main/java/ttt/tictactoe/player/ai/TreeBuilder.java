package ttt.original.player.ai;

import ttt.models.Token;
import ttt.models.board.TicTacToeBoard;
import ttt.models.position.Coordinate1D;
import ttt.original.*;

import java.util.*;

public class TreeBuilder {

    private final BasicRuleEngine _Basic_ruleEngine;

    TreeBuilder(BasicRuleEngine basicRuleEngine) {
        _Basic_ruleEngine = basicRuleEngine;
    }

    public static TreeBuilder of() {
        return new TreeBuilder(BasicRuleEngine.of());
    }

    public Node buildTree() {
        Token currentToken = Token.X;
        Node root = Node.of(BasicBoard.of(), 0);

        Queue<Node> nextNodes = new ArrayDeque<>();
        nextNodes.add(root);

        int depth = 0;
        int currentLevelSize = nextNodes.size();
        while (!nextNodes.isEmpty()) {
            Node currentNode = nextNodes.poll();
            if (!_Basic_ruleEngine.isGameOver(currentNode.getBoard())) {
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
                BasicBoard basicBoard = parentNode.getBoard().copy();
                if (basicBoard.getSquare(Coordinate1D.of(i, j)).isEmpty()) {
                    basicBoard.setSquare(Coordinate1D.of(i, j), BasicSquare.of(BasicValue.of(token)));
                    children.add(Node.of(basicBoard, Coordinate1D.of(i, j), depth));
                }
            }
        }
        return children;
    }

    private Token switchCurrentToken(Token currentToken) {
        return currentToken == Token.X ? Token.O : Token.X;
    }
}
