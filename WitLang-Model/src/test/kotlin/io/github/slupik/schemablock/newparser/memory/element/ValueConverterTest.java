package io.github.slupik.schemablock.newparser.memory.element;

import org.junit.jupiter.api.Test;

import static io.github.slupik.schemablock.newparser.memory.element.ValueType.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * All rights reserved & copyright ©
 */
class ValueConverterTest {

    @Test
    void checkCasting() {
        assertTrue(ValueConverter.castValueToType(DOUBLE, "5") instanceof Double);
        assertTrue(ValueConverter.castValueToType(DOUBLE, 5) instanceof Double);
        assertTrue(ValueConverter.castValueToType(INTEGER, 5.0) instanceof Integer);
        assertTrue(ValueConverter.castValueToType(BYTE, 5.0) instanceof Byte);

        assertTrue(ValueConverter.castValueToType(BOOLEAN, true) instanceof Boolean);
        assertTrue(ValueConverter.castValueToType(BOOLEAN, "true") instanceof Boolean);
    }
}