package org.example;

import org.example.machinery.Coordinate;
import org.example.machinery.GameBoard;
import org.example.machinery.Move;

public class Main {
    public static void main(String[] args) {
        System.out.println(GameBoard.STARTING_BOARD
                .blindMove(Move.create(new Coordinate(0, 0), new Coordinate(5, 5))).toDisplayString());
    }
}
