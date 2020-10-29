package io.github.slupik.schemablock.newparser.executor.implementation;

import io.github.slupik.schemablock.newparser.memory.element.SimpleValue;
import io.github.slupik.schemablock.newparser.memory.element.SimpleValueImpl;
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
        SimpleValue result = MathOperationExecutor.add(new SimpleValueImpl(SHORT, "2"), new SimpleValueImpl(INTEGER, "5"));
        assertEquals(INTEGER, result.getType());
        assertEquals(7, ((int) result.getValue()));

        result = MathOperationExecutor.add(new SimpleValueImpl(DOUBLE, "2.0"), new SimpleValueImpl(INTEGER, 5));
        assertEquals(DOUBLE, result.getType());
        assertEquals(7, ((double) result.getValue()));

        result = MathOperationExecutor.add(new SimpleValueImpl(BYTE, "2.0"), new SimpleValueImpl(BYTE, 5));
        assertEquals(BYTE, result.getType());
        assertEquals(7, ((byte) result.getValue()));

        result = MathOperationExecutor.add(new SimpleValueImpl(BYTE, "2.0"), new SimpleValueImpl(BYTE, 5));
        assertEquals(BYTE, result.getType());
        byte ans = result.getCastedValue();
        assertEquals(7, ans);

        //String
        result = MathOperationExecutor.add(new SimpleValueImpl(STRING, "foo"), new SimpleValueImpl(STRING, "bar"));
        assertEquals(STRING, result.getType());
        assertEquals("foobar", result.getValue());

        result = MathOperationExecutor.add(new SimpleValueImpl(STRING, "2"), new SimpleValueImpl(STRING, "5"));
        assertEquals(STRING, result.getType());
        assertEquals("25", result.getValue());

        result = MathOperationExecutor.add(new SimpleValueImpl(STRING, "2"), new SimpleValueImpl(INTEGER, "5"));
        assertEquals(STRING, result.getType());
        assertEquals("25", result.getValue());

        result = MathOperationExecutor.add(new SimpleValueImpl(STRING, 2), new SimpleValueImpl(INTEGER, "5"));
        assertEquals(STRING, result.getType());
        assertEquals("25", result.getValue());

        result = MathOperationExecutor.add(new SimpleValueImpl(STRING, "2"), new SimpleValueImpl(INTEGER, 5));
        assertEquals(STRING, result.getType());
        assertEquals("25", result.getValue());

        result = MathOperationExecutor.add(new SimpleValueImpl(INTEGER, "2"), new SimpleValueImpl(STRING, "5"));
        assertEquals(STRING, result.getType());
        assertEquals("25", result.getValue());

        result = MathOperationExecutor.add(new SimpleValueImpl(INTEGER, 2), new SimpleValueImpl(STRING, "5"));
        assertEquals(STRING, result.getType());
        assertEquals("25", result.getValue());

        result = MathOperationExecutor.add(new SimpleValueImpl(INTEGER, "2"), new SimpleValueImpl(STRING, 5));
        assertEquals(STRING, result.getType());
        assertEquals("25", result.getValue());

        //Errors
        assertThrows(IllegalOperation.class,
                () -> MathOperationExecutor.add(new SimpleValueImpl(BOOLEAN, "true"), new SimpleValueImpl(BOOLEAN, false)));
    }

    @Test
    void subtract() throws IllegalOperation {
        //Numbers >0
        SimpleValue result = MathOperationExecutor.subtract(new SimpleValueImpl(SHORT, "5"), new SimpleValueImpl(INTEGER, "2"));
        assertEquals(INTEGER, result.getType());
        assertEquals(3, ((int) result.getValue()));

        result = MathOperationExecutor.subtract(new SimpleValueImpl(DOUBLE, "5.0"), new SimpleValueImpl(INTEGER, 2));
        assertEquals(DOUBLE, result.getType());
        assertEquals(3, ((double) result.getValue()));

        result = MathOperationExecutor.subtract(new SimpleValueImpl(BYTE, "5.0"), new SimpleValueImpl(BYTE, 2));
        assertEquals(BYTE, result.getType());
        assertEquals(3, ((byte) result.getValue()));

        result = MathOperationExecutor.subtract(new SimpleValueImpl(BYTE, "5.0"), new SimpleValueImpl(BYTE, 2));
        assertEquals(BYTE, result.getType());
        byte ans = result.getCastedValue();
        assertEquals(3, ans);

        //Numbers <0
        result = MathOperationExecutor.subtract(new SimpleValueImpl(SHORT, "2"), new SimpleValueImpl(INTEGER, "5"));
        assertEquals(INTEGER, result.getType());
        assertEquals(-3, ((int) result.getValue()));

        result = MathOperationExecutor.subtract(new SimpleValueImpl(DOUBLE, "2.0"), new SimpleValueImpl(INTEGER, 5));
        assertEquals(DOUBLE, result.getType());
        assertEquals(-3, ((double) result.getValue()));

        result = MathOperationExecutor.subtract(new SimpleValueImpl(BYTE, "2.0"), new SimpleValueImpl(BYTE, 5));
        assertEquals(BYTE, result.getType());
        assertEquals(-3, ((byte) result.getValue()));

        result = MathOperationExecutor.subtract(new SimpleValueImpl(BYTE, "2.0"), new SimpleValueImpl(BYTE, 5));
        assertEquals(BYTE, result.getType());
        ans = result.getCastedValue();
        assertEquals(-3, ans);

        //Errors
        assertThrows(IllegalOperation.class,
                () -> MathOperationExecutor.subtract(new SimpleValueImpl(BOOLEAN, "true"), new SimpleValueImpl(BOOLEAN, false)));

        //Errors
        assertThrows(IllegalOperation.class,
                () -> MathOperationExecutor.subtract(new SimpleValueImpl(STRING, "foo"), new SimpleValueImpl(STRING, "bar")));
    }

    @Test
    void multiply() throws IllegalOperation {
        //Numbers >0
        SimpleValue result = MathOperationExecutor.multiply(new SimpleValueImpl(SHORT, "5"), new SimpleValueImpl(INTEGER, "2"));
        assertEquals(INTEGER, result.getType());
        assertEquals(10, ((int) result.getValue()));

        result = MathOperationExecutor.multiply(new SimpleValueImpl(DOUBLE, "5.0"), new SimpleValueImpl(INTEGER, 2));
        assertEquals(DOUBLE, result.getType());
        assertEquals(10, ((double) result.getValue()));

        result = MathOperationExecutor.multiply(new SimpleValueImpl(BYTE, "5.0"), new SimpleValueImpl(BYTE, 2));
        assertEquals(BYTE, result.getType());
        assertEquals(10, ((byte) result.getValue()));

        result = MathOperationExecutor.multiply(new SimpleValueImpl(BYTE, "5.0"), new SimpleValueImpl(BYTE, 2));
        assertEquals(BYTE, result.getType());
        byte ans = result.getCastedValue();
        assertEquals(10, ans);

        //Numbers <0
        result = MathOperationExecutor.multiply(new SimpleValueImpl(SHORT, "-2"), new SimpleValueImpl(INTEGER, "5"));
        assertEquals(INTEGER, result.getType());
        assertEquals(-10, ((int) result.getValue()));

        result = MathOperationExecutor.multiply(new SimpleValueImpl(DOUBLE, "-2.0"), new SimpleValueImpl(INTEGER, 5));
        assertEquals(DOUBLE, result.getType());
        assertEquals(-10, ((double) result.getValue()));

        result = MathOperationExecutor.multiply(new SimpleValueImpl(BYTE, "2.0"), new SimpleValueImpl(BYTE, -5));
        assertEquals(BYTE, result.getType());
        assertEquals(-10, ((byte) result.getValue()));

        result = MathOperationExecutor.multiply(new SimpleValueImpl(BYTE, "2.0"), new SimpleValueImpl(BYTE, -5));
        assertEquals(BYTE, result.getType());
        ans = result.getCastedValue();
        assertEquals(-10, ans);

        //Errors
        assertThrows(IllegalOperation.class,
                () -> MathOperationExecutor.multiply(new SimpleValueImpl(BOOLEAN, "true"), new SimpleValueImpl(BOOLEAN, false)));

        //Errors
        assertThrows(IllegalOperation.class,
                () -> MathOperationExecutor.multiply(new SimpleValueImpl(STRING, "foo"), new SimpleValueImpl(STRING, "bar")));
    }

    @Test
    void divideWithRest() throws IllegalOperation {
        //Numbers >0
        SimpleValue result = MathOperationExecutor.divide(new SimpleValueImpl(SHORT, "5"), new SimpleValueImpl(INTEGER, "2"), true);
        assertEquals(INTEGER, result.getType());
        assertEquals(2, ((int) result.getValue()));

        result = MathOperationExecutor.divide(new SimpleValueImpl(DOUBLE, "5.0"), new SimpleValueImpl(INTEGER, 2), true);
        assertEquals(DOUBLE, result.getType());
        assertEquals(2.5, ((double) result.getValue()));

        result = MathOperationExecutor.divide(new SimpleValueImpl(BYTE, "5.0"), new SimpleValueImpl(BYTE, 2), true);
        assertEquals(BYTE, result.getType());
        assertEquals(2, ((byte) result.getValue()));

        result = MathOperationExecutor.divide(new SimpleValueImpl(BYTE, "5.0"), new SimpleValueImpl(BYTE, 2), true);
        assertEquals(BYTE, result.getType());
        byte ans = result.getCastedValue();
        assertEquals(2, ans);

        //Numbers <0
        result = MathOperationExecutor.divide(new SimpleValueImpl(SHORT, "-5"), new SimpleValueImpl(INTEGER, "2"), true);
        assertEquals(INTEGER, result.getType());
        assertEquals(-2, ((int) result.getValue()));

        result = MathOperationExecutor.divide(new SimpleValueImpl(DOUBLE, "-5.0"), new SimpleValueImpl(INTEGER, 2), true);
        assertEquals(DOUBLE, result.getType());
        assertEquals(-2.5, ((double) result.getValue()));

        result = MathOperationExecutor.divide(new SimpleValueImpl(BYTE, "5.0"), new SimpleValueImpl(BYTE, -2), true);
        assertEquals(BYTE, result.getType());
        assertEquals(-2, ((byte) result.getValue()));

        result = MathOperationExecutor.divide(new SimpleValueImpl(BYTE, "5.0"), new SimpleValueImpl(BYTE, -2), true);
        assertEquals(BYTE, result.getType());
        ans = result.getCastedValue();
        assertEquals(-2, ans);
        //Errors
        assertThrows(IllegalOperation.class,
                () -> MathOperationExecutor.divide(new SimpleValueImpl(BOOLEAN, "true"), new SimpleValueImpl(BOOLEAN, false), true));

        //Errors
        assertThrows(IllegalOperation.class,
                () -> MathOperationExecutor.divide(new SimpleValueImpl(STRING, "foo"), new SimpleValueImpl(STRING, "bar"), true));
    }

    @Test
    void divideWithoutRest() throws IllegalOperation {
        //Numbers >0
        SimpleValue result = MathOperationExecutor.divide(new SimpleValueImpl(SHORT, "5"), new SimpleValueImpl(INTEGER, "2"), false);
        assertEquals(INTEGER, result.getType());
        assertEquals(2, ((int) result.getValue()));

        result = MathOperationExecutor.divide(new SimpleValueImpl(DOUBLE, "5.0"), new SimpleValueImpl(INTEGER, 2), false);
        assertEquals(DOUBLE, result.getType());
        assertEquals(2, ((double) result.getValue()));

        result = MathOperationExecutor.divide(new SimpleValueImpl(BYTE, "5.0"), new SimpleValueImpl(BYTE, 2), false);
        assertEquals(BYTE, result.getType());
        assertEquals(2, ((byte) result.getValue()));

        result = MathOperationExecutor.divide(new SimpleValueImpl(BYTE, "5.0"), new SimpleValueImpl(BYTE, 2), false);
        assertEquals(BYTE, result.getType());
        byte ans = result.getCastedValue();
        assertEquals(2, ans);

        //Numbers <0
        result = MathOperationExecutor.divide(new SimpleValueImpl(SHORT, "-5"), new SimpleValueImpl(INTEGER, "2"), false);
        assertEquals(INTEGER, result.getType());
        assertEquals(-2, ((int) result.getValue()));

        result = MathOperationExecutor.divide(new SimpleValueImpl(DOUBLE, "-5.0"), new SimpleValueImpl(INTEGER, 2), false);
        assertEquals(DOUBLE, result.getType());
        assertEquals(-2, ((double) result.getValue()));

        result = MathOperationExecutor.divide(new SimpleValueImpl(BYTE, "5.0"), new SimpleValueImpl(BYTE, -2), false);
        assertEquals(BYTE, result.getType());
        assertEquals(-2, ((byte) result.getValue()));

        result = MathOperationExecutor.divide(new SimpleValueImpl(BYTE, "5.0"), new SimpleValueImpl(BYTE, -2), false);
        assertEquals(BYTE, result.getType());
        ans = result.getCastedValue();
        assertEquals(-2, ans);
        //Errors
        assertThrows(IllegalOperation.class,
                () -> MathOperationExecutor.divide(new SimpleValueImpl(BOOLEAN, "true"), new SimpleValueImpl(BOOLEAN, false), false));

        //Errors
        assertThrows(IllegalOperation.class,
                () -> MathOperationExecutor.divide(new SimpleValueImpl(STRING, "foo"), new SimpleValueImpl(STRING, "bar"), false));
    }

    @Test
    void modulo() throws IllegalOperation {
        //Numbers >0
        SimpleValue result = MathOperationExecutor.modulo(new SimpleValueImpl(SHORT, "5"), new SimpleValueImpl(INTEGER, "2"));
        assertEquals(INTEGER, result.getType());
        assertEquals(1, ((int) result.getValue()));

        result = MathOperationExecutor.modulo(new SimpleValueImpl(DOUBLE, "5.0"), new SimpleValueImpl(INTEGER, 2));
        assertEquals(DOUBLE, result.getType());
        assertEquals(1, ((double) result.getValue()));

        result = MathOperationExecutor.modulo(new SimpleValueImpl(BYTE, "5.0"), new SimpleValueImpl(BYTE, 2));
        assertEquals(BYTE, result.getType());
        assertEquals(1, ((byte) result.getValue()));

        result = MathOperationExecutor.modulo(new SimpleValueImpl(BYTE, "5.0"), new SimpleValueImpl(BYTE, 2));
        assertEquals(BYTE, result.getType());
        byte ans = result.getCastedValue();
        assertEquals(1, ans);

        //Numbers <0
        result = MathOperationExecutor.modulo(new SimpleValueImpl(SHORT, "-2"), new SimpleValueImpl(INTEGER, "5"));
        assertEquals(INTEGER, result.getType());
        assertEquals(-2, ((int) result.getValue()));

        result = MathOperationExecutor.modulo(new SimpleValueImpl(DOUBLE, "-2.0"), new SimpleValueImpl(INTEGER, 5));
        assertEquals(DOUBLE, result.getType());
        assertEquals(-2, ((double) result.getValue()));

        result = MathOperationExecutor.modulo(new SimpleValueImpl(BYTE, "2.0"), new SimpleValueImpl(BYTE, -5));
        assertEquals(BYTE, result.getType());
        assertEquals(2, ((byte) result.getValue()));

        result = MathOperationExecutor.modulo(new SimpleValueImpl(BYTE, "2.0"), new SimpleValueImpl(BYTE, -5));
        assertEquals(BYTE, result.getType());
        ans = result.getCastedValue();
        assertEquals(2, ans);

        //Errors
        assertThrows(IllegalOperation.class,
                () -> MathOperationExecutor.modulo(new SimpleValueImpl(BOOLEAN, "true"), new SimpleValueImpl(BOOLEAN, false)));

        //Errors
        assertThrows(IllegalOperation.class,
                () -> MathOperationExecutor.modulo(new SimpleValueImpl(STRING, "foo"), new SimpleValueImpl(STRING, "bar")));
    }
}