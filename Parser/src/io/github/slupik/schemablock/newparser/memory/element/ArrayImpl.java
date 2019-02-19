package io.github.slupik.schemablock.newparser.memory.element;

import java.util.Arrays;

/**
 * All rights reserved & copyright ©
 */
public class ArrayImpl implements Array {

    private final ValueType TYPE;
    private final int DIMENSIONS;
    private final Memoryable[] VALUES;

    public ArrayImpl(ValueType type, int dimensions, int size) {
        TYPE = type;
        DIMENSIONS = dimensions;
        VALUES = new Memoryable[size];
    }

    @Override
    public int getDimensionsCount() {
        return DIMENSIONS;
    }

    @Override
    public void setValue(int[] indexes, Value value) {
        if(value.getType()==TYPE) {
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
                        VALUES[indexes[0]] = value;
                    } else {
                        //TODO throw error
                    }
                } else {
                    //TODO throw error
                }
            }
        } else {
            //TODO throw error
        }
    }

    @Override
    public void setValue(int[] indexes, Array value) {
        if(value.getType() == TYPE) {
            if(value.getDimensionsCount()+indexes.length==DIMENSIONS) {
                if(indexes.length==1) {
                    VALUES[indexes[0]] = value;
                } else {
                    Array array = getArray(Arrays.copyOfRange(indexes, 0, indexes.length-1));
                    array.setValue(new int[]{indexes[indexes.length-1]}, value);
                }
            } else {
                //TODO throw error
            }
        } else {
            //TODO throw error
        }
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
    public Memoryable getElement(int[] indexes) {
        if(indexes.length>0) {
            if(indexes.length==1) {
                return VALUES[indexes[0]];
            } else {
                if(DIMENSIONS==indexes.length) {
                    Memoryable memorizes = VALUES[indexes[0]];
                    if(memorizes instanceof Array) {
                        return ((Array) memorizes).getElement(Arrays.copyOfRange(indexes, 1, indexes.length));
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
    public ValueType getType() {
        return TYPE;
    }

    @Override
    public int getDimensions() {
        return getDimensionsCount();
    }

    @Override
    public boolean isValue() {
        return false;
    }
}
