package org.example.machinery;

public interface BinaryEncodable {
    static int requiredBits() {
        throw new Error("METHOD NOT OVERRIDDEN");
    }
    
    static BinaryEncodable fromBits(int bits) {
        throw new Error("METHOD NOT OVERRIDDEN");
    }
    
    int toBits();
    
}
