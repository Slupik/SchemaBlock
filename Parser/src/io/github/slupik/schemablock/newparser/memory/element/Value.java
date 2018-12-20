package io.github.slupik.schemablock.newparser.memory.element;

import io.github.slupik.schemablock.newparser.executor.exception.ExceptedMoreDimensionsThanExists;
import io.github.slupik.schemablock.newparser.executor.exception.IncompatibleArrayException;
import io.github.slupik.schemablock.newparser.executor.exception.IncompatibleTypeException;
import io.github.slupik.schemablock.newparser.executor.exception.IndexOutOfBoundsException;

/**
 * All rights reserved & copyright ©
 */
public interface Value extends Memoryable {
    Object getValue();
    Value getValue(int index) throws IndexOutOfBoundsException;
    Value getValue(int[] indexes) throws IndexOutOfBoundsException, ExceptedMoreDimensionsThanExists;
    void setValue(int index, Value value) throws IndexOutOfBoundsException, IncompatibleArrayException, IncompatibleTypeException;
    void setValue(int indexes[], Value value) throws IndexOutOfBoundsException, IncompatibleArrayException, IncompatibleTypeException;

    // if is an array...
    int getArrayLength();
}
