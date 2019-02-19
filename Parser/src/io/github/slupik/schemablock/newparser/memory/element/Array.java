package io.github.slupik.schemablock.newparser.memory.element;

/**
 * All rights reserved & copyright ©
 */
public interface Array extends Memoryable {
    int getDimensionsCount();
    void setValue(int[] indexes, Value value);
    void setValue(int[] indexes, Array value);
    Memoryable getElement(int indexes[]);
}
