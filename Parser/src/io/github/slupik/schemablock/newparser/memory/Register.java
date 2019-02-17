package io.github.slupik.schemablock.newparser.memory;

import io.github.slupik.schemablock.newparser.memory.element.Memoryable;

/**
 * All rights reserved & copyright ©
 */
public interface Register {

    /**
     * Add value
     * @return success
     */
    boolean add(Memoryable value);

    /**
     * Removes and returns value
     * @return top value
     */
    Memoryable pop();

    /**
     * Only returns value
     * @return top value
     */
    Memoryable peek();

    int size();
    void clear();
}
