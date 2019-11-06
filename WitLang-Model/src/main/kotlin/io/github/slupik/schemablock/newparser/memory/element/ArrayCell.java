package io.github.slupik.schemablock.newparser.memory.element;

/**
 * All rights reserved & copyright ©
 */
public interface ArrayCell extends Value {
    Value getValue();
    void setValue(Value value);

    default boolean isArray(){
        return false;
    }
}
