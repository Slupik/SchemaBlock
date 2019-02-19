package io.github.slupik.schemablock.newparser.memory.element;

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
    public void setValue(int[] indexes, SimpleValue value) {
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
                        VALUES[indexes[0]].setValue(value);
                    } else {
                        System.err.println("SVE1");
                        //TODO throw error
                    }
                } else {
                    System.err.println("SVE2");
                    //TODO throw error
                }
            }
        } else {
            System.err.println("SVE3");
            //TODO throw error
        }
    }

    @Override
    public void setValue(int[] indexes, Array value) {
        if(value.getType() == TYPE) {
            if(value.getDimensionsCount()+indexes.length==DIMENSIONS) {
                if(indexes.length==1) {
                    VALUES[indexes[0]].setValue(value);
                } else {
                    Array array = getArray(Arrays.copyOfRange(indexes, 0, indexes.length-1));
                    array.setValue(new int[]{indexes[indexes.length-1]}, value);
                }
            } else {
                System.err.println("SAE1");
                //TODO throw error
            }
        } else {
            System.err.println("SAE2");
            //TODO throw error
        }
    }

    @Override
    public ArrayCell getCell(int[] indexes) {
        if(indexes.length>0) {
            if(indexes.length==1) {
                return VALUES[indexes[0]];
            } else {
                if(DIMENSIONS==indexes.length) {
                    ArrayCell cell = VALUES[indexes[0]];
                    Value memorizes = cell.getValue();
                    if(memorizes instanceof Array) {
                        return ((Array) memorizes).getCell(Arrays.copyOfRange(indexes, 1, indexes.length));
                    } else {
                        System.err.println("GE1");
                        //TODO throw error
                    }
                } else {
                    System.err.println("GE2");
                    //TODO throw error
                }
            }
        } else {
            System.err.println("GE3");
            //TODO throw error
        }
        return null;
    }

    @Override
    public ArrayCell[] getCells() {
        return VALUES;
    }

    private Array getArray(int[] indexes) {
        Memoryable memorized = getElement(indexes);
        if(memorized instanceof Array) {
            return (Array) memorized;
        } else {
            //TODO throw error
            return null;
        }
    }

    @Override
    public ValueType getType() {
        return TYPE;
    }
}
