package org.example.machinery;

public final class BinaryLogic {
    /**
     * @param seed      the number to read
     * @param startIncl the starting bit index H <- L
     * @param endExcl   the ending bit index H <- L
     * @return the bit shifted bit-range
     */
    static int getBitRange(int seed, int startIncl, int endExcl) {
        return (seed & ((1 << (endExcl - startIncl)) - 1) << startIncl) >> startIncl;
    }
    
    /**
     * @param seed        the number to alter
     * @param replacement the field that replaces the range
     * @param startIncl   the starting bit index of the range H <- L
     * @param endIncl     the starting bit index of the range H <- L
     * @return the altered number
     */
    static int setBitRange(int seed, int replacement, int startIncl, int endIncl) {
        return ((seed & ~(((1 << (endIncl - startIncl)) - 1) << startIncl)) | ((replacement & ((1 << (endIncl - startIncl)) - 1)) << startIncl));
    }
    
    /**
     * @param seed  the seed number
     * @param index the index of the bit to set
     * @return the seed index with the specified bit set
     */
    static int setBit(int seed, int index) {
        return seed | (1 << index);
    }
    
    /**
     * @param seed  the seed number
     * @param index the index of the bit to reset
     * @return the seed index with the specified bit reset
     */
    static int resetBit(int seed, int index) {
        return seed & ~(1 << index);
    }
    
    /**
     * @param seed  the seed number
     * @param index the index of the bit to set
     * @return the seed index with the specified bit set
     */
    static long setBit(long seed, int index) {
        return seed | (1L << index);
    }
    
    /**
     * @param seed  the seed number
     * @param index the index of the bit to reset
     * @return the seed index with the specified bit reset
     */
    static long resetBit(long seed, int index) {
        return seed & ~(1L << index);
    }
    
    static int getBit(long seed, int index) {
        return ((seed & (1L << index)) == 0 ? 0 : 1);
    }
    
    static int getBit(int seed, int index) {
        return ((seed & (1 << index)) == 0 ? 0 : 1);
    }
    
    
    /**
     * @param seed        the number to alter
     * @param replacement the field that replaces the range
     * @param start       the starting bit index of the range H <- L
     * @param end         the starting bit index of the range H <- L
     * @return the altered number
     */
    static long setBitRange(long seed, int replacement, int start, int end) {
        return ((seed & ~(((1L << (end - start)) - 1) << start)) | ((replacement & ((1L << (end - start)) - 1)) << start));
    }
    
    
}
