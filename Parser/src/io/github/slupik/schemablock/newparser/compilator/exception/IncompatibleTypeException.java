package io.github.slupik.schemablock.newparser.compilator.exception;

import io.github.slupik.schemablock.newparser.memory.element.ValueType;

/**
 * All rights reserved & copyright ©
 */
public class IncompatibleTypeException extends Exception {
    public IncompatibleTypeException(ValueType excepted, ValueType actual) {
        super("Incompatibility types: "+excepted+" and "+actual);
    }
}
