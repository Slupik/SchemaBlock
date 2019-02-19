package io.github.slupik.schemablock.newparser.memory.element;

import io.github.slupik.schemablock.newparser.compilator.exception.IncompatibleArrayException;
import io.github.slupik.schemablock.newparser.compilator.exception.IncompatibleTypeException;

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
    public Value getContent() {
        return value;
    }

    @Override
    public void setContent(Value value) throws IncompatibleTypeException, IncompatibleArrayException {
        if(value!=null) {
            if(!ValueType.isCompatible(type, value.getType())) {
                throw new IncompatibleTypeException(type, value.getType());
            }
            if(value.isArray()) {
                Array array = ((Array) value);
                if(array.getDimensionsCount()!= dimensions) {
                    throw new IncompatibleArrayException(dimensions, array.getDimensionsCount());
                }
            } else {
                if(dimensions!=0) {
                    //TODO error
                }
            }
        }
        this.value = value;
    }

    @Override
    public ValueType getType() {
        return type;
    }

    @Override
    public int getDimensionsCount() {
        return dimensions;
    }





//    public void setContentOld(Value value) throws IncompatibleTypeException, IncompatibleArrayException {
//        if(value!=null) {
//            if(!ValueType.isCompatible(type, value.getType())) {
//                throw new IncompatibleTypeException(type, value.getType());
//            }
//            if(value.getDimensions()!= getDimensionsCount()) {
//                throw new IncompatibleArrayException(getDimensionsCount(), value.getDimensions());
//            }
//        }
//        this.value = value;
//    }
//
//    public SimpleValue getContentOld(int index) throws ExceptedArrayButNotReceivedException, IndexOutOfBoundsException {
//        if(!value.isArray()) {
//            throw new ExceptedArrayButNotReceivedException();
//        }
//        return value.getValue(index);
//    }
//
//    public void setContentOld(int index, Value value) throws IncompatibleTypeException, IndexOutOfBoundsException, ExceptedArrayButNotReceivedException, IncompatibleArrayException {
//        if(!this.value.isArray()) {
//            throw new ExceptedArrayButNotReceivedException();
//        }
//        this.value.setValue(index, value);
//    }
//
//
//    public void setContentOld(int indexes[], Value value) throws IncompatibleTypeException, IndexOutOfBoundsException, ExceptedArrayButNotReceivedException, IncompatibleArrayException {
//        if(!this.value.isArray()) {
//            throw new ExceptedArrayButNotReceivedException();
//        }
//        this.value.setValue(indexes, value);
//    }

}
