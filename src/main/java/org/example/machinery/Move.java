package org.example.machinery;

import static org.example.machinery.BinaryLogic.getBitRange;

public record Move(int binaryData) {
    public static Move create(Coordinate start, Coordinate end) {
        if (start.isOutOfBounds()) {
            throw new IllegalArgumentException(String.format("%s IS NOT IN BOUNDS", start));
        }
        if (end.isOutOfBounds()) {
            throw new IllegalArgumentException(String.format("%s IS NOT IN BOUNDS", end));
        }
        return new Move(start.toBinary() | (end.toBinary()) << 6);
    }
    
    public static Move create(String encoding) {
        return Move.create(new Coordinate(encoding.toLowerCase().charAt(0) - 'a', encoding.charAt(1) - '1'),
                new Coordinate(encoding.toLowerCase().charAt(2) - 'a', encoding.charAt(3) - '1'));
    }
    
    public Coordinate getStart() {
        return Coordinate.parseBinary(getBitRange(binaryData, 0, 5));
    }
    
    public int getStartIndex() {
        return getBitRange(binaryData, 0, 6);
    }
    
    public Coordinate getEnd() {
        return Coordinate.parseBinary(getBitRange(binaryData, 6, 11));
    }
    
    public int getEndIndex() {
        return getBitRange(binaryData, 6, 12);
    }
}
