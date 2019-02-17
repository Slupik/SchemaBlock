package io.github.slupik.schemablock.newparser.executor.implementation;

import io.github.slupik.schemablock.newparser.memory.element.Value;
import io.github.slupik.schemablock.newparser.memory.element.ValueImpl;
import org.junit.jupiter.api.Test;

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
        Value result = ComparisonOperationExecutor.smallerOrEqual(new ValueImpl(INTEGER, 5), new ValueImpl(DOUBLE, "5.0"));
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.smallerOrEqual(new ValueImpl(INTEGER, 5), new ValueImpl(DOUBLE, "5"));
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        //true
        result = ComparisonOperationExecutor.smallerOrEqual(new ValueImpl(SHORT, "2"), new ValueImpl(INTEGER, "5"));
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.smallerOrEqual(new ValueImpl(DOUBLE, "2.0"), new ValueImpl(INTEGER, 5));
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.smallerOrEqual(new ValueImpl(BYTE, "2.0"), new ValueImpl(BYTE, 5));
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.smallerOrEqual(new ValueImpl(BYTE, "2.0"), new ValueImpl(BYTE, 5));
        assertEquals(BOOLEAN, result.getType());
        boolean ans = result.getCastedValue();
        assertTrue(ans);


        //false
        result = ComparisonOperationExecutor.smallerOrEqual(new ValueImpl(INTEGER, "5"), new ValueImpl(SHORT, "2"));
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.smallerOrEqual(new ValueImpl(INTEGER, 5), new ValueImpl(DOUBLE, "2.0"));
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.smallerOrEqual(new ValueImpl(BYTE, 5), new ValueImpl(BYTE, "2.0"));
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.smallerOrEqual(new ValueImpl(BYTE, 5), new ValueImpl(BYTE, "2.0"));
        assertEquals(BOOLEAN, result.getType());
        ans = result.getCastedValue();
        assertFalse(ans);

        //Errors
        assertThrows(IllegalOperation.class,
                () -> ComparisonOperationExecutor.smallerOrEqual(new ValueImpl(STRING, "2"), new ValueImpl(STRING, 5)));
        assertThrows(IllegalOperation.class,
                () -> ComparisonOperationExecutor.smallerOrEqual(new ValueImpl(BOOLEAN, "true"), new ValueImpl(BOOLEAN, false)));
    }

    @Test
    void greaterOrEqual() throws Throwable {
        //equal
        Value result = ComparisonOperationExecutor.greaterOrEqual(new ValueImpl(INTEGER, 5), new ValueImpl(DOUBLE, "5.0"));
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.greaterOrEqual(new ValueImpl(INTEGER, 5), new ValueImpl(DOUBLE, "5"));
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        //true
        result = ComparisonOperationExecutor.greaterOrEqual(new ValueImpl(INTEGER, "5"), new ValueImpl(SHORT, "2"));
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.greaterOrEqual(new ValueImpl(INTEGER, 5), new ValueImpl(DOUBLE, "2.0"));
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.greaterOrEqual(new ValueImpl(BYTE, 5), new ValueImpl(BYTE, "2.0"));
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.greaterOrEqual(new ValueImpl(BYTE, 5), new ValueImpl(BYTE, "2.0"));
        assertEquals(BOOLEAN, result.getType());
        boolean ans = result.getCastedValue();
        assertTrue(ans);

        //false
        result = ComparisonOperationExecutor.greaterOrEqual(new ValueImpl(SHORT, "2"), new ValueImpl(INTEGER, "5"));
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.greaterOrEqual(new ValueImpl(DOUBLE, "2.0"), new ValueImpl(INTEGER, 5));
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.greaterOrEqual(new ValueImpl(BYTE, "2.0"), new ValueImpl(BYTE, 5));
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.greaterOrEqual(new ValueImpl(BYTE, "2.0"), new ValueImpl(BYTE, 5));
        assertEquals(BOOLEAN, result.getType());
        ans = result.getCastedValue();
        assertFalse(ans);

        //Errors
        assertThrows(IllegalOperation.class,
                () -> ComparisonOperationExecutor.greaterOrEqual(new ValueImpl(STRING, "2"), new ValueImpl(STRING, 5)));
        assertThrows(IllegalOperation.class,
                () -> ComparisonOperationExecutor.greaterOrEqual(new ValueImpl(BOOLEAN, "true"), new ValueImpl(BOOLEAN, false)));
    }

    @Test
    void smaller() throws Throwable {
        //equal
        Value result = ComparisonOperationExecutor.smaller(new ValueImpl(INTEGER, 5), new ValueImpl(DOUBLE, "5.0"));
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.smaller(new ValueImpl(INTEGER, 5), new ValueImpl(DOUBLE, "5"));
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        //true
        result = ComparisonOperationExecutor.smaller(new ValueImpl(SHORT, "2"), new ValueImpl(INTEGER, "5"));
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.smaller(new ValueImpl(DOUBLE, "2.0"), new ValueImpl(INTEGER, 5));
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.smaller(new ValueImpl(BYTE, "2.0"), new ValueImpl(BYTE, 5));
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.smaller(new ValueImpl(BYTE, "2.0"), new ValueImpl(BYTE, 5));
        assertEquals(BOOLEAN, result.getType());
        boolean ans = result.getCastedValue();
        assertTrue(ans);


        //false
        result = ComparisonOperationExecutor.smaller(new ValueImpl(INTEGER, "5"), new ValueImpl(SHORT, "2"));
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.smaller(new ValueImpl(INTEGER, 5), new ValueImpl(DOUBLE, "2.0"));
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.smaller(new ValueImpl(BYTE, 5), new ValueImpl(BYTE, "2.0"));
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.smaller(new ValueImpl(BYTE, 5), new ValueImpl(BYTE, "2.0"));
        assertEquals(BOOLEAN, result.getType());
        ans = result.getCastedValue();
        assertFalse(ans);

        //Errors
        assertThrows(IllegalOperation.class,
                () -> ComparisonOperationExecutor.smaller(new ValueImpl(STRING, "2"), new ValueImpl(STRING, 5)));
        assertThrows(IllegalOperation.class,
                () -> ComparisonOperationExecutor.smaller(new ValueImpl(BOOLEAN, "true"), new ValueImpl(BOOLEAN, false)));
    }

    @Test
    void greater() throws Throwable {
        //equal
        Value result = ComparisonOperationExecutor.greater(new ValueImpl(INTEGER, 5), new ValueImpl(DOUBLE, "5.0"));
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.greater(new ValueImpl(INTEGER, 5), new ValueImpl(DOUBLE, "5"));
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        //true
        result = ComparisonOperationExecutor.greater(new ValueImpl(INTEGER, "5"), new ValueImpl(SHORT, "2"));
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.greater(new ValueImpl(INTEGER, 5), new ValueImpl(DOUBLE, "2.0"));
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.greater(new ValueImpl(BYTE, 5), new ValueImpl(BYTE, "2.0"));
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.greater(new ValueImpl(BYTE, 5), new ValueImpl(BYTE, "2.0"));
        assertEquals(BOOLEAN, result.getType());
        boolean ans = result.getCastedValue();
        assertTrue(ans);

        //false
        result = ComparisonOperationExecutor.greater(new ValueImpl(SHORT, "2"), new ValueImpl(INTEGER, "5"));
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.greater(new ValueImpl(DOUBLE, "2.0"), new ValueImpl(INTEGER, 5));
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.greater(new ValueImpl(BYTE, "2.0"), new ValueImpl(BYTE, 5));
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.greater(new ValueImpl(BYTE, "2.0"), new ValueImpl(BYTE, 5));
        assertEquals(BOOLEAN, result.getType());
        ans = result.getCastedValue();
        assertFalse(ans);

        //Errors
        assertThrows(IllegalOperation.class,
                () -> ComparisonOperationExecutor.greater(new ValueImpl(STRING, "2"), new ValueImpl(STRING, 5)));
        assertThrows(IllegalOperation.class,
                () -> ComparisonOperationExecutor.greater(new ValueImpl(BOOLEAN, "true"), new ValueImpl(BOOLEAN, false)));
    }

    @Test
    void equal() throws Throwable {
        //equal
        Value result = ComparisonOperationExecutor.equal(new ValueImpl(INTEGER, 5), new ValueImpl(DOUBLE, "5.0"));
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.equal(new ValueImpl(INTEGER, 5), new ValueImpl(DOUBLE, "5"));
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        //not equal
        result = ComparisonOperationExecutor.equal(new ValueImpl(SHORT, "2"), new ValueImpl(INTEGER, "5"));
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.equal(new ValueImpl(DOUBLE, "2.0"), new ValueImpl(INTEGER, 5));
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.equal(new ValueImpl(BYTE, "2.0"), new ValueImpl(BYTE, 5));
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.equal(new ValueImpl(BYTE, "2.0"), new ValueImpl(BYTE, 5));
        assertEquals(BOOLEAN, result.getType());
        boolean ans = result.getCastedValue();
        assertFalse(ans);

        
        result = ComparisonOperationExecutor.equal(new ValueImpl(INTEGER, "5"), new ValueImpl(SHORT, "2"));
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.equal(new ValueImpl(INTEGER, 5), new ValueImpl(DOUBLE, "2.0"));
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.equal(new ValueImpl(BYTE, 5), new ValueImpl(BYTE, "2.0"));
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.equal(new ValueImpl(BYTE, 5), new ValueImpl(BYTE, "2.0"));
        assertEquals(BOOLEAN, result.getType());
        ans = result.getCastedValue();
        assertFalse(ans);



        //String
        result = ComparisonOperationExecutor.equal(new ValueImpl(STRING, "2"), new ValueImpl(STRING, 5));
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.equal(new ValueImpl(STRING, "5.0"), new ValueImpl(STRING, 5));
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.equal(new ValueImpl(STRING, "2"), new ValueImpl(INTEGER, 5));
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.equal(new ValueImpl(STRING, "5.0"), new ValueImpl(BYTE, 5));
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));


        result = ComparisonOperationExecutor.equal(new ValueImpl(STRING, "5.0"), new ValueImpl(DOUBLE, 5));
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.equal(new ValueImpl(STRING, "5.0"), new ValueImpl(STRING, 5.0));
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));



        //Boolean
        result = ComparisonOperationExecutor.equal(new ValueImpl(BOOLEAN, "true"), new ValueImpl(BOOLEAN, false));
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));


        result = ComparisonOperationExecutor.equal(new ValueImpl(BOOLEAN, "false"), new ValueImpl(BOOLEAN, false));
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));
    }

    @Test
    void notEqual() throws Throwable {
        //equal
        Value result = ComparisonOperationExecutor.notEqual(new ValueImpl(INTEGER, 5), new ValueImpl(DOUBLE, "5.0"));
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.notEqual(new ValueImpl(INTEGER, 5), new ValueImpl(DOUBLE, "5"));
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        //not equal
        result = ComparisonOperationExecutor.notEqual(new ValueImpl(SHORT, "2"), new ValueImpl(INTEGER, "5"));
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.notEqual(new ValueImpl(DOUBLE, "2.0"), new ValueImpl(INTEGER, 5));
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.notEqual(new ValueImpl(BYTE, "2.0"), new ValueImpl(BYTE, 5));
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.notEqual(new ValueImpl(BYTE, "2.0"), new ValueImpl(BYTE, 5));
        assertEquals(BOOLEAN, result.getType());
        boolean ans = result.getCastedValue();
        assertTrue(ans);


        result = ComparisonOperationExecutor.notEqual(new ValueImpl(INTEGER, "5"), new ValueImpl(SHORT, "2"));
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.notEqual(new ValueImpl(INTEGER, 5), new ValueImpl(DOUBLE, "2.0"));
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.notEqual(new ValueImpl(BYTE, 5), new ValueImpl(BYTE, "2.0"));
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.notEqual(new ValueImpl(BYTE, 5), new ValueImpl(BYTE, "2.0"));
        assertEquals(BOOLEAN, result.getType());
        ans = result.getCastedValue();
        assertTrue(ans);



        //String
        result = ComparisonOperationExecutor.notEqual(new ValueImpl(STRING, "2"), new ValueImpl(STRING, 5));
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.notEqual(new ValueImpl(STRING, "5.0"), new ValueImpl(STRING, 5));
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.notEqual(new ValueImpl(STRING, "2"), new ValueImpl(INTEGER, 5));
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.notEqual(new ValueImpl(STRING, "5.0"), new ValueImpl(BYTE, 5));
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));


        result = ComparisonOperationExecutor.notEqual(new ValueImpl(STRING, "5.0"), new ValueImpl(DOUBLE, 5));
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));

        result = ComparisonOperationExecutor.notEqual(new ValueImpl(STRING, "5.0"), new ValueImpl(STRING, 5.0));
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));



        //Boolean
        result = ComparisonOperationExecutor.notEqual(new ValueImpl(BOOLEAN, "true"), new ValueImpl(BOOLEAN, false));
        assertEquals(BOOLEAN, result.getType());
        assertTrue(((boolean) result.getValue()));


        result = ComparisonOperationExecutor.notEqual(new ValueImpl(BOOLEAN, "false"), new ValueImpl(BOOLEAN, false));
        assertEquals(BOOLEAN, result.getType());
        assertFalse(((boolean) result.getValue()));
    }
}