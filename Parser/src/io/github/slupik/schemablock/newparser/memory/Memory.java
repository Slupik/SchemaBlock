package io.github.slupik.schemablock.newparser.memory;

import io.github.slupik.schemablock.newparser.memory.element.Variable;

/**
 * All rights reserved & copyright ©
 */
public interface Memory {

    void register(Variable variable);
    Variable get(String name);
}
