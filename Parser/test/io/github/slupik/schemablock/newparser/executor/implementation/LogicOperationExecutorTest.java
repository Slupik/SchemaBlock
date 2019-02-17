package io.github.slupik.schemablock.newparser.executor.implementation;

import io.github.slupik.schemablock.newparser.memory.element.Value;
import io.github.slupik.schemablock.newparser.memory.element.ValueImpl;
import org.junit.jupiter.api.Test;

import static io.github.slupik.schemablock.newparser.memory.element.ValueType.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * All rights reserved & copyright ©
 */
class LogicOperationExecutorTest {

    @Test
    void and() throws Throwable {
        Value result = LogicOperationExecutor.and(new ValueImpl(BOOLEAN, "true"), new ValueImpl(BOOLEAN, true));
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = LogicOperationExecutor.and(new ValueImpl(BOOLEAN, "false"), new ValueImpl(BOOLEAN, true));
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = LogicOperationExecutor.and(new ValueImpl(BOOLEAN, "true"), new ValueImpl(BOOLEAN, false));
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = LogicOperationExecutor.and(new ValueImpl(BOOLEAN, "false"), new ValueImpl(BOOLEAN, false));
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        //Errors
        assertThrows(IllegalOperation.class,
                () -> LogicOperationExecutor.and(new ValueImpl(STRING, "foo"), new ValueImpl(STRING, "bar")));
        assertThrows(IllegalOperation.class,
                () -> LogicOperationExecutor.and(new ValueImpl(STRING, "2"), new ValueImpl(STRING, 5)));
    }

    @Test
    void or() throws Throwable {
        Value result = LogicOperationExecutor.or(new ValueImpl(BOOLEAN, "true"), new ValueImpl(BOOLEAN, true));
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = LogicOperationExecutor.or(new ValueImpl(BOOLEAN, "false"), new ValueImpl(BOOLEAN, true));
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = LogicOperationExecutor.or(new ValueImpl(BOOLEAN, "true"), new ValueImpl(BOOLEAN, false));
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = LogicOperationExecutor.or(new ValueImpl(BOOLEAN, "false"), new ValueImpl(BOOLEAN, false));
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        //Errors
        assertThrows(IllegalOperation.class,
                () -> LogicOperationExecutor.and(new ValueImpl(STRING, "foo"), new ValueImpl(STRING, "bar")));
        assertThrows(IllegalOperation.class,
                () -> LogicOperationExecutor.and(new ValueImpl(STRING, "2"), new ValueImpl(STRING, 5)));
    }

    @Test
    void not() throws Throwable {
        Value result = LogicOperationExecutor.not(new ValueImpl(BOOLEAN, "false"));
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = LogicOperationExecutor.not(new ValueImpl(BOOLEAN, false));
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = LogicOperationExecutor.not(new ValueImpl(BOOLEAN, "true"));
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = LogicOperationExecutor.not(new ValueImpl(BOOLEAN, true));
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        //Errors
        assertThrows(IllegalOperation.class,
                () -> LogicOperationExecutor.and(new ValueImpl(STRING, "foo"), new ValueImpl(STRING, "bar")));
        assertThrows(IllegalOperation.class,
                () -> LogicOperationExecutor.and(new ValueImpl(STRING, "2"), new ValueImpl(STRING, 5)));
    }
}