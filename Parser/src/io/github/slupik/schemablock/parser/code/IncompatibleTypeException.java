package io.github.slupik.schemablock.parser.code;

import io.github.slupik.schemablock.parser.math.rpn.variable.value.ValueType;

/**
 * All rights reserved & copyright ©
 */
public class IncompatibleTypeException extends Exception {
    public IncompatibleTypeException(ValueType type1, ValueType type2) {
        super("Excepted type "+type1+" but received "+type2);
    }
}
