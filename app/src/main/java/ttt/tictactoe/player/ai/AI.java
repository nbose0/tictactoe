package ttt.tictactoe.player.ai;

import ttt.models.Token;
import ttt.models.position.Coordinate1D;
import ttt.tictactoe.BasicBoard;
import ttt.tictactoe.BasicBoardController;
import ttt.tictactoe.BasicRuleEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static ttt.models.TerminalState.*;

public class AI {

    private final BasicRuleEngine _Basic_ruleEngine;
    private final TreeBuilder _treeBuilder;
    private final Random _random;

    private Node _root;
    private Node _currentNode;

    AI(BasicRuleEngine basicRuleEngine, TreeBuilder treeBuilder, Random random) {
        _Basic_ruleEngine = basicRuleEngine;
        _treeBuilder = treeBuilder;
        _random = random;
    }

    public static AI of() {
        return new AI(BasicRuleEngine.of(), TreeBuilder.of(), new Random());
    }

    public Node buildTreeWithValues(Token playerToken) {
        Node root = _treeBuilder.buildTree();
        buildValues(root, playerToken, Token.X, 0);
        return root;
    }

    public Coordinate1D makeMove(BasicBoard currentBasicBoard, Token playerToken) {
        if (_root == null) {
            _root = buildTreeWithValues(playerToken);
            _currentNode = _root;
        }

        for (Node node : _currentNode.getChildren()) {
            if (node.getBoard().areBoardsSame(currentBasicBoard)) {
                _currentNode = node;
            }
        }

        _currentNode = moveWithMax(_currentNode.getChildren());
        return _currentNode._move;
    }


    private Node buildValues(Node currentNode, Token playerToken, Token playerToPlay, int depth) {
        if (currentNode.hasValue()) {
            return currentNode;
        }
        if (currentNode.isTerminal()) {
            if (_Basic_ruleEngine.getWinner(currentNode.getBoard()).map(winner -> winner == playerToken).orElseGet(() -> false)) {
                currentNode.setValue(WIN.val());
            } else if (_Basic_ruleEngine.getWinner(currentNode.getBoard()).map(winner -> winner != playerToken).orElseGet(() -> false)) {
                currentNode.setValue(LOSS.val());
            } else {
                currentNode.setValue(STALEMATE.val());
            }
            currentNode.setDepth(depth);
        } else {
            for (Node child : currentNode.getChildren()) {
                buildValues(child, playerToken, playerToPlay.next(), depth + 1);
            }
            if (playerToPlay == playerToken) {
                Node maxNode = moveWithMax(currentNode.getChildren());
                currentNode.setValue(maxNode.getValue());
                currentNode.setDepth(maxNode.getDepth());
            } else {
                Node minNode = moveWithMin(currentNode.getChildren());
                currentNode.setValue(minNode.getValue());
                currentNode.setDepth(minNode.getDepth());
            }
        }
        return currentNode;
    }

    private int max(List<Node> nodes) {
        int max = Integer.MIN_VALUE;
        for (Node node : nodes) {
            max = Math.max(node._value, max);
        }
        return max;
    }

    private Node moveWithMax(List<Node> nodes) {
        int max = Integer.MIN_VALUE;
        List<Node> nodesWithMaxVal = new ArrayList<>();
        for (Node node : nodes) {
            if (max < node.getValue() || max == node.getValue() && node.getDepth() < nodesWithMaxVal.get(0).getDepth()) {
                max = node.getValue();
                nodesWithMaxVal = new ArrayList<>();
                nodesWithMaxVal.add(node);
            } else if (max == node.getValue() && node.getDepth() == nodesWithMaxVal.get(0).getDepth()) {
                nodesWithMaxVal.add(node);
            }
        }

        return nodesWithMaxVal.get(_random.nextInt(nodesWithMaxVal.size()));
    }

    private Node moveWithMin(List<Node> nodes) {
        int min = Integer.MAX_VALUE;
        List<Node> nodesWithMinVal = new ArrayList<>();
        for (Node node : nodes) {
            if (min > node.getValue()) {
                min = node.getValue();
                nodesWithMinVal = new ArrayList<>();
                nodesWithMinVal.add(node);
            } else if (min == node.getValue()) {
                nodesWithMinVal.add(node);
            }
        }

        return nodesWithMinVal.get(_random.nextInt(nodesWithMinVal.size()));
    }
    private int min(List<Node> nodes) {
        int min = Integer.MAX_VALUE;
        for (Node node : nodes) {
            min = Math.min(node._value, min);
        }
        return min;
    }
}
