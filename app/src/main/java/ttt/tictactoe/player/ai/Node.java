package ttt.tictactoe.player.ai;

import ttt.models.position.Coordinate1D;
import ttt.tictactoe.BasicBoard;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Node {

    BasicBoard _Basic_board;
    List<Node> _children;
    Coordinate1D _move;
    int _value;
    int _depth;

    Node(BasicBoard basicBoard, List<Node> children, int depth) {
        _Basic_board = basicBoard;
        _children = children;
        _value = Integer.MIN_VALUE;
        _depth = depth;
    }

    public BasicBoard getBoard() {
        return _Basic_board;
    }

    public List<Node> getChildren() {
        return _children;
    }

    public void setBoard(BasicBoard basicBoard) {
        _Basic_board = basicBoard;
    }

    public int getValue() {
        return _value;
    }

    public void setValue(int value) {
        _value = value;
    }

    public boolean hasValue() {
        return _value != Integer.MIN_VALUE;
    }

    public void setChildren(List<Node> children) {
        _children = children;
    }

    public void setMove(Coordinate1D lastMove) {
        _move = lastMove;
    }

    public Coordinate1D getMove() {
        return _move;
    }

    public boolean isTerminal() {
        return _children.isEmpty();
    }

    public int getDepth() {
        return _depth;
    }

    public void setDepth(int depth) {
        _depth = depth;
    }

    public static Node of(BasicBoard basicBoard, int depth) {
        return new Node(basicBoard, Collections.emptyList(), depth);
    }

    public static Node of(BasicBoard basicBoard, Coordinate1D move, int depth) {
        Node node = new Node(basicBoard, Collections.emptyList(), depth);
        node.setMove(move);
        return node;
    }

    class NodeComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            if (o1.getValue() > o2.getValue()) {
                return 1;
            } else if (o1.getValue() < o2.getValue()) {
                return -1;
            } else if (o1.getDepth() < o2.getDepth()) {
                return 1;
            } else if (o1.getDepth() > o2.getDepth()) {
                return -1;
            }
            return 0;
        }
    }
}
