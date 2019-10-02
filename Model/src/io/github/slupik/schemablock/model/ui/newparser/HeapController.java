package io.github.slupik.schemablock.model.ui.newparser;

import io.github.slupik.schemablock.newparser.compilator.exception.IncompatibleArrayException;
import io.github.slupik.schemablock.newparser.compilator.exception.IncompatibleTypeException;
import io.github.slupik.schemablock.newparser.memory.element.Value;
import io.github.slupik.schemablock.newparser.memory.element.ValueType;
import io.github.slupik.schemablock.both.execution.VariableNotFound;

/**
 * All rights reserved & copyright ©
 */
public interface HeapController {

    void setVariableValue(String name, Value value) throws IncompatibleArrayException, IncompatibleTypeException, VariableNotFound;

    ValueType getVariableType(String name) throws VariableNotFound;
}
