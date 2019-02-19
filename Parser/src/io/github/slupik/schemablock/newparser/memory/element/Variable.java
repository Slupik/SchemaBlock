package io.github.slupik.schemablock.newparser.memory.element;

import io.github.slupik.schemablock.newparser.compilator.exception.IncompatibleArrayException;
import io.github.slupik.schemablock.newparser.compilator.exception.IncompatibleTypeException;

/**
 * All rights reserved & copyright ©
 */
public interface Variable extends Memoryable  {
    String getName();
    int getDimensionsCount();
    Value getContent();
    void setContent(Value value) throws IncompatibleTypeException, IncompatibleArrayException;
}
