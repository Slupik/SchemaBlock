package io.github.slupik.schemablock.newparser.memory.element;

import io.github.slupik.schemablock.model.ui.error.AlgorithmException;

/**
 * All rights reserved & copyright ©
 */
public interface Variable extends Memoryable  {
    String getName();
    int getDimensionsCount();
    Value getContent();
    void setContent(Value value) throws AlgorithmException;
}
