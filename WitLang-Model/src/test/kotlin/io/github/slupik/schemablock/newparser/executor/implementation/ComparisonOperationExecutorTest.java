package io.github.slupik.schemablock.newparser.executor.implementation;

import io.github.slupik.schemablock.newparser.executor.implementation.exception.IllegalOperation;
import io.github.slupik.schemablock.newparser.memory.element.SimpleValue;
import io.github.slupik.schemablock.newparser.memory.element.SimpleValueImpl;
import org.junit.jupiter.api.Test;

import static io.github.slupik.schemablock.newparser.executor.implementation.ByteCommandOperationMocker.mockByteCommandOperation;
import static io.github.slupik.schemablock.newparser.memory.element.ValueType.*;
import static io.github.slupik.schemablock.newparser.memory.element.ValueType.BYTE;
import static org.junit.jupiter.api.Assertions.*;

/**
 * All rights reserved & copyright ©
 */
class ComparisonOperationExecutorTest {

    @Test
    void smallerOrEqual() throws Throwable {
        //equal
        SimpleValue result = ComparisonOperationExecutor.smallerOrEqual(new SimpleValueImpl(INTEGER, 5), new SimpleValueImpl(DOUBLE, "5.0"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.smallerOrEqual(new SimpleValueImpl(INTEGER, 5), new SimpleValueImpl(DOUBLE, "5"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        //true
        result = ComparisonOperationExecutor.smallerOrEqual(new SimpleValueImpl(SHORT, "2"), new SimpleValueImpl(INTEGER, "5"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.smallerOrEqual(new SimpleValueImpl(DOUBLE, "2.0"), new SimpleValueImpl(INTEGER, 5), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.smallerOrEqual(new SimpleValueImpl(BYTE, "2.0"), new SimpleValueImpl(BYTE, 5), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.smallerOrEqual(new SimpleValueImpl(BYTE, "2.0"), new SimpleValueImpl(BYTE, 5), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        boolean ans = result.getCastedValue();
        assertTrue(ans);


        //false
        result = ComparisonOperationExecutor.smallerOrEqual(new SimpleValueImpl(INTEGER, "5"), new SimpleValueImpl(SHORT, "2"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.smallerOrEqual(new SimpleValueImpl(INTEGER, 5), new SimpleValueImpl(DOUBLE, "2.0"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.smallerOrEqual(new SimpleValueImpl(BYTE, 5), new SimpleValueImpl(BYTE, "2.0"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.smallerOrEqual(new SimpleValueImpl(BYTE, 5), new SimpleValueImpl(BYTE, "2.0"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        ans = result.getCastedValue();
        assertFalse(ans);

        //Errors
        assertThrows(IllegalOperation.class,
                () -> ComparisonOperationExecutor.smallerOrEqual(new SimpleValueImpl(STRING, "2"), new SimpleValueImpl(STRING, 5), mockByteCommandOperation()));
        assertThrows(IllegalOperation.class,
                () -> ComparisonOperationExecutor.smallerOrEqual(new SimpleValueImpl(BOOLEAN, "true"), new SimpleValueImpl(BOOLEAN, false), mockByteCommandOperation()));
    }

    @Test
    void greaterOrEqual() throws Throwable {
        //equal
        SimpleValue result = ComparisonOperationExecutor.greaterOrEqual(new SimpleValueImpl(INTEGER, 5), new SimpleValueImpl(DOUBLE, "5.0"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.greaterOrEqual(new SimpleValueImpl(INTEGER, 5), new SimpleValueImpl(DOUBLE, "5"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        //true
        result = ComparisonOperationExecutor.greaterOrEqual(new SimpleValueImpl(INTEGER, "5"), new SimpleValueImpl(SHORT, "2"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.greaterOrEqual(new SimpleValueImpl(INTEGER, 5), new SimpleValueImpl(DOUBLE, "2.0"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.greaterOrEqual(new SimpleValueImpl(BYTE, 5), new SimpleValueImpl(BYTE, "2.0"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.greaterOrEqual(new SimpleValueImpl(BYTE, 5), new SimpleValueImpl(BYTE, "2.0"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        boolean ans = result.getCastedValue();
        assertTrue(ans);

        //false
        result = ComparisonOperationExecutor.greaterOrEqual(new SimpleValueImpl(SHORT, "2"), new SimpleValueImpl(INTEGER, "5"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.greaterOrEqual(new SimpleValueImpl(DOUBLE, "2.0"), new SimpleValueImpl(INTEGER, 5), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.greaterOrEqual(new SimpleValueImpl(BYTE, "2.0"), new SimpleValueImpl(BYTE, 5), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.greaterOrEqual(new SimpleValueImpl(BYTE, "2.0"), new SimpleValueImpl(BYTE, 5), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        ans = result.getCastedValue();
        assertFalse(ans);

        //Errors
        assertThrows(IllegalOperation.class,
                () -> ComparisonOperationExecutor.greaterOrEqual(new SimpleValueImpl(STRING, "2"), new SimpleValueImpl(STRING, 5), mockByteCommandOperation()));
        assertThrows(IllegalOperation.class,
                () -> ComparisonOperationExecutor.greaterOrEqual(new SimpleValueImpl(BOOLEAN, "true"), new SimpleValueImpl(BOOLEAN, false), mockByteCommandOperation()));
    }

    @Test
    void smaller() throws Throwable {
        //equal
        SimpleValue result = ComparisonOperationExecutor.smaller(new SimpleValueImpl(INTEGER, 5), new SimpleValueImpl(DOUBLE, "5.0"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.smaller(new SimpleValueImpl(INTEGER, 5), new SimpleValueImpl(DOUBLE, "5"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        //true
        result = ComparisonOperationExecutor.smaller(new SimpleValueImpl(SHORT, "2"), new SimpleValueImpl(INTEGER, "5"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.smaller(new SimpleValueImpl(DOUBLE, "2.0"), new SimpleValueImpl(INTEGER, 5), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.smaller(new SimpleValueImpl(BYTE, "2.0"), new SimpleValueImpl(BYTE, 5), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.smaller(new SimpleValueImpl(BYTE, "2.0"), new SimpleValueImpl(BYTE, 5), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        boolean ans = result.getCastedValue();
        assertTrue(ans);


        //false
        result = ComparisonOperationExecutor.smaller(new SimpleValueImpl(INTEGER, "5"), new SimpleValueImpl(SHORT, "2"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.smaller(new SimpleValueImpl(INTEGER, 5), new SimpleValueImpl(DOUBLE, "2.0"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.smaller(new SimpleValueImpl(BYTE, 5), new SimpleValueImpl(BYTE, "2.0"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.smaller(new SimpleValueImpl(BYTE, 5), new SimpleValueImpl(BYTE, "2.0"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        ans = result.getCastedValue();
        assertFalse(ans);

        //Errors
        assertThrows(IllegalOperation.class,
                () -> ComparisonOperationExecutor.smaller(new SimpleValueImpl(STRING, "2"), new SimpleValueImpl(STRING, 5), mockByteCommandOperation()));
        assertThrows(IllegalOperation.class,
                () -> ComparisonOperationExecutor.smaller(new SimpleValueImpl(BOOLEAN, "true"), new SimpleValueImpl(BOOLEAN, false), mockByteCommandOperation()));
    }

    @Test
    void greater() throws Throwable {
        //equal
        SimpleValue result = ComparisonOperationExecutor.greater(new SimpleValueImpl(INTEGER, 5), new SimpleValueImpl(DOUBLE, "5.0"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.greater(new SimpleValueImpl(INTEGER, 5), new SimpleValueImpl(DOUBLE, "5"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        //true
        result = ComparisonOperationExecutor.greater(new SimpleValueImpl(INTEGER, "5"), new SimpleValueImpl(SHORT, "2"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.greater(new SimpleValueImpl(INTEGER, 5), new SimpleValueImpl(DOUBLE, "2.0"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.greater(new SimpleValueImpl(BYTE, 5), new SimpleValueImpl(BYTE, "2.0"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.greater(new SimpleValueImpl(BYTE, 5), new SimpleValueImpl(BYTE, "2.0"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        boolean ans = result.getCastedValue();
        assertTrue(ans);

        //false
        result = ComparisonOperationExecutor.greater(new SimpleValueImpl(SHORT, "2"), new SimpleValueImpl(INTEGER, "5"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.greater(new SimpleValueImpl(DOUBLE, "2.0"), new SimpleValueImpl(INTEGER, 5), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.greater(new SimpleValueImpl(BYTE, "2.0"), new SimpleValueImpl(BYTE, 5), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.greater(new SimpleValueImpl(BYTE, "2.0"), new SimpleValueImpl(BYTE, 5), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        ans = result.getCastedValue();
        assertFalse(ans);

        //Errors
        assertThrows(IllegalOperation.class,
                () -> ComparisonOperationExecutor.greater(new SimpleValueImpl(STRING, "2"), new SimpleValueImpl(STRING, 5), mockByteCommandOperation()));
        assertThrows(IllegalOperation.class,
                () -> ComparisonOperationExecutor.greater(new SimpleValueImpl(BOOLEAN, "true"), new SimpleValueImpl(BOOLEAN, false), mockByteCommandOperation()));
    }

    @Test
    void equal() throws Throwable {
        //equal
        SimpleValue result = ComparisonOperationExecutor.equal(new SimpleValueImpl(INTEGER, 5), new SimpleValueImpl(DOUBLE, "5.0"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.equal(new SimpleValueImpl(INTEGER, 5), new SimpleValueImpl(DOUBLE, "5"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        //not equal
        result = ComparisonOperationExecutor.equal(new SimpleValueImpl(SHORT, "2"), new SimpleValueImpl(INTEGER, "5"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.equal(new SimpleValueImpl(DOUBLE, "2.0"), new SimpleValueImpl(INTEGER, 5), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.equal(new SimpleValueImpl(BYTE, "2.0"), new SimpleValueImpl(BYTE, 5), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.equal(new SimpleValueImpl(BYTE, "2.0"), new SimpleValueImpl(BYTE, 5), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        boolean ans = result.getCastedValue();
        assertFalse(ans);

        
        result = ComparisonOperationExecutor.equal(new SimpleValueImpl(INTEGER, "5"), new SimpleValueImpl(SHORT, "2"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.equal(new SimpleValueImpl(INTEGER, 5), new SimpleValueImpl(DOUBLE, "2.0"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.equal(new SimpleValueImpl(BYTE, 5), new SimpleValueImpl(BYTE, "2.0"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.equal(new SimpleValueImpl(BYTE, 5), new SimpleValueImpl(BYTE, "2.0"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        ans = result.getCastedValue();
        assertFalse(ans);



        //String
        result = ComparisonOperationExecutor.equal(new SimpleValueImpl(STRING, "2"), new SimpleValueImpl(STRING, 5), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.equal(new SimpleValueImpl(STRING, "5.0"), new SimpleValueImpl(STRING, 5), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.equal(new SimpleValueImpl(STRING, "2"), new SimpleValueImpl(INTEGER, 5), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.equal(new SimpleValueImpl(STRING, "5.0"), new SimpleValueImpl(BYTE, 5), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));


        result = ComparisonOperationExecutor.equal(new SimpleValueImpl(STRING, "5.0"), new SimpleValueImpl(DOUBLE, 5), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.equal(new SimpleValueImpl(STRING, "5.0"), new SimpleValueImpl(STRING, 5.0), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));



        //Boolean
        result = ComparisonOperationExecutor.equal(new SimpleValueImpl(BOOLEAN, "true"), new SimpleValueImpl(BOOLEAN, false), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));


        result = ComparisonOperationExecutor.equal(new SimpleValueImpl(BOOLEAN, "false"), new SimpleValueImpl(BOOLEAN, false), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));
    }

    @Test
    void notEqual() throws Throwable {
        //equal
        SimpleValue result = ComparisonOperationExecutor.notEqual(new SimpleValueImpl(INTEGER, 5), new SimpleValueImpl(DOUBLE, "5.0"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.notEqual(new SimpleValueImpl(INTEGER, 5), new SimpleValueImpl(DOUBLE, "5"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        //not equal
        result = ComparisonOperationExecutor.notEqual(new SimpleValueImpl(SHORT, "2"), new SimpleValueImpl(INTEGER, "5"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.notEqual(new SimpleValueImpl(DOUBLE, "2.0"), new SimpleValueImpl(INTEGER, 5), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.notEqual(new SimpleValueImpl(BYTE, "2.0"), new SimpleValueImpl(BYTE, 5), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.notEqual(new SimpleValueImpl(BYTE, "2.0"), new SimpleValueImpl(BYTE, 5), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        boolean ans = result.getCastedValue();
        assertTrue(ans);


        result = ComparisonOperationExecutor.notEqual(new SimpleValueImpl(INTEGER, "5"), new SimpleValueImpl(SHORT, "2"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.notEqual(new SimpleValueImpl(INTEGER, 5), new SimpleValueImpl(DOUBLE, "2.0"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.notEqual(new SimpleValueImpl(BYTE, 5), new SimpleValueImpl(BYTE, "2.0"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.notEqual(new SimpleValueImpl(BYTE, 5), new SimpleValueImpl(BYTE, "2.0"), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        ans = result.getCastedValue();
        assertTrue(ans);



        //String
        result = ComparisonOperationExecutor.notEqual(new SimpleValueImpl(STRING, "2"), new SimpleValueImpl(STRING, 5), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.notEqual(new SimpleValueImpl(STRING, "5.0"), new SimpleValueImpl(STRING, 5), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.notEqual(new SimpleValueImpl(STRING, "2"), new SimpleValueImpl(INTEGER, 5), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.notEqual(new SimpleValueImpl(STRING, "5.0"), new SimpleValueImpl(BYTE, 5), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));


        result = ComparisonOperationExecutor.notEqual(new SimpleValueImpl(STRING, "5.0"), new SimpleValueImpl(DOUBLE, 5), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.notEqual(new SimpleValueImpl(STRING, "5.0"), new SimpleValueImpl(STRING, 5.0), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));



        //Boolean
        result = ComparisonOperationExecutor.notEqual(new SimpleValueImpl(BOOLEAN, "true"), new SimpleValueImpl(BOOLEAN, false), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));


        result = ComparisonOperationExecutor.notEqual(new SimpleValueImpl(BOOLEAN, "false"), new SimpleValueImpl(BOOLEAN, false), mockByteCommandOperation());
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));
    }
}