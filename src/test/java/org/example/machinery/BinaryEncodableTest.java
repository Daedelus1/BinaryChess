package org.example.machinery;

import com.google.common.truth.Truth;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class BinaryEncodableTest {
    private static final Class<? extends BinaryEncodable>[] implementors =
            new Class[]{MovingState.class, Shape.class, Team.class};
    
    @Test
    void properUsageTest() {
        assertDoesNotThrow(() -> Arrays.stream(implementors).forEach(implementor -> {
            try {
                implementor.getMethod("requiredBits", null).invoke(null);
                implementor.getMethod("fromBits", int.class).invoke(implementor, 0);
                implementor.getMethod("values", null).invoke(null);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }));
    }
    
    @Test
    void encodingContinuityTest() {
        assertDoesNotThrow(() -> Arrays.stream(implementors).forEach(implementor -> {
            try {
                Arrays.stream((BinaryEncodable[]) implementor.getMethod("values", null).invoke(null))
                        .forEach(binaryEncodable ->
                        {
                            try {
                                Truth.assertThat(binaryEncodable).isEqualTo(implementor.getMethod("fromBits", int.class)
                                        .invoke(null, binaryEncodable.toBits()));
                            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                                throw new RuntimeException(e);
                            }
                        });
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }));
    }
    
    
}
