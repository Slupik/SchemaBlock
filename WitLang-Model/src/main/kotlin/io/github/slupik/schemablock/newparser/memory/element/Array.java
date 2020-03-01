package io.github.slupik.schemablock.newparser.memory.element;

import io.github.slupik.schemablock.model.ui.error.AlgorithmException;

/**
 * All rights reserved & copyright ©
 */
public interface Array extends Value {
    int getDimensionsCount();
    void setValue(int[] indexes, SimpleValue value) throws AlgorithmException;
    void setValue(int[] indexes, Array value) throws AlgorithmException;

    ArrayCell getCell(int[] indexes) throws AlgorithmException;
    ArrayCell[] getCells();
    default Value getElement(int indexes[]) throws AlgorithmException {
        return getCell(indexes).getValue();
    }

    default boolean isArray(){
        return true;
    }
}
