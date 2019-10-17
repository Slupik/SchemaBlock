package io.github.slupik.schemablock.newparser.memory;

import io.github.slupik.schemablock.newparser.memory.element.SimpleValue;
import io.github.slupik.schemablock.newparser.memory.element.SimpleValueImpl;
import io.github.slupik.schemablock.newparser.memory.element.ValueType;

/**
 * All rights reserved & copyright ©
 */
public class ValueFactory {

    public static SimpleValue createValue(ValueType type, String value) {
        return new SimpleValueImpl(type, value);
    }

}
