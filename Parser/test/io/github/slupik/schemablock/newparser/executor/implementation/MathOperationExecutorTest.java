package io.github.slupik.schemablock.newparser.executor.implementation;

import io.github.slupik.schemablock.newparser.memory.element.Value;
import io.github.slupik.schemablock.newparser.memory.element.ValueImpl;
import org.junit.jupiter.api.Test;

import static io.github.slupik.schemablock.newparser.memory.element.ValueType.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * All rights reserved & copyright ©
 */
class MathOperationExecutorTest {

    @Test
    void add() {
        Value result = MathOperationExecutor.add(new ValueImpl(SHORT, "2"), new ValueImpl(INTEGER, "5"));
        assertEquals(INTEGER, result.getType());
        assertEquals(7, ((int) result.getValue()));

        result = MathOperationExecutor.add(new ValueImpl(DOUBLE, "2.0"), new ValueImpl(INTEGER, 5));
        assertEquals(DOUBLE, result.getType());
        assertEquals(7, ((double) result.getValue()));

        result = MathOperationExecutor.add(new ValueImpl(BYTE, "2.0"), new ValueImpl(BYTE, 5));
        assertEquals(BYTE, result.getType());
        assertEquals(7, ((byte) result.getValue()));

        result = MathOperationExecutor.add(new ValueImpl(BYTE, "2.0"), new ValueImpl(BYTE, 5));
        assertEquals(BYTE, result.getType());
        byte ans = result.getCastedValue();
        assertEquals(7, ans);
    }
}