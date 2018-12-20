package io.github.slupik.schemablock.newparser.memory.element;

import io.github.slupik.schemablock.newparser.executor.exception.ExceptedArrayButNotReceivedException;
import io.github.slupik.schemablock.newparser.executor.exception.IncompatibleArrayException;
import io.github.slupik.schemablock.newparser.executor.exception.IncompatibleTypeException;
import io.github.slupik.schemablock.newparser.executor.exception.IndexOutOfBoundsException;

/**
 * All rights reserved & copyright ©
 */
public class VariableImpl implements Variable {

    private final ValueType type;
    private final int dimensions;
    private final String name;

    private Value value;

    public VariableImpl(ValueType type, int dimensions, String name) {
        this.type = type;
        this.dimensions = dimensions;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Value getValue() {
        return value;
    }

    @Override
    public void setValue(Value value) throws IncompatibleTypeException, IncompatibleArrayException {
        if(value!=null) {
            if(!ValueType.isCompatible(type, value.getType())) {
                throw new IncompatibleTypeException(type, value.getType());
            }
            if(value.getDimensions()!=getDimensions()) {
                throw new IncompatibleArrayException(getDimensions(), value.getDimensions());
            }
        }
        this.value = value;
    }

    @Override
    public Value getValue(int index) throws ExceptedArrayButNotReceivedException, IndexOutOfBoundsException {
        if(!value.isArray()) {
            throw new ExceptedArrayButNotReceivedException();
        }
        return value.getValue(index);
    }

    @Override
    public void setValue(int index, Value value) throws IncompatibleTypeException, IndexOutOfBoundsException, ExceptedArrayButNotReceivedException, IncompatibleArrayException {
        if(!this.value.isArray()) {
            throw new ExceptedArrayButNotReceivedException();
        }
        this.value.setValue(index, value);
    }


    @Override
    public void setValue(int indexes[], Value value) throws IncompatibleTypeException, IndexOutOfBoundsException, ExceptedArrayButNotReceivedException, IncompatibleArrayException {
        if(!this.value.isArray()) {
            throw new ExceptedArrayButNotReceivedException();
        }
        this.value.setValue(indexes, value);
    }

    @Override
    public ValueType getType() {
        return type;
    }

    @Override
    public int getDimensions() {
        return dimensions;
    }

}
