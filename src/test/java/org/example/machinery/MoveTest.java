package org.example.machinery;

import com.google.common.flogger.FluentLogger;
import com.google.common.truth.Truth;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.util.logging.Level;

class MoveTest {
    FluentLogger logger = FluentLogger.forEnclosingClass();
    
    @Test
    void CoordinateToMoveEncodingContinuityTest() {
        Level reportingLevel = Level.FINE;
        for (int x1 = 0; x1 < 8; x1++) {
            for (int y1 = 0; y1 < 8; y1++) {
                for (int x2 = 0; x2 < 8; x2++) {
                    for (int y2 = 0; y2 < 8; y2++) {
                        Coordinate start = new Coordinate(x1, y1);
                        Coordinate end = new Coordinate(x2, y2);
                        Move move = Move.create(start, end);
                        logger.at(reportingLevel).log(String.format("Move[0b%s] (Start: %s | End: %s -> Start: %s | End: %s]",
                                Integer.toBinaryString(move.binaryData()), start.toIndex(), end.toIndex(), move.getStartIndex(), move.getEndIndex()));
                        Truth.assertThat(move.getStartIndex()).isEqualTo(start.toIndex());
                        Truth.assertThat(move.getEndIndex()).isEqualTo(end.toIndex());
                    }
                }
            }
        }
    }
}
