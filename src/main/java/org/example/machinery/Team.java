package org.example.machinery;

public enum Team implements BinaryEncodable {
    WHITE, BLACK;
    
    public static int requiredBits() {
        return 1;
    }
    
    public static Team fromBits(int bit) {
        return switch (bit) {
            case 1 -> WHITE;
            case 0 -> BLACK;
            default -> throw new IllegalArgumentException(String.format("%s IS NOT A VALID INPUT", bit));
        };
    }
    
    public int toBits() {
        return switch (this) {
            case WHITE -> 1;
            case BLACK -> 0;
        };
    }
}
