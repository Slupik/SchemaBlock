package io.github.slupik.schemablock.newparser.memory.element;

/**
 * All rights reserved & copyright ©
 */
public interface Memoryable {
    ValueType getType();
    int getDimensions();

    default boolean isArray(){
        return getDimensions()>0;
    }
}
