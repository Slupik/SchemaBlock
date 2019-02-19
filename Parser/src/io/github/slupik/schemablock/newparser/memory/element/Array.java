package io.github.slupik.schemablock.newparser.memory.element;

/**
 * All rights reserved & copyright ©
 */
public interface Array extends Value {
    int getDimensionsCount();
    void setValue(int[] indexes, SimpleValue value);
    void setValue(int[] indexes, Array value);

    ArrayCell getCell(int[] indexes);
    ArrayCell[] getCells();
    default Value getElement(int indexes[]){
        return getCell(indexes).getValue();
    }

    default boolean isArray(){
        return true;
    }
}
