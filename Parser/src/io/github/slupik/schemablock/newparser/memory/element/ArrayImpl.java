package io.github.slupik.schemablock.newparser.memory.element;

import io.github.slupik.schemablock.model.ui.error.AlgorithmException;
import io.github.slupik.schemablock.newparser.compilator.exception.IncompatibleArrayException;
import io.github.slupik.schemablock.newparser.compilator.exception.IncompatibleTypeException;
import io.github.slupik.schemablock.newparser.compilator.exception.IndexOutOfBoundsException;

import java.util.Arrays;

/**
 * All rights reserved & copyright ©
 */
public class ArrayImpl implements Array {

    private final ValueType TYPE;
    private final int DIMENSIONS;
    private final ArrayCell[] VALUES;

    public ArrayImpl(ValueType type, int dimensions, int size) {
        TYPE = type;
        DIMENSIONS = dimensions;
        VALUES = new ArrayCell[size];
        for(int i = 0;i<size;i++) {
            VALUES[i] = new ArrayCellImpl(type);
        }
    }

    @Override
    public int getDimensionsCount() {
        return DIMENSIONS;
    }

    @Override
    public void setValue(int[] indexes, SimpleValue value) throws AlgorithmException {
        if(ValueType.isCompatible(TYPE, value.getType())) {
            if(indexes.length>1) {
                getArray(
                        Arrays.copyOfRange(indexes, 0, indexes.length-1)
                ).setValue(
                        new int[]{indexes[indexes.length-1]},
                        value
                );
            } else {
                if(indexes.length == 1) {
                    if(DIMENSIONS==1) {
                        if(VALUES.length>indexes[0]) {
                            VALUES[indexes[0]].setValue(value);
                        } else {
                            throw new IndexOutOfBoundsException(VALUES.length, indexes[0]);
                        }
                    } else {
                        throw new IncompatibleArrayException(0, indexes.length);
                    }
                } else {
                    throw new IncompatibleArrayException(indexes.length, 1);
                }
            }
        } else {
            throw new IncompatibleTypeException(TYPE, value.getType());
        }
    }

    @Override
    public void setValue(int[] indexes, Array value) throws AlgorithmException {
        if(value.getType() == TYPE) {
            if(value.getDimensionsCount()+indexes.length==DIMENSIONS) {
                if(indexes.length==1) {
                    if(VALUES.length>indexes[0]) {
                        VALUES[indexes[0]].setValue(value);
                    } else {
                        throw new IndexOutOfBoundsException(VALUES.length, indexes[0]);
                    }
                } else {
                    Array array = getArray(Arrays.copyOfRange(indexes, 0, indexes.length-1));
                    array.setValue(new int[]{indexes[indexes.length-1]}, value);
                }
            } else {
                throw new IncompatibleArrayException(DIMENSIONS, indexes.length);
            }
        } else {
            throw new IncompatibleTypeException(TYPE, value.getType());
        }
    }

    @Override
    public ArrayCell getCell(int[] indexes) throws AlgorithmException {
        if(indexes.length==1) {
            return VALUES[indexes[0]];
        } else {
            if(DIMENSIONS==indexes.length) {
                ArrayCell cell = VALUES[indexes[0]];
                Value memorizes = cell.getValue();
                if(memorizes instanceof Array) {
                    return ((Array) memorizes).getCell(Arrays.copyOfRange(indexes, 1, indexes.length));
                } else {
                    throw new ExceptedArray();
                }
            } else {
                throw new IncompatibleArrayException(DIMENSIONS, indexes.length);
            }
        }
    }

    @Override
    public ArrayCell[] getCells() {
        return VALUES;
    }

    private Array getArray(int[] indexes) throws AlgorithmException {
        Memoryable memorized = getElement(indexes);
        if(memorized instanceof Array) {
            return (Array) memorized;
        } else {
            throw new ExceptedArray();
        }
    }

    @Override
    public ValueType getType() {
        return TYPE;
    }
}
