package io.github.slupik.schemablock.model.ui.newparser;

import io.github.slupik.schemablock.execution.VariableNotFound;
import io.github.slupik.schemablock.model.ui.error.AlgorithmException;
import io.github.slupik.schemablock.newparser.memory.element.Value;
import io.github.slupik.schemablock.newparser.memory.element.ValueType;

/**
 * All rights reserved & copyright ©
 */
public interface HeapController {

    void setVariableValue(String name, Value value) throws AlgorithmException;

    ValueType getVariableType(String name) throws VariableNotFound;

}
