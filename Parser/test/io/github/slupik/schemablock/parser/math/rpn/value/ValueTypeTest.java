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
        checkValue(ValueType.INT, "1234555");
        checkValue(ValueType.LONG, "123454234324255");
        checkValue(ValueType.FLOAT, "1234542.4");
        checkValue(ValueType.DOUBLE, "432432423434234324242242342342534543534534342432.423");
        checkValue(ValueType.CHAR, "c");
        checkValue(ValueType.STRING, "\"String for tests purposes\"");
    }

    private void checkValue(ValueType excepted, String value) throws NotFoundTypeException {
        assertEquals(excepted, ValueType.getType(value));
    }

    private static void keepInports(){
        assertEquals(1, 1);
    }
}