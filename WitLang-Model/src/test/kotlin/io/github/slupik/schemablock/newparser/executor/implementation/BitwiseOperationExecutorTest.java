package io.github.slupik.schemablock.newparser.executor.implementation;

import io.github.slupik.schemablock.newparser.executor.implementation.exception.IllegalOperation;
import io.github.slupik.schemablock.newparser.memory.element.SimpleValue;
import io.github.slupik.schemablock.newparser.memory.element.SimpleValueImpl;
import org.junit.jupiter.api.Test;

import static io.github.slupik.schemablock.newparser.executor.implementation.ByteCommandOperationMocker.mockByteCommandOperation;
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
        SimpleValue result = BitwiseOperationExecutor.leftShift(new SimpleValueImpl(SHORT, "5"), new SimpleValueImpl(INTEGER, "2"), mockByteCommandOperation());
        assertEquals(INTEGER, result.getType());
        assertEquals(5<<2, result.getValue());

        result = BitwiseOperationExecutor.leftShift(new SimpleValueImpl(BYTE, "5.0"), new SimpleValueImpl(BYTE, 2), mockByteCommandOperation());
        assertEquals(INTEGER, result.getType());
        assertEquals(5<<2, result.getValue());

        result = BitwiseOperationExecutor.leftShift(new SimpleValueImpl(BYTE, "5.0"), new SimpleValueImpl(BYTE, 2), mockByteCommandOperation());
        assertEquals(INTEGER, result.getType());
        int ans = result.getCastedValue();
        assertEquals(5<<2, ans);

        //Numbers <0
        result = BitwiseOperationExecutor.leftShift(new SimpleValueImpl(SHORT, "-2"), new SimpleValueImpl(INTEGER, "5"), mockByteCommandOperation());
        assertEquals(INTEGER, result.getType());
        assertEquals(-2<<5, result.getValue());

        result = BitwiseOperationExecutor.leftShift(new SimpleValueImpl(BYTE, "2.0"), new SimpleValueImpl(BYTE, -5), mockByteCommandOperation());
        assertEquals(INTEGER, result.getType());
        assertEquals(2<<-5, result.getValue());

        result = BitwiseOperationExecutor.leftShift(new SimpleValueImpl(BYTE, "2.0"), new SimpleValueImpl(BYTE, -5), mockByteCommandOperation());
        assertEquals(INTEGER, result.getType());
        ans = result.getCastedValue();
        assertEquals(2<<-5, ans);

        //Errors
        assertThrows(IllegalOperation.class,
                () -> BitwiseOperationExecutor.leftShift(new SimpleValueImpl(BOOLEAN, "true"), new SimpleValueImpl(BOOLEAN, false), mockByteCommandOperation()));


        assertThrows(IllegalOperation.class,
                () -> BitwiseOperationExecutor.leftShift(new SimpleValueImpl(DOUBLE, "-2.0"), new SimpleValueImpl(INTEGER, 5), mockByteCommandOperation()));

        assertThrows(IllegalOperation.class,
                () -> BitwiseOperationExecutor.leftShift(new SimpleValueImpl(STRING, "foo"), new SimpleValueImpl(STRING, "bar"), mockByteCommandOperation()));
    }

    @Test
    void rightShift() throws Throwable {
        //Numbers >0
        SimpleValue result = BitwiseOperationExecutor.rightShift(new SimpleValueImpl(SHORT, "5"), new SimpleValueImpl(INTEGER, "2"), mockByteCommandOperation());
        assertEquals(INTEGER, result.getType());
        assertEquals(5>>2, result.getValue());

        result = BitwiseOperationExecutor.rightShift(new SimpleValueImpl(BYTE, "5.0"), new SimpleValueImpl(BYTE, 2), mockByteCommandOperation());
        assertEquals(INTEGER, result.getType());
        assertEquals(5>>2, result.getValue());

        result = BitwiseOperationExecutor.rightShift(new SimpleValueImpl(BYTE, "5.0"), new SimpleValueImpl(BYTE, 2), mockByteCommandOperation());
        assertEquals(INTEGER, result.getType());
        int ans = result.getCastedValue();
        assertEquals(5>>2, ans);

        //Numbers <0
        result = BitwiseOperationExecutor.rightShift(new SimpleValueImpl(SHORT, "-2"), new SimpleValueImpl(INTEGER, "5"), mockByteCommandOperation());
        assertEquals(INTEGER, result.getType());
        assertEquals(-2>>5, result.getValue());

        result = BitwiseOperationExecutor.rightShift(new SimpleValueImpl(BYTE, "2.0"), new SimpleValueImpl(BYTE, -5), mockByteCommandOperation());
        assertEquals(INTEGER, result.getType());
        assertEquals(2>>-5, result.getValue());

        result = BitwiseOperationExecutor.rightShift(new SimpleValueImpl(BYTE, "2.0"), new SimpleValueImpl(BYTE, -5), mockByteCommandOperation());
        assertEquals(INTEGER, result.getType());
        ans = result.getCastedValue();
        assertEquals(2>>-5, ans);

        //Errors
        assertThrows(IllegalOperation.class,
                () -> BitwiseOperationExecutor.rightShift(new SimpleValueImpl(BOOLEAN, "true"), new SimpleValueImpl(BOOLEAN, false), mockByteCommandOperation()));


        assertThrows(IllegalOperation.class,
                () -> BitwiseOperationExecutor.rightShift(new SimpleValueImpl(DOUBLE, "-2.0"), new SimpleValueImpl(INTEGER, 5), mockByteCommandOperation()));

        assertThrows(IllegalOperation.class,
                () -> BitwiseOperationExecutor.rightShift(new SimpleValueImpl(STRING, "foo"), new SimpleValueImpl(STRING, "bar"), mockByteCommandOperation()));
    }

    @Test
    void and() throws Throwable {
        //Numbers >0
        SimpleValue result = BitwiseOperationExecutor.and(new SimpleValueImpl(SHORT, "5"), new SimpleValueImpl(INTEGER, "2"), mockByteCommandOperation());
        assertEquals(INTEGER, result.getType());
        assertEquals(5&2, result.getValue());

        result = BitwiseOperationExecutor.and(new SimpleValueImpl(BYTE, "5.0"), new SimpleValueImpl(BYTE, 2), mockByteCommandOperation());
        assertEquals(BYTE, result.getType());
        assertEquals(((byte) (5 & 2)), result.getValue());

        result = BitwiseOperationExecutor.and(new SimpleValueImpl(BYTE, "5.0"), new SimpleValueImpl(BYTE, 2), mockByteCommandOperation());
        assertEquals(BYTE, result.getType());
        byte ans = result.getCastedValue();
        assertEquals(5&2, ans);

        //Numbers <0
        result = BitwiseOperationExecutor.and(new SimpleValueImpl(SHORT, "-2"), new SimpleValueImpl(INTEGER, "5"), mockByteCommandOperation());
        assertEquals(INTEGER, result.getType());
        assertEquals(-2&5, result.getValue());

        result = BitwiseOperationExecutor.and(new SimpleValueImpl(BYTE, "2.0"), new SimpleValueImpl(BYTE, -5), mockByteCommandOperation());
        assertEquals(BYTE, result.getType());
        assertEquals(((byte) (2 & -5)), result.getValue());

        result = BitwiseOperationExecutor.and(new SimpleValueImpl(BYTE, "2.0"), new SimpleValueImpl(BYTE, -5), mockByteCommandOperation());
        assertEquals(BYTE, result.getType());
        ans = result.getCastedValue();
        assertEquals(2&-5, ans);

        //Errors
        assertThrows(IllegalOperation.class,
                () -> BitwiseOperationExecutor.and(new SimpleValueImpl(BOOLEAN, "true"), new SimpleValueImpl(BOOLEAN, false), mockByteCommandOperation()));


        assertThrows(IllegalOperation.class,
                () -> BitwiseOperationExecutor.and(new SimpleValueImpl(DOUBLE, "-2.0"), new SimpleValueImpl(INTEGER, 5), mockByteCommandOperation()));

        assertThrows(IllegalOperation.class,
                () -> BitwiseOperationExecutor.and(new SimpleValueImpl(STRING, "foo"), new SimpleValueImpl(STRING, "bar"), mockByteCommandOperation()));
    }

    @Test
    void xor() throws Throwable {
        //Numbers >0
        SimpleValue result = BitwiseOperationExecutor.xor(new SimpleValueImpl(SHORT, "5"), new SimpleValueImpl(INTEGER, "2"), mockByteCommandOperation());
        assertEquals(INTEGER, result.getType());
        assertEquals(5^2, result.getValue());

        result = BitwiseOperationExecutor.xor(new SimpleValueImpl(BYTE, "5.0"), new SimpleValueImpl(BYTE, 2), mockByteCommandOperation());
        assertEquals(INTEGER, result.getType());
        assertEquals(5 ^ 2, result.getValue());

        result = BitwiseOperationExecutor.xor(new SimpleValueImpl(BYTE, "5.0"), new SimpleValueImpl(BYTE, 2), mockByteCommandOperation());
        assertEquals(INTEGER, result.getType());
        int ans = result.getCastedValue();
        assertEquals(5^2, ans);

        //Numbers <0
        result = BitwiseOperationExecutor.xor(new SimpleValueImpl(SHORT, "-2"), new SimpleValueImpl(INTEGER, "5"), mockByteCommandOperation());
        assertEquals(INTEGER, result.getType());
        assertEquals(-2^5, result.getValue());

        result = BitwiseOperationExecutor.xor(new SimpleValueImpl(BYTE, "2.0"), new SimpleValueImpl(BYTE, -5), mockByteCommandOperation());
        assertEquals(INTEGER, result.getType());
        assertEquals(2 ^ -5, result.getValue());

        result = BitwiseOperationExecutor.xor(new SimpleValueImpl(BYTE, "2.0"), new SimpleValueImpl(BYTE, -5), mockByteCommandOperation());
        assertEquals(INTEGER, result.getType());
        ans = result.getCastedValue();
        assertEquals(2^-5, ans);

        //Errors
        assertThrows(IllegalOperation.class,
                () -> BitwiseOperationExecutor.xor(new SimpleValueImpl(BOOLEAN, "true"), new SimpleValueImpl(BOOLEAN, false), mockByteCommandOperation()));


        assertThrows(IllegalOperation.class,
                () -> BitwiseOperationExecutor.xor(new SimpleValueImpl(DOUBLE, "-2.0"), new SimpleValueImpl(INTEGER, 5), mockByteCommandOperation()));

        assertThrows(IllegalOperation.class,
                () -> BitwiseOperationExecutor.xor(new SimpleValueImpl(STRING, "foo"), new SimpleValueImpl(STRING, "bar"), mockByteCommandOperation()));
    }

    @Test
    void or() throws Throwable {
        //Numbers >0
        SimpleValue result = BitwiseOperationExecutor.or(new SimpleValueImpl(SHORT, "5"), new SimpleValueImpl(INTEGER, "2"), mockByteCommandOperation());
        assertEquals(INTEGER, result.getType());
        assertEquals(5|2, result.getValue());

        result = BitwiseOperationExecutor.or(new SimpleValueImpl(BYTE, "5.0"), new SimpleValueImpl(BYTE, 2), mockByteCommandOperation());
        assertEquals(BYTE, result.getType());
        assertEquals(((byte) (5 | 2)), result.getValue());

        result = BitwiseOperationExecutor.or(new SimpleValueImpl(BYTE, "5.0"), new SimpleValueImpl(BYTE, 2), mockByteCommandOperation());
        assertEquals(BYTE, result.getType());
        byte ans = result.getCastedValue();
        assertEquals(5|2, ans);

        //Numbers <0
        result = BitwiseOperationExecutor.or(new SimpleValueImpl(SHORT, "-2"), new SimpleValueImpl(INTEGER, "5"), mockByteCommandOperation());
        assertEquals(INTEGER, result.getType());
        assertEquals(-2|5, result.getValue());

        result = BitwiseOperationExecutor.or(new SimpleValueImpl(BYTE, "2.0"), new SimpleValueImpl(BYTE, -5), mockByteCommandOperation());
        assertEquals(BYTE, result.getType());
        assertEquals(((byte) (2 | -5)), result.getValue());

        result = BitwiseOperationExecutor.or(new SimpleValueImpl(BYTE, "2.0"), new SimpleValueImpl(BYTE, -5), mockByteCommandOperation());
        assertEquals(BYTE, result.getType());
        ans = result.getCastedValue();
        assertEquals(2|-5, ans);

        //Errors
        assertThrows(IllegalOperation.class,
                () -> BitwiseOperationExecutor.or(new SimpleValueImpl(BOOLEAN, "true"), new SimpleValueImpl(BOOLEAN, false), mockByteCommandOperation()));


        assertThrows(IllegalOperation.class,
                () -> BitwiseOperationExecutor.or(new SimpleValueImpl(DOUBLE, "-2.0"), new SimpleValueImpl(INTEGER, 5), mockByteCommandOperation()));

        assertThrows(IllegalOperation.class,
                () -> BitwiseOperationExecutor.or(new SimpleValueImpl(STRING, "foo"), new SimpleValueImpl(STRING, "bar"), mockByteCommandOperation()));
    }

    @Test
    void not() throws Throwable {
        //Numbers >0
        SimpleValue result = BitwiseOperationExecutor.not(new SimpleValueImpl(BYTE, "5"), mockByteCommandOperation());
        assertEquals(BYTE, result.getType());
        assertEquals(((byte) ~5), result.getValue());

        result = BitwiseOperationExecutor.not(new SimpleValueImpl(SHORT, "5"), mockByteCommandOperation());
        assertEquals(SHORT, result.getType());
        assertEquals(((short) ~5), result.getValue());

        result = BitwiseOperationExecutor.not(new SimpleValueImpl(INTEGER, "5"), mockByteCommandOperation());
        assertEquals(INTEGER, result.getType());
        assertEquals(~5, result.getValue());

        result = BitwiseOperationExecutor.not(new SimpleValueImpl(LONG, "5"), mockByteCommandOperation());
        assertEquals(LONG, result.getType());
        assertEquals(((long) ~5), result.getValue());


        //Numbers <0
        result = BitwiseOperationExecutor.not(new SimpleValueImpl(BYTE, "-5"), mockByteCommandOperation());
        assertEquals(BYTE, result.getType());
        assertEquals(((byte) ~-5), result.getValue());

        result = BitwiseOperationExecutor.not(new SimpleValueImpl(SHORT, "-5"), mockByteCommandOperation());
        assertEquals(SHORT, result.getType());
        assertEquals(((short) ~-5), result.getValue());

        result = BitwiseOperationExecutor.not(new SimpleValueImpl(INTEGER, "-5"), mockByteCommandOperation());
        assertEquals(INTEGER, result.getType());
        assertEquals(~-5, result.getValue());

        result = BitwiseOperationExecutor.not(new SimpleValueImpl(LONG, "-5"), mockByteCommandOperation());
        assertEquals(LONG, result.getType());
        assertEquals(((long) ~-5), result.getValue());

        //Errors
        assertThrows(IllegalOperation.class,
                () -> BitwiseOperationExecutor.or(new SimpleValueImpl(BOOLEAN, "true"), new SimpleValueImpl(BOOLEAN, false), mockByteCommandOperation()));


        assertThrows(IllegalOperation.class,
                () -> BitwiseOperationExecutor.or(new SimpleValueImpl(DOUBLE, "-2.0"), new SimpleValueImpl(INTEGER, 5), mockByteCommandOperation()));

        assertThrows(IllegalOperation.class,
                () -> BitwiseOperationExecutor.or(new SimpleValueImpl(STRING, "foo"), new SimpleValueImpl(STRING, "bar"), mockByteCommandOperation()));
    }
}