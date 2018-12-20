package io.github.slupik.schemablock.newparser.memory.element;

import io.github.slupik.schemablock.newparser.executor.exception.ExceptedMoreDimensionsThanExists;
import io.github.slupik.schemablock.newparser.executor.exception.IncompatibleArrayException;
import io.github.slupik.schemablock.newparser.executor.exception.IncompatibleTypeException;
import io.github.slupik.schemablock.newparser.executor.exception.IndexOutOfBoundsException;

import java.util.Arrays;

/**
 * All rights reserved & copyright ©
 */
public class ValueImpl implements Value {

    private final ValueType type;
    private final Object single;
    private final Value[] array;

    public ValueImpl(ValueType type, Object value) {
        this.type = type;
        if(value instanceof Value && ((Value) value).isArray()) {
            this.array = new Value[]{((Value) value)};
            this.single = null;
        } else {
            this.single = value;
            this.array = null;
        }
    }

    public ValueImpl(ValueType type, Value[] value) {
        this.type = type;
        this.array = value;
        this.single = null;
    }

    @Override
    public Object getValue() {
        if(single!=null) {
            return single;
        } else {
            return array;
        }
    }

    @Override
    public Value getValue(int index) throws IndexOutOfBoundsException {
        if(index>=getArrayLength()) {
            throw new IndexOutOfBoundsException(getArrayLength(), index);
        }
        assert array != null;
        return array[index];
    }

    @Override
    public Value getValue(int[] indexes) throws IndexOutOfBoundsException, ExceptedMoreDimensionsThanExists {
        int firstIndex = indexes[0];
        if(firstIndex>=getArrayLength()) {
            throw new IndexOutOfBoundsException(getArrayLength(), firstIndex);
        }
        if(getDimensions()<indexes.length) {
            throw new ExceptedMoreDimensionsThanExists(getDimensions(), indexes.length);
        }
        if(indexes.length!=1) {
            int[] nestedIndexes = Arrays.copyOfRange(indexes, 1, indexes.length);
            return getValue(firstIndex).getValue(nestedIndexes);
        } else {
            return getValue(firstIndex);
        }
    }

    @Override
    public void setValue(int index, Value value) throws IndexOutOfBoundsException, IncompatibleArrayException, IncompatibleTypeException {
        if(index>=getArrayLength()) {
            throw new IndexOutOfBoundsException(getArrayLength(), index);
        }
        Value copy = null;
        if(value!=null) {
            // type
            if(!ValueType.isCompatible(type, value.getType())) {
                throw new IncompatibleTypeException(type, value.getType());
            }
            // dimension - [][]...[]
            int dim = getDimensions();
            if(dim!=1) {
                if(value.getDimensions()!=(dim-1)) {
                    throw new IncompatibleArrayException(dim-1, value.getDimensions());
                }
            }
            if(value.isArray()) {
                copy = new ValueImpl(type, ((Value[]) value.getValue()));
            } else {
                copy = new ValueImpl(type, value.getValue());
            }
        }
        array[index]=copy;
    }

    @Override
    public void setValue(int[] indexes, Value value) throws IndexOutOfBoundsException, IncompatibleArrayException, IncompatibleTypeException {
        int[] path = Arrays.copyOfRange(indexes, 0, indexes.length-1);

        Value copy = null;
        if(value!=null) {
            // type
            if(!ValueType.isCompatible(type, value.getType())) {
                throw new IncompatibleTypeException(type, value.getType());
            }
            // dimension - [][]...[]
            int dim = getDimensions();
            if(value.getDimensions()!=(dim-path.length-1)) {
                throw new IncompatibleArrayException(dim-path.length-1, value.getDimensions());
            }
            if(value.isArray()) {
                copy = new ValueImpl(type, ((Value[]) value.getValue()));
            } else {
                copy = new ValueImpl(type, value.getValue());
            }
        }
        try {
            if(path.length!=0) {
                getValue(path).setValue(indexes[indexes.length-1], copy);
            } else {
                setValue(indexes[0], copy);
            }
        } catch (ExceptedMoreDimensionsThanExists e) {
            //Something were wrong checked before
            e.printStackTrace();
        }
    }

    @Override
    public int getArrayLength() {
        if(array==null) {
            return -1;
        } else {
            return array.length;
        }
    }

    @Override
    public ValueType getType() {
        return type;
    }

    @Override
    public int getDimensions() {
        if(array==null) {
            if(single instanceof Value) {
                return ((Value) single).getDimensions()+1;
            }
            return 0;
        } else {
            if(array.length>0 && array[0] != null) {
                return array[0].getDimensions() + 1;
            }
            return 1;
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
