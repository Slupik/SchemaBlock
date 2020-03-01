package io.github.slupik.schemablock.newparser.memory.element;

/**
 * All rights reserved & copyright ©
 */
public interface SimpleValue extends Value {
    <T> T getCastedValue();
    Object getValue();

    default boolean isArray(){
        return false;
    }
}
