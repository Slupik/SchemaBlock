package io.github.slupik.schemablock.newparser.utils;

/**
 * All rights reserved & copyright ©
 */
public class ValueTooBig extends Throwable {

    public ValueTooBig() {
        super("Given number is too big to be processed.");
    }
}
