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
class BitwiseOperationExecutorTest {

    @Test
    void leftShift() throws Throwable {
        //Numbers >0
        Value result = BitwiseOperationExecutor.leftShift(new ValueImpl(SHORT, "5"), new ValueImpl(INTEGER, "2"));
        assertEquals(INTEGER, result.getType());
        assertEquals(5<<2, result.getValue());

        result = BitwiseOperationExecutor.leftShift(new ValueImpl(BYTE, "5.0"), new ValueImpl(BYTE, 2));
        assertEquals(INTEGER, result.getType());
        assertEquals(5<<2, result.getValue());

        result = BitwiseOperationExecutor.leftShift(new ValueImpl(BYTE, "5.0"), new ValueImpl(BYTE, 2));
        assertEquals(INTEGER, result.getType());
        int ans = result.getCastedValue();
        assertEquals(5<<2, ans);

        //Numbers <0
        result = BitwiseOperationExecutor.leftShift(new ValueImpl(SHORT, "-2"), new ValueImpl(INTEGER, "5"));
        assertEquals(INTEGER, result.getType());
        assertEquals(-2<<5, result.getValue());

        result = BitwiseOperationExecutor.leftShift(new ValueImpl(BYTE, "2.0"), new ValueImpl(BYTE, -5));
        assertEquals(INTEGER, result.getType());
        assertEquals(2<<-5, result.getValue());

        result = BitwiseOperationExecutor.leftShift(new ValueImpl(BYTE, "2.0"), new ValueImpl(BYTE, -5));
        assertEquals(INTEGER, result.getType());
        ans = result.getCastedValue();
        assertEquals(2<<-5, ans);

        //Errors
        assertThrows(IllegalOperation.class,
                () -> BitwiseOperationExecutor.leftShift(new ValueImpl(BOOLEAN, "true"), new ValueImpl(BOOLEAN, false)));


        assertThrows(IllegalOperation.class,
                () -> BitwiseOperationExecutor.leftShift(new ValueImpl(DOUBLE, "-2.0"), new ValueImpl(INTEGER, 5)));

        assertThrows(IllegalOperation.class,
                () -> BitwiseOperationExecutor.leftShift(new ValueImpl(STRING, "foo"), new ValueImpl(STRING, "bar")));
    }

    @Test
    void rightShift() throws Throwable {
        //Numbers >0
        Value result = BitwiseOperationExecutor.rightShift(new ValueImpl(SHORT, "5"), new ValueImpl(INTEGER, "2"));
        assertEquals(INTEGER, result.getType());
        assertEquals(5>>2, result.getValue());

        result = BitwiseOperationExecutor.rightShift(new ValueImpl(BYTE, "5.0"), new ValueImpl(BYTE, 2));
        assertEquals(INTEGER, result.getType());
        assertEquals(5>>2, result.getValue());

        result = BitwiseOperationExecutor.rightShift(new ValueImpl(BYTE, "5.0"), new ValueImpl(BYTE, 2));
        assertEquals(INTEGER, result.getType());
        int ans = result.getCastedValue();
        assertEquals(5>>2, ans);

        //Numbers <0
        result = BitwiseOperationExecutor.rightShift(new ValueImpl(SHORT, "-2"), new ValueImpl(INTEGER, "5"));
        assertEquals(INTEGER, result.getType());
        assertEquals(-2>>5, result.getValue());

        result = BitwiseOperationExecutor.rightShift(new ValueImpl(BYTE, "2.0"), new ValueImpl(BYTE, -5));
        assertEquals(INTEGER, result.getType());
        assertEquals(2>>-5, result.getValue());

        result = BitwiseOperationExecutor.rightShift(new ValueImpl(BYTE, "2.0"), new ValueImpl(BYTE, -5));
        assertEquals(INTEGER, result.getType());
        ans = result.getCastedValue();
        assertEquals(2>>-5, ans);

        //Errors
        assertThrows(IllegalOperation.class,
                () -> BitwiseOperationExecutor.rightShift(new ValueImpl(BOOLEAN, "true"), new ValueImpl(BOOLEAN, false)));


        assertThrows(IllegalOperation.class,
                () -> BitwiseOperationExecutor.rightShift(new ValueImpl(DOUBLE, "-2.0"), new ValueImpl(INTEGER, 5)));

        assertThrows(IllegalOperation.class,
                () -> BitwiseOperationExecutor.rightShift(new ValueImpl(STRING, "foo"), new ValueImpl(STRING, "bar")));
    }
}