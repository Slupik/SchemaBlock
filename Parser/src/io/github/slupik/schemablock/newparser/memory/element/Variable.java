package io.github.slupik.schemablock.newparser.memory.element;

import io.github.slupik.schemablock.newparser.compilator.exception.ExceptedArrayButNotReceivedException;
import io.github.slupik.schemablock.newparser.compilator.exception.IncompatibleArrayException;
import io.github.slupik.schemablock.newparser.compilator.exception.IncompatibleTypeException;
import io.github.slupik.schemablock.newparser.compilator.exception.IndexOutOfBoundsException;

/**
 * All rights reserved & copyright ©
 */
public interface Variable extends Memoryable  {
    String getName();
    Value getValue();
    void setValue(Value value) throws IncompatibleTypeException, IncompatibleArrayException;

    //if is an array
    Value getValue(int index) throws ExceptedArrayButNotReceivedException, IndexOutOfBoundsException;
    void setValue(int index, Value value) throws IncompatibleTypeException, IndexOutOfBoundsException, ExceptedArrayButNotReceivedException, IncompatibleArrayException;
    void setValue(int indexes[], Value value) throws IncompatibleTypeException, IndexOutOfBoundsException, ExceptedArrayButNotReceivedException, IncompatibleArrayException;
}
