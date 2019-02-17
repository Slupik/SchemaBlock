package io.github.slupik.schemablock.newparser.executor.implementation;

import io.github.slupik.schemablock.newparser.memory.element.Value;
import io.github.slupik.schemablock.newparser.memory.element.ValueImpl;
import org.junit.jupiter.api.Test;

import static io.github.slupik.schemablock.newparser.memory.element.ValueType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * All rights reserved & copyright ©
 */
class MathOperationExecutorTest {

    @Test
    void add() throws IllegalOperation {
        //Numbers
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

        //String
        result = MathOperationExecutor.add(new ValueImpl(STRING, "foo"), new ValueImpl(STRING, "bar"));
        assertEquals(STRING, result.getType());
        assertEquals("foobar", result.getValue());

        result = MathOperationExecutor.add(new ValueImpl(STRING, "2"), new ValueImpl(STRING, "5"));
        assertEquals(STRING, result.getType());
        assertEquals("25", result.getValue());

        result = MathOperationExecutor.add(new ValueImpl(STRING, 2), new ValueImpl(STRING, "5"));
        assertEquals(STRING, result.getType());
        assertEquals("25", result.getValue());

        result = MathOperationExecutor.add(new ValueImpl(STRING, "2"), new ValueImpl(STRING, 5));
        assertEquals(STRING, result.getType());
        assertEquals("25", result.getValue());

        //Errors
        assertThrows(IllegalOperation.class,
                () -> MathOperationExecutor.add(new ValueImpl(BOOLEAN, "true"), new ValueImpl(BOOLEAN, false)));
    }

    @Test
    void subtract() throws IllegalOperation {
        //Numbers >0
        Value result = MathOperationExecutor.subtract(new ValueImpl(SHORT, "5"), new ValueImpl(INTEGER, "2"));
        assertEquals(INTEGER, result.getType());
        assertEquals(3, ((int) result.getValue()));

        result = MathOperationExecutor.subtract(new ValueImpl(DOUBLE, "5.0"), new ValueImpl(INTEGER, 2));
        assertEquals(DOUBLE, result.getType());
        assertEquals(3, ((double) result.getValue()));

        result = MathOperationExecutor.subtract(new ValueImpl(BYTE, "5.0"), new ValueImpl(BYTE, 2));
        assertEquals(BYTE, result.getType());
        assertEquals(3, ((byte) result.getValue()));

        result = MathOperationExecutor.subtract(new ValueImpl(BYTE, "5.0"), new ValueImpl(BYTE, 2));
        assertEquals(BYTE, result.getType());
        byte ans = result.getCastedValue();
        assertEquals(3, ans);

        //Numbers <0
        result = MathOperationExecutor.subtract(new ValueImpl(SHORT, "2"), new ValueImpl(INTEGER, "5"));
        assertEquals(INTEGER, result.getType());
        assertEquals(-3, ((int) result.getValue()));

        result = MathOperationExecutor.subtract(new ValueImpl(DOUBLE, "2.0"), new ValueImpl(INTEGER, 5));
        assertEquals(DOUBLE, result.getType());
        assertEquals(-3, ((double) result.getValue()));

        result = MathOperationExecutor.subtract(new ValueImpl(BYTE, "2.0"), new ValueImpl(BYTE, 5));
        assertEquals(BYTE, result.getType());
        assertEquals(-3, ((byte) result.getValue()));

        result = MathOperationExecutor.subtract(new ValueImpl(BYTE, "2.0"), new ValueImpl(BYTE, 5));
        assertEquals(BYTE, result.getType());
        ans = result.getCastedValue();
        assertEquals(-3, ans);

        //Errors
        assertThrows(IllegalOperation.class,
                () -> MathOperationExecutor.subtract(new ValueImpl(BOOLEAN, "true"), new ValueImpl(BOOLEAN, false)));

        //Errors
        assertThrows(IllegalOperation.class,
                () -> MathOperationExecutor.subtract(new ValueImpl(STRING, "foo"), new ValueImpl(STRING, "bar")));
    }
}