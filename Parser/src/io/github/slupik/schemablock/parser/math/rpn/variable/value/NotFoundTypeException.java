package io.github.slupik.schemablock.parser.math.rpn.variable.value;

/**
 * All rights reserved & copyright ©
 */
public class NotFoundTypeException extends Exception {
    public NotFoundTypeException(String value) {
        super("Type for string: "+value+", hasn't been found");
    }

    public NotFoundTypeException(Object value) {
        super("Type for object: "+value+", hasn't been found");
    }
}
