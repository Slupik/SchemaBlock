package io.github.slupik.schemablock.newparser.executor.implementation;

import io.github.slupik.schemablock.newparser.memory.element.SimpleValue;
import io.github.slupik.schemablock.newparser.memory.element.SimpleValueImpl;
import org.junit.jupiter.api.Test;

import static io.github.slupik.schemablock.newparser.executor.implementation.ByteCommandOperationMocker.mockByteCommandOperation;
import static io.github.slupik.schemablock.newparser.memory.element.ValueType.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * All rights reserved & copyright ©
 */
class LogicOperationExecutorTest {

    @Test
    void and() throws Throwable {
        SimpleValue result = LogicOperationExecutor.and(new SimpleValueImpl(BOOLEAN, "true"), new SimpleValueImpl(BOOLEAN, true), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = LogicOperationExecutor.and(new SimpleValueImpl(BOOLEAN, "false"), new SimpleValueImpl(BOOLEAN, true), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = LogicOperationExecutor.and(new SimpleValueImpl(BOOLEAN, "true"), new SimpleValueImpl(BOOLEAN, false), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = LogicOperationExecutor.and(new SimpleValueImpl(BOOLEAN, "false"), new SimpleValueImpl(BOOLEAN, false), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        //Errors
        assertThrows(IllegalOperation.class,
                () -> LogicOperationExecutor.and(new SimpleValueImpl(STRING, "foo"), new SimpleValueImpl(STRING, "bar"), mockByteCommandOperation()));
        assertThrows(IllegalOperation.class,
                () -> LogicOperationExecutor.and(new SimpleValueImpl(STRING, "2"), new SimpleValueImpl(STRING, 5), mockByteCommandOperation()));
    }

    @Test
    void or() throws Throwable {
        SimpleValue result = LogicOperationExecutor.or(new SimpleValueImpl(BOOLEAN, "true"), new SimpleValueImpl(BOOLEAN, true), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = LogicOperationExecutor.or(new SimpleValueImpl(BOOLEAN, "false"), new SimpleValueImpl(BOOLEAN, true), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = LogicOperationExecutor.or(new SimpleValueImpl(BOOLEAN, "true"), new SimpleValueImpl(BOOLEAN, false), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = LogicOperationExecutor.or(new SimpleValueImpl(BOOLEAN, "false"), new SimpleValueImpl(BOOLEAN, false), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        //Errors
        assertThrows(IllegalOperation.class,
                () -> LogicOperationExecutor.and(new SimpleValueImpl(STRING, "foo"), new SimpleValueImpl(STRING, "bar"), mockByteCommandOperation()));
        assertThrows(IllegalOperation.class,
                () -> LogicOperationExecutor.and(new SimpleValueImpl(STRING, "2"), new SimpleValueImpl(STRING, 5), mockByteCommandOperation()));
    }

    @Test
    void not() throws Throwable {
        SimpleValue result = LogicOperationExecutor.not(new SimpleValueImpl(BOOLEAN, "false"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = LogicOperationExecutor.not(new SimpleValueImpl(BOOLEAN, false), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = LogicOperationExecutor.not(new SimpleValueImpl(BOOLEAN, "true"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = LogicOperationExecutor.not(new SimpleValueImpl(BOOLEAN, true), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        //Errors
        assertThrows(IllegalOperation.class,
                () -> LogicOperationExecutor.and(new SimpleValueImpl(STRING, "foo"), new SimpleValueImpl(STRING, "bar"), mockByteCommandOperation()));
        assertThrows(IllegalOperation.class,
                () -> LogicOperationExecutor.and(new SimpleValueImpl(STRING, "2"), new SimpleValueImpl(STRING, 5), mockByteCommandOperation()));
    }
}