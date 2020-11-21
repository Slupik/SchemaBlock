package io.github.slupik.schemablock.newparser.executor.implementation.exception;

import io.github.slupik.schemablock.InternalException;

/**
 * All rights reserved & copyright ©
 */
public class UnknownMemoryElement extends InternalException {

    public UnknownMemoryElement() {
        super("Element returned by memory is unknown.");
    }

}
