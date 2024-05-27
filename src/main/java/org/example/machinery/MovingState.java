package org.example.machinery;

public enum MovingState implements BinaryEncodable {
    HAS_MOVED, HAS_NOT_MOVED;
    
    public static int requiredBits() {
        return 1;
    }
    
    public static MovingState fromBits(int bit) {
        return switch (bit) {
            case 0 -> HAS_NOT_MOVED;
            case 1 -> HAS_MOVED;
            default -> throw new IllegalStateException("Unexpected value: " + bit);
        };
    }
    
    @Override
    public int toBits() {
        return switch (this) {
            case HAS_MOVED -> 1;
            case HAS_NOT_MOVED -> 0;
        };
    }
}
