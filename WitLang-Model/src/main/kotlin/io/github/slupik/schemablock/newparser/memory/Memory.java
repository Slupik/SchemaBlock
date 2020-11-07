package io.github.slupik.schemablock.newparser.memory;

import io.github.slupik.schemablock.execution.VariableNotFound;
import io.github.slupik.schemablock.model.ui.error.AlgorithmException;
import io.github.slupik.schemablock.newparser.memory.element.Variable;

/**
 * All rights reserved & copyright ©
 */
public interface Memory {

    void register(Variable variable) throws AlgorithmException;

    Variable get(String name) throws AlgorithmException;

    void clear();
}
