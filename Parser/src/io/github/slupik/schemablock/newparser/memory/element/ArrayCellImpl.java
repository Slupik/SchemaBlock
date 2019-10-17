package io.github.slupik.schemablock.newparser.memory.element;

/**
 * All rights reserved & copyright ©
 */
public class ArrayCellImpl implements ArrayCell {

    private final ValueType type;
    private Value value;

    public ArrayCellImpl(ValueType type) {
        this.type = type;
    }

    @Override
    public Value getValue() {
        return value;
    }

    @Override
    public void setValue(Value value) {
        this.value = value;
    }

    @Override
    public ValueType getType() {
        return type;
    }
}
