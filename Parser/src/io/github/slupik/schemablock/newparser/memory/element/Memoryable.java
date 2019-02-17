package io.github.slupik.schemablock.newparser.memory.element;

/**
 * All rights reserved & copyright ©
 */
public interface Memoryable {
    ValueType getType();
    int getDimensions();
    boolean isValue();

    default boolean isArray(){
        return getDimensions()>0;
    }
}
