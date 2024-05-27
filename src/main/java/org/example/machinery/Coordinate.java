package org.example.machinery;

import org.jetbrains.annotations.NotNull;

public record Coordinate(int x, int y) implements Comparable<Coordinate> {
    public static Coordinate parseBinary(int binaryData) {
        return new Coordinate(BinaryLogic.getBitRange(binaryData, 0, 2),
                BinaryLogic.getBitRange(binaryData, 3, 5));
    }
    
    public Coordinate add(@NotNull Coordinate other) {
        return new Coordinate(this.x() + other.x, this.y() + other.y);
    }
    
    public Coordinate add(int x, int y) {
        return this.add(new Coordinate(x, y));
    }
    
    public Coordinate subtract(@NotNull Coordinate other) {
        return new Coordinate(this.x() - other.x, this.y() - other.y);
    }
    
    public Coordinate subtract(int x, int y) {
        return this.subtract(new Coordinate(x, y));
    }
    
    public Coordinate difference(Coordinate other) {
        return new Coordinate(Math.abs(other.x - this.x()), Math.abs(other.y - this.y()));
    }
    
    public Coordinate difference(int x, int y) {
        return this.difference(new Coordinate(x, y));
    }
    
    public Coordinate multiply(int value) {
        return new Coordinate(this.x() * value, this.y() * value);
    }
    
    public int toIndex(int width) {
        return this.y() * width + this.x();
    }
    
    public int toIndex() {
        return this.y() * 8 + this.x();
    }
    
    public boolean isInBounds(int xMin, int yMin, int xMax, int yMax) {
        return this.x() >= xMin && this.x() <= xMax && this.y() >= yMin && this.y() <= yMax;
    }
    
    public boolean isInBounds() {
        return this.isInBounds(0, 0, 7, 7);
    }
    
    public boolean isOutOfBounds() {
        return !this.isInBounds();
    }
    
    public int toBinary() {
        if (isOutOfBounds()) {
            throw new IllegalArgumentException(String.format("%s IS OUT OF BOUNDS", this));
        }
        return this.x() | (this.y() << 3);
    }
    
    public String toDisplayString() {
        return String.format("(%s, %s)", x, y);
    }
    
    @Override
    public int compareTo(@NotNull Coordinate other) {
        Coordinate delta = this.subtract(other);
        return delta.y() != 0 ? delta.y() : delta.x();
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Coordinate && this.x() == ((Coordinate) obj).x && this.y() == ((Coordinate) obj).y;
    }
    
    public boolean equals(int x, int y) {
        return this.x() == x && this.y() == y;
    }
}
