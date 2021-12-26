package ttt.original.player.ai;

import ttt.models.position.Coordinate1D;
import ttt.original.Board;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Node {

    Board _board;
    List<Node> _children;
    Coordinate1D _move;
    int _value;
    int _depth;

    Node(Board board, List<Node> children, int depth) {
        _board = board;
        _children = children;
        _value = Integer.MIN_VALUE;
        _depth = depth;
    }

    public Board getBoard() {
        return _board;
    }

    public List<Node> getChildren() {
        return _children;
    }

    public void setBoard(Board board) {
        _board = board;
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

    public static Node of(Board board, int depth) {
        return new Node(board, Collections.emptyList(), depth);
    }

    public static Node of(Board board, Coordinate1D move, int depth) {
        Node node = new Node(board, Collections.emptyList(), depth);
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
