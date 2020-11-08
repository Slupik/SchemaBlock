package io.github.slupik.schemablock.model.ui.newparser;

import io.github.slupik.schemablock.model.ui.error.AlgorithmException;
import io.github.slupik.schemablock.newparser.memory.element.Value;
import io.github.slupik.schemablock.newparser.memory.element.ValueType;

/**
 * All rights reserved & copyright ©
 */
public interface HeapController {

    default void setVariableValue(String name, Value value) throws AlgorithmException {
        setVariableValue(name, new int[0], value);
    }

    void setVariableValue(String name, int[] indexes, Value value) throws AlgorithmException;

    ValueType getVariableType(String name) throws AlgorithmException;

}
