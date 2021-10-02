package tictactoe.model.board;

import tictactoe.model.position.Position;
import tictactoe.model.Square;

/**
 * A Board is a data structure of Positions to Squares.
 * B is the type of value the square contains.
 * T is the type of Square.
 * C is the type of value the Position contains.
 */
public interface Board <C, B, T extends Square<B>>{

    boolean isFull();

    void setSquare(T square, Position<C> p);

    void print();
}
