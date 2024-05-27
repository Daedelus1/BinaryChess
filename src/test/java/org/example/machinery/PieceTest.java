package org.example.machinery;

import com.google.common.truth.Truth;
import org.junit.jupiter.api.Test;

class PieceTest {
    
    @Test
    void binaryEncodingsTest() {
        for (Team team : Team.values()) {
            for (MovingState movingState : MovingState.values()) {
                for (Shape shape : Shape.values()) {
                    Piece piece = Piece.create(team, shape, movingState);
                    Truth.assertThat(piece.getTeam()).isEqualTo(team);
                    Truth.assertThat(piece.getShape()).isEqualTo(shape);
                }
            }
        }
    }
    
}
