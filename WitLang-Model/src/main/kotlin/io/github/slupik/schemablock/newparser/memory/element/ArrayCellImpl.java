package io.github.slupik.schemablock.newparser.memory.element;

import io.github.slupik.schemablock.model.ui.error.AlgorithmException;
import io.github.slupik.schemablock.newparser.compilator.exception.IncompatibleArrayException;

/**
 * All rights reserved & copyright ©
 */
public class ArrayCellImpl implements ArrayCell {

    private final ValueType type;
    private Value value;
    private int dimensionCount;
    private int index;

    public ArrayCellImpl(ValueType type) {
        this.type = type;
    }

    @Override
    public Value getValue() {
        return value;
    }

    @Override
    public void setValue(Value value) throws AlgorithmException {
        if (dimensionCount == 0) {
            if (!(value instanceof SimpleValue)) {
                throw new ExceptedValue();
            }
        } else {
            if (!(value instanceof Array)) {
                throw new ExceptedArray();
            }
            Array array = (Array) value;
            if (array.getDimensionsCount() != dimensionCount) {
                throw new IncompatibleArrayException(dimensionCount, array.getDimensionsCount());
            }
        }
        this.value = value;
    }

    @Override
    public int getDimensionCount() {
        return dimensionCount;
    }

    @Override
    public void setDimensionCount(int dimensionCount) {
        this.dimensionCount = dimensionCount;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public ValueType getType() {
        return type;
    }
}
