package io.github.slupik.schemablock.newparser.memory.element;

import io.github.slupik.schemablock.model.ui.error.AlgorithmException;

/**
 * All rights reserved & copyright ©
 */
public interface ArrayCell extends Value {
    Value getValue();

    void setValue(Value value) throws AlgorithmException;

    default boolean isArray() {
        return false;
    }

    int getDimensionCount();

    void setDimensionCount(int dimensionCount);

    int getIndex();

    void setIndex(int index);

}
