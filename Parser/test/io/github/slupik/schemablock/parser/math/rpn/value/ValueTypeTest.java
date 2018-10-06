package io.github.slupik.schemablock.parser.math.rpn.value;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * All rights reserved & copyright ©
 */
class ValueTypeTest {

    @Test
    void getType() throws NotFoundTypeException {
        checkValue(ValueType.SHORT, "123");
        checkStandardizedValue(ValueType.INT, "123");
        checkStandardizedValue(ValueType.INT, "1234555");
        checkStandardizedValue(ValueType.LONG, "123454234324255");
        checkStandardizedValue(ValueType.DOUBLE, "1234542.4");
        checkStandardizedValue(ValueType.DOUBLE, "432432423434234324242242342342534543534534342432.423");
        checkStandardizedValue(ValueType.CHAR, "c");
        checkStandardizedValue(ValueType.STRING, "\"String for tests purposes\"");

        checkStandardizedValue(ValueType.INT, "-12345");

        checkValue(ValueType.FLOAT, "1234542.4");
        checkValue(ValueType.SHORT, "-12345");
    }

    private void checkStandardizedValue(ValueType excepted, String value) throws NotFoundTypeException {
        assertEquals(excepted, ValueType.getStandardizedType(value));
    }

    private void checkValue(ValueType excepted, String value) throws NotFoundTypeException {
        assertEquals(excepted, ValueType.getType(value));
    }

    private static void keepInports(){
        assertEquals(1, 1);
    }
}