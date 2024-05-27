package org.example.machinery;

import org.example.ConsoleColors;
import org.example.ConsoleColors.ColorType;
import org.example.ConsoleColors.ColorValue;

import static org.example.machinery.Shape.EMPTY;
import static org.example.machinery.Shape.EN_PASSANT_GHOST;

/**
 * M -> hasMoved: 1 == yes : 0 == no
 * T -> team: 1 == white : 0 == black
 * S -> shapeID: see Shape.java
 *
 * @param binaryData 0b000M_TSSS
 */
public record Piece(byte binaryData) implements BinaryEncodable {
    
    public static final Piece EMPTY_TILE = new Piece((byte) 0);
    
    public static Piece create(Team team, Shape shape, MovingState hasMoved) {
//        System.out.println(hasMoved + " | " + team + " | " + shape.toDisplayString() + " | " + hasMoved.toBits() + " | " + team.toBits() + " | " +
//                           Integer.toString(shape.toBits(), 2) + " | " + Shape.fromBits(shape.toBits()).toDisplayString() + " | " +
//                           (byte) (hasMoved.toBits() << 4 | team.toBits() << 3 | shape.toBits()));
        return new Piece((byte) (hasMoved.toBits() << (Shape.requiredBits() + Team.requiredBits())
                                 | team.toBits() << (Shape.requiredBits()) | shape.toBits()));
    }
    
    public MovingState hasMoved() {
        return MovingState.fromBits(BinaryLogic.getBit(binaryData, Shape.requiredBits() + Team.requiredBits()));
    }
    
    public Team getTeam() {
        return Team.fromBits(BinaryLogic.getBit(binaryData, Shape.requiredBits()));
    }
    
    public Shape getShape() {
        return Shape.fromBits(BinaryLogic.getBitRange(binaryData, 0, Shape.requiredBits()));
    }
    
    public boolean isEmpty() {
        return this.getShape() == EMPTY || this.getShape() == EN_PASSANT_GHOST;
    }
    
    public boolean isPresent() {
        return !this.isEmpty();
    }
    
    public String toDisplayString() {
        if (this.isEmpty()) {
            return " * ";
        }
        return ConsoleColors.colorize(" " + switch (this.getShape()) {
            case PAWN -> "P";
            case KNIGHT -> "N";
            case BISHOP -> "B";
            case ROOK -> "R";
            case QUEEN -> "Q";
            case KING -> "K";
            case EMPTY, EN_PASSANT_GHOST -> throw new IllegalStateException("TRIED TO COLORIZE EMPTY TILE");
        } + " ", switch (this.getTeam()) {
            case WHITE -> ColorValue.WHITE;
            case BLACK -> ColorValue.BLACK;
        }, ColorType.BRIGHT);
    }
    
    @Override
    public int toBits() {
        return binaryData;
    }
}
