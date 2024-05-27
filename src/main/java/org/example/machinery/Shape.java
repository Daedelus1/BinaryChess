package org.example.machinery;

/**
 * EMPTY - 0
 * EN_PASSANT - 1
 * PAWN - 0b10
 * KNIGHT - 0b11
 * BISHOP -
 */
public enum Shape implements BinaryEncodable {
    EMPTY, EN_PASSANT_GHOST, PAWN, KNIGHT, BISHOP, ROOK, QUEEN, KING;
    
    public static int requiredBits() {
        return 3;
    }
    
    public static Shape fromBits(int id) {
        return Shape.values()[id];
    }
    
    static Shape fromBits(long id) {
        return Shape.values()[(int) id];
    }
    
    public int toBits() {
        return this.ordinal();
    }
    
    public String toDisplayString() {
        return this.toString().substring(0, 4);
    }
}
